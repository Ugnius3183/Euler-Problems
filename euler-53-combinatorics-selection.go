package main

import (
	"fmt"
	"math/big" // Any factorial over 12 exceeds the normal maximum value that int can have. math/big is used to make these big numbers work
	"os"
	"strconv"
)

func main() {
	arg, _ := strconv.Atoi(os.Args[1]) //Get command line argument
	n := big.NewInt(int64(arg)) // Set n to be the big integer version of the argument
	fmt.Println(distinct_combinatorics(n)) // Print count of distinct nCr that exceed 1000000
}

// Use the combinatoric formula to get any outputs over 1000000
func distinct_combinatorics(n *big.Int) int {
	mil := big.NewInt(1000000)
	count := 0
	// Loop from numbers 1 to n
	for i := big.NewInt(1); i.Cmp(n) <= 0; i.Add(i, big.NewInt(1)) {
		// Loop from numbers 1 to i
		for j := big.NewInt(1); j.Cmp(i) <= 0; j.Add(j, big.NewInt(1)) {
			if c_formula(i, j).Cmp(mil) == 1 {
				count++ // Increment count if the combinatoric is over 1000000
			}
		}
	}
	return count
}

func c_formula(n *big.Int, r *big.Int) *big.Int {
	if r.Cmp(n) == 1 {
		// if r was greater than n, it would not make logical sense if the combination size is larger than the total amount of numbers
		fmt.Println("Logic error: The amount of numbers chosen exceeds the total amount")
		os.Exit(1)
	}
	
	// With the use of Big integer types, this is quite messy, will look to shortening it
	fact_n := factorial(n)
	fact_r := factorial(r)
	diff := big.NewInt(0)
	diff.Sub(n, r)
	diff = factorial(diff)
	prod := big.NewInt(0)
	prod.Mul(fact_r, diff)
	div := big.NewInt(0)
	div.Div(fact_n, prod)
	return div
}

// Function that gets the factorial of a number
func factorial(n *big.Int) *big.Int {
	fact := big.NewInt(1)
	for i := big.NewInt(1); i.Cmp(n) <= 0; i.Add(i, big.NewInt(1)) {
		fact.Mul(fact, i)
	}
	return fact
}