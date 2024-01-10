package main

import (
	"fmt"
	"os"
	"strconv"
)

func main() {
	args := os.Args[1:] // Get the arguments
	nums := make([]int, len(args))
	for i, arg := range args { // Creates a slice with all arguments
		num, _ := strconv.Atoi(arg)
		nums[i] = num
	}
	subset_size := 3
	combinations := getcombinations(nums, len(nums), subset_size)
	fmt.Println(get_unique_subset_sum(combinations)) // Print sum of unique subset sums
}

// Uses the generate_subsets function to store all subset combinations
func getcombinations(nums []int, length int, subset_size int) [][]int {
	curr := make([]int, subset_size)
	combinations := [][]int{}
	generate_subsets(&combinations, nums, curr, 0, length-1, 0, subset_size) // An address to combinations allows generate_subsets to make changes to combinations
	return combinations
}

//Algorithm to generate all subsets of a given size from a set of numbers
//Made with the help of https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
func generate_subsets(combinations *[][]int, nums []int, curr []int, start int, end int, index int, subset_size int) {

	if index == subset_size { //Checks if curr is filled
		temp := make([]int, subset_size)
		copy(temp, curr)
		*combinations = append(*combinations, temp) // I have no idea why I need to make a temp variable for this but it only works like this
		return
	}

	// Loops from the numbers i to end and ensures that the number of remaining numbers in the set is greater than or equal to the subset size
	for i := start; i <= end && end - i + 1 >= subset_size - index; i++ {
		curr[index] = nums[i]
		generate_subsets(combinations, nums, curr, i + 1, end, index + 1, subset_size); //Recursive call of generate_subsets
	}
}

// Finds the sum of all unique subset sums
func get_unique_subset_sum(subsets [][]int) int {
	sum_counter := make(map[int]int) // Creates a map that holds sums as keys and how many times they occur in their value
	sum := 0
	for _, subset := range subsets { // Iterate through all subsets

		for _, num := range subset { // Iterate through the numbers in the subset
			sum += num // Sum up the subset numbers
		}

		// Check if sum_counter contains sum, ref: https://golang.cafe/blog/how-to-check-if-a-map-contains-a-key-in-go
		_, ok := sum_counter[sum]
		if ok {
			sum_counter[sum] += 1
		} else {
			sum_counter[sum] = 1
		}
		sum = 0
	}
	sum_of_unique_sums := 0
	for key, value := range sum_counter {
		if value == 1 {
			sum_of_unique_sums += key // Adds key to sum of unique sum if the sum occured just once
		}
	}

	return sum_of_unique_sums
}