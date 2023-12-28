package main

import _ "net/http/pprof"

import (
	"fmt"
	"math"
	"math/rand"
	"sync"
	"time"
	"log"
	"net/http"
)

var TaskId int = 1
var ResultId int = 1
var fileMutex sync.Mutex
var file float32

type Task struct {
	taskId int
	cost float32
	taskType int
	value float32
}

type Result struct {
	resultId int
	result float32
	time float32
}

func createTasks(numberTasks int, maxWriter int, channelTasks chan Task, waitGroup *sync.WaitGroup) {
	defer waitGroup.Done()

	for i := 0; i < numberTasks; {
		var task Task
		task.taskId = TaskId
		task.cost = rand.Float32() * float32(math.Pow10(-1))

		task.taskType = rand.Intn(2)
		if task.taskType == 0 {
			if maxWriter > 0 {
				task.taskType = 0
				task.value = rand.Float32() * 10
				maxWriter--
			} else {
				task.taskType = 1
				task.value = 0
			}
		} else {
			task.taskType = 1
			task.value = 0
		}

		channelTasks <- task
		TaskId++
		i++
	}

	close(channelTasks)
}

func executor(T int, numberTasks int, channelTasks chan Task, channelResults chan Result, waitGroup *sync.WaitGroup) {
	defer waitGroup.Done()

	part := numberTasks / T

	for i := 0; i < T; i++ {
		for j := i * part; j < (i + 1) * part; j++ {
			waitGroup.Add(1)
			task := <- channelTasks
			go worker(task, channelResults, waitGroup)
		}
	}
}

func worker(task Task, channelResults chan Result, waitGroup *sync.WaitGroup) {
	defer waitGroup.Done()

	startTime := time.Now()

	fileMutex.Lock()
	defer fileMutex.Unlock()

	if task.taskType == 0 {
		time.Sleep(time.Duration(task.cost))

		file = file + task.value

		endTime := time.Now()
		time := endTime.Sub(startTime)

		var result Result
		result.resultId = task.taskId
		result.time = float32(time)
		result.result = file

		channelResults <- result
	} else if task.taskType == 1 {
		fmt.Println("File: ", file)

		endTime := time.Now()
		time := endTime.Sub(startTime)

		var result Result
		result.resultId = task.taskId
		result.time = float32(time)
		result.result = file

		channelResults <- result
	}
}

func main() {
	go func() {
		log.Println(http.ListenAndServe(":6060", nil))
	}()

	var N int
	var T int
	var E int
	var maxWriter int
	var maxReader int
	var numberTasks int
	var waitGroup sync.WaitGroup

	fmt.Print("N: ")
	fmt.Scan(&N)
	fmt.Print("T: ")
	fmt.Scan(&T)

	for {
		fmt.Print("E: ")
		fmt.Scan(&E)
		if E >= 0 && E <= 100 {
			break
		}
	}

	numberTasks = int(math.Pow10(N))
	maxReader = numberTasks - (numberTasks * E) / 100
	maxWriter = numberTasks - maxReader

	channelTasks := make(chan Task, numberTasks)

	waitGroup.Add(1)
	go createTasks(numberTasks, maxWriter, channelTasks, &waitGroup)

	startTime := time.Now()

	channelResults := make(chan Result, numberTasks)
	waitGroup.Add(1)
	go executor(T, numberTasks, channelTasks, channelResults, &waitGroup);
	waitGroup.Wait()

	endTime := time.Now()
	time := endTime.Sub(startTime)
	fmt.Println("Time: ", time)

	err := http.ListenAndServe("localhost:6061", nil)
	if err != nil {
		log.Fatal(err)
	}

	//time.Sleep(30 * time.Second)

	select {}
}