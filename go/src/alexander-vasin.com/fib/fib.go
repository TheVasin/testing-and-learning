package fib

import "math"

var cache [math.MaxInt16]uint64

func Fib(n int) int {
	if n == 0 { return 0 }
	if n == 1 { return 1 }
	return Fib(n-1) + Fib(n-2)
}

func FibWithCache(n int) uint64 {
	if n == 0 { return 0 }
	if n == 1 { return 1 }

	if cache[n] != 0 { return cache[n] }
	cache[n] = FibWithCache(n-2) + FibWithCache(n-1)
	return cache[n]
}

	
