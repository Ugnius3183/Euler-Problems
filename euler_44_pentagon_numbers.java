import java.util.*;

public class euler_44_pentagon_numbers {

    public static List<Integer> generatePentagonNums(int n) {
        List<Integer> penNums = new ArrayList<>(); //Create new array list

        //Get all pentagon numbers up to the nth term
        for (int i = 1; i <= n; i++) {
            int penNum = (i * ((3 * i) - 1)) / 2;
            penNums.add(i - 1, penNum);
        }
        return penNums;
    }

    //Evaluate all pentagon pairs and keep track of smallest difference
    public static void evaluatePenPairs(int n, List<Integer> curr, int start, int end, int index) {
        List<Integer> penNums = generatePentagonNums(n); //Generate pentagon numbers

        //If a new pair is in curr
        if (index == 2) {
            int diff = Math.abs(curr.get(0) - curr.get(1)); //Get the absolute difference

            //Check if the sum and diff exist in pentagon numbers and if diff is smaller than the smallest diff found
            if (penNums.contains(curr.get(0) + curr.get(1)) && penNums.contains(diff) && diff < GlobalVariable.minDiff) {
                GlobalVariable.minDiff = diff; //Assign diff to global var minDiff
            }
            return;
        }

        //Loop from start of penNums to the end
        for (int i = start; i <= end; i++) {
            curr.set(index, penNums.get(i)); //Set curr at index to a pentagon number
            evaluatePenPairs(n, curr, i+1, end, index+1);
        }
    }

    public static void main(String[] args) {
        int arg = Integer.parseInt(args[0]); //Argument defines the number of pentagon numbers to be evaluated
        List<Integer> curr = new ArrayList<>(); //Create curr arraylist
        curr.add(0); //Fill in first 2 indexes to avoid index errors
        curr.add(0);
        evaluatePenPairs(arg, curr, 0, arg - 1, 0); //Evaluate pentagon numbers
        System.out.println("The minimum difference is: " + GlobalVariable.minDiff); //Print the smallest difference found
    }
}

//I use a global variable here to make it so minDiff can be changed from anywhere in the program
class GlobalVariable {
   public static int minDiff = Integer.MAX_VALUE;
}
