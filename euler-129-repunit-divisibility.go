package main

import (
	"fmt"
	"math/big"
	"os"
	"strconv"
)

func main() {
	arg, _ := strconv.Atoi(os.Args[1]) // Get command-line argument
	n := big.NewInt(3)
	// Loop from numbers 3 to arg until a least value of n is found that exceeds arg
	for; A(n).Cmp(big.NewInt(int64(arg))) <= 0; n.Add(n, big.NewInt(2)) {} // Increment every other number (all repunits are odd)
	fmt.Println(n)
}

// Creates a repunit of length k
func R(k *big.Int) *big.Int {
	r := big.NewInt(0)
	for i := big.NewInt(0); i.Cmp(k) < 0; i.Add(i, big.NewInt(1)) {
		r.Add(r.Mul(r, big.NewInt(10)), big.NewInt(1))
	}
	return r
}

// Return true if k is divisible by n
func isDivisible(k *big.Int, n *big.Int) bool {
	temp := new(big.Int).Set(k)
	return temp.Mod(R(k), n).Cmp(big.NewInt(0)) == 0
}

// Return the least value of k for which k is divisible by n
func A(n *big.Int) *big.Int {
	k := big.NewInt(1)
	for !isDivisible(k, n) { // while repunit is not divisible by n
		temp := new(big.Int).Set(n)
		if temp.Mod(n, big.NewInt(5)).Cmp(big.NewInt(0)) == 0 { //Checks if n is divisible by 5, a repunit can never be divisible by 5
			break
		}
		
		k.Add(k, big.NewInt(1)) //Add 1 to k
	}
	return k
}