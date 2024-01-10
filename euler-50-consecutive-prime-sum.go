package main

import(
	"fmt"
	"os"
	"strconv"
)

func main() {
	arg, _ := strconv.Atoi(os.Args[1]) // Get command-line argument
	fmt.Println(find_max_consecutive_prime_sum(arg)) // Print the max consecutive prime sum
}

//Sieve of eratosthenes algorithm finds all prime numbers below or equal to n
//https://www.baeldung.com/cs/prime-number-algorithms
func sieve_of_eratosthenes(n int) []int {
	primes := make([]bool, n+1) // Create a new slice to hold primes
	for i := range primes {
		primes[i] = true // Set every index of primes to True
	}
	// Loop over the numbers from 2 to the square root of n
	for p := 2; p * p <= n; p++ {
		if primes[p] {
			j := p * p // Start from the square of the current prime number
			for j <= n { // While j is less than or equal to n
				primes[j] = false
				j += p // Move to next multiple
			}
		}
	}
	primeNumbers := []int{2} // Creates new list to hold prime numbers, 2 is pre-placed inside as it's the only even prime
		// Loops the length of the boolean list we created with the sieve of eratosthenes, loop every other index
		for p := 3; p <= n; p += 2 {
			if primes[p] { // If the list entry is true, then that index is a prime number
				primeNumbers = append(primeNumbers, p) // Append the index as a prime in the new list
			}
		}
	return primeNumbers
}

func find_max_consecutive_prime_sum(n int) int {
	primeNumbers := sieve_of_eratosthenes(n) // Get list of prime numbers
	max_prime_sum := 0 // Variable to keep the prime with the most consecutive terms that it's the sum of
	max_term_count := 0 // Variable to keep the term count of the highest prime

	for _, prime := range primeNumbers { // For loop that visits every prime in the list
		for start := 0; primeNumbers[start] < prime; start++ { // Loop that changes what prime to start adding from, ex. start from 3 is finished, new start is 5
			prime_sum := 0 // Variable that holds the sum of the primes visited so far
			term_count := 0 // Variable that keeps count of number of primes added up
			for p := start; p < len(primeNumbers); p++ { // For loop goes through every prime after the start prime
				prime_sum += primeNumbers[p] // Add prime to the total sum
				term_count++ // Increment term count
				if prime_sum > prime { // Check if the sum has exceeded the prime we are checking, if it has, we break out of the loop and start adding from a new prime
					break
				}
				if prime_sum == prime { // Check if the sum is equal to prime, if true that means we found a consecutive sum
					if term_count > max_term_count { // Check if the newfound consecutive sum has a higher term count than any we found already
						max_prime_sum = prime_sum // New highest prime
						max_term_count = term_count // New highest term count
					}
				}
			}
		}
    }
	return max_prime_sum
}
