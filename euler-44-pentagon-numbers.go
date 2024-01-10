package main

import(
	"fmt"
	"strconv"
	"math"
	"os"
)

func main() {
	arg, _ := strconv.Atoi(os.Args[1]) // Get command-line argument
	pen_nums := generate_pentagon_nums(arg) // Generate all pentagon numbers up to the number term given by arg
	min_diff := math.MaxInt64 // Sets min_diff to the maximum integer go allows
	curr := make([]int, 2) // Create a list of size 2 to hold pairs of pentagon numbers
	evaluate_pen_pairs(&min_diff, pen_nums, curr, 0, arg - 1, 0) // Evaluate the generated pentagon numbers, the address to min_diff is given
	if min_diff == math.MaxInt64 { // If this is true then the command line argument given was too small
		fmt.Println("No pairs with pentagon sum and diff found in given range")
	} else {
		fmt.Println(min_diff)
	}
}

// Generates every pentagon number up to the nth term
func generate_pentagon_nums(n int) []int {
	pen_nums := []int{} // Make list to store pentagon numbers
	for i := 1; i <= n; i++ {
		pen_num := (i * ((3 * i) - 1)) / 2
		pen_nums = append(pen_nums, pen_num) // Append to end of list
	}
	return pen_nums
}

// Evaluate every pair of pentagon numbers in the given range and keeps track of the smallest difference between a pair
// Derived from generate_subsets from euler-201-subsets-with-unique-sum.go
func evaluate_pen_pairs(min_diff *int, pen_nums []int, curr []int, start int, end int, index int) {

	if index == 2 { //If index is 2 then curr has a pair of pentagon numbers ready
		diff := int(math.Abs(float64(curr[0]) - float64(curr[1]))) // Get absolute difference
		//Check if there exists the sum, difference in the list of pentagon numbers and if the difference is smaller than the current minimum
		if contains(pen_nums, curr[0] + curr[1]) && contains(pen_nums, diff) && diff < *min_diff{
			*min_diff = diff // Track the smallest difference
		}
		return
	}

	// Loop until the end of the slice is reached
	for i := start; i <= end; i++ {
		curr[index] = pen_nums[i] // Set value of pen_nums at index i to an index in curr
		evaluate_pen_pairs(min_diff, pen_nums, curr, i + 1, end, index + 1); // Recursively call function
	}
}

// Check if an item exists in a slice
func contains(slice []int, item int) bool {
	for _, value := range slice {
		if value == item {
			return true
		}
	}
	return false
}