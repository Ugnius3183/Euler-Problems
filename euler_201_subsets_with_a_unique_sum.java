import java.util.*;

public class euler_201_subsets_with_a_unique_sum {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(); //New integer array

        //Add all arguments to the array, creating our set
        for (String arg : args) {
            nums.add(Integer.parseInt(arg));
        }
        int subsetSize = 3;
        List<List<Integer>> combinations = getCombinations(nums, nums.size(), subsetSize); //Generate combinations
        System.out.println(getUniqueSubsetSum(combinations)); //Print the sum of all unique subset sums
    }

    //Uses generate subsets method to get all combinations
    public static List<List<Integer>> getCombinations(List<Integer> nums, int length, int subsetSize) {
        List<Integer> curr = new ArrayList<>();

        //Fill curr's indexes to avoid indexing errors
        for (int i = 0; i < subsetSize; i++) {
            curr.add(0);
        }
        List<List<Integer>> combinations = new ArrayList<>();
        generateSubsets(combinations, nums, curr, 0, length - 1, 0, subsetSize);
        return combinations;
    }

    //Generate all combinations of a given size of a set of numbers
    public static void generateSubsets(List<List<Integer>> combinations, List<Integer> nums, List<Integer> curr, int start, int end, int index, int subsetSize) {

        //Check if a new combination is ready
        if (index == subsetSize) {
            combinations.add(new ArrayList<>(curr));
            return;
        }
 
        //Loop from start to end of the set of numbers and ensure that there always remains enough numbers in the set to generate a subset
        for (int i = start; i <= end && end - i + 1 >= subsetSize - index; i++) {
            curr.set(index, nums.get(i));
            generateSubsets(combinations, nums, curr, i + 1, end, index + 1, subsetSize); //Recursive call
        }
    }

    //Calculates and returns the sum of all unique subset sums
    public static int getUniqueSubsetSum(List<List<Integer>> subsets) {
        Map<Integer, Integer> sumCounter = new HashMap<>(); //Create a new map of integer keys and values
        int sum = 0;

        //For every subset in subsets
        for (List<Integer> subset : subsets) {

            //For every number in the subset
            for (int num : subset) {
                sum += num;
            }

            //If the sum already exists in the map, increment the value, otherwise add the sum with a value of 1
            if (sumCounter.containsKey(sum)) {
                sumCounter.put(sum, sumCounter.get(sum) + 1);
            } else {
                sumCounter.put(sum, 1);
            }
            sum = 0;
        }
        int sumOfUniqueSums = 0;

        // Loop through hashmap, ref: https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        for (Map.Entry<Integer, Integer> mapEntry : sumCounter.entrySet()) {

            //If the counter of the sum is 1 (is it a unique sum?)
            if (mapEntry.getValue() == 1) {
                sumOfUniqueSums += mapEntry.getKey();
            }
        }
        return sumOfUniqueSums;
    }
}