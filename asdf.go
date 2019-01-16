package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
)

func main() {
	var M int
	var N int
	fmt.Scanln(&M, &N)

	children := make([]int, N)
	scanner := bufio.NewScanner(os.Stdin)

	var child int

	for i:= 0; i < N; i++ {
		scanner.Scan()
		child, _ = strconv.Atoi(scanner.Text())
		children[i] = child
	}

	sort.Ints(children)
	largestValue := children[len(children)-1]

	OuterLoop:
	for M > 0 {
		for i := len(children) - 1; i >= 0; i--{
			child := children[i]
			if child >= largestValue {
				children[i] -= 1
				M -= 1
			} else {
				break
			}

			if M == 0 {
				break OuterLoop
			}
		}
		largestValue -= 1
	}

	anger := 0

	for _, child := range children {
		anger += child * child
	}

	fmt.Println(anger)
}
