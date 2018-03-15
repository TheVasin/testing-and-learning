package main

import "fmt"
import "alexander-vasin.com/fib"

func main() {
	for i := 0; i < 100; i++ {
		fmt.Println(fib.FibWithCache(i))
	}
}
