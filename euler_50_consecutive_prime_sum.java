import java.util.*;

public class euler_50_consecutive_prime_sum {
    private int maxPrimeSum; //Declare max prime sum
    private int maxTermCount; //Declare max term count

    //Constructor for declared attributes
    public euler_50_consecutive_prime_sum() {
        this.maxPrimeSum = 0;
        this.maxTermCount = 0;
    }

    //Generate all prime numbers up to the nth term
    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean[] primes = new boolean[n+1]; //Create boolean array
        Arrays.fill(primes, true); //Fill the boolean array with true at all indexes

        //Loop from numbers 2 to the square root of n
        for (int p = 2; p * p <= n; p++) {

            //Check if primes at index p is true (is a prime)
            if (primes[p]) {
                int j = p * p; //Start at p squared

                //While j is less than or equal to n
                while (j <= n) {
                    primes[j] = false; //Set primes at index j to false (not a prime)
                    j += p; //Add p to j (every multiple is not a prime)
                }
            }
        }
        List<Integer> primeNumbers = new ArrayList<>(); //Create a new integer array to hold the prime numbers
        primeNumbers.add(2); //Add 2 as it's the only even prime

        //Loop through every odd before or equal to n
        for (int p = 3; p <= n; p += 2) {

            //If primes at index p is true (is a prime)
            if (primes[p]) {
                primeNumbers.add(p); //Add index p to the int list
            }
        }
        return primeNumbers; //Return primes
    }

    public void findMaxConsecutivePrimeSum(int n) {
        List<Integer> primeNumbers = sieveOfEratosthenes(n); //Generate primes

        //Iterate through every prime in the array
        for (int prime : primeNumbers) {

            //Iterate through every starting prime
            for (int start = 0; primeNumbers.get(start) < prime; start++) {
                int primeSum = 0;
                int termCount = 0;

                //Iterate through every prime that comes after the start prime
                for (int p = start; p < primeNumbers.size(); p++) {
                    primeSum += primeNumbers.get(p);
                    termCount++; //Increment term count

                    //Break and move on to next start pos if the consecutive sum goes over the prime
                    if (primeSum > prime) {
                        break;
                    }

                    //If the consecutive prime sum is equal to the prime
                    if (primeSum == prime) {

                        //Check if the number of primes in the consecutive sum exceeds the current highest
                        if (termCount > this.maxTermCount) {
                            this.maxPrimeSum = primeSum; //Change max prime sum attribute
                            this.maxTermCount = termCount; //Change max term count attribute
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        euler_50_consecutive_prime_sum primeSum = new euler_50_consecutive_prime_sum(); //Create a new prime sum object
        primeSum.findMaxConsecutivePrimeSum(Integer.parseInt(args[0])); //Calculate the max consecutive prime sum
        System.out.println(primeSum.maxPrimeSum);
    }
}