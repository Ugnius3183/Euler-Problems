import java.math.BigInteger;

public class euler_53_combinatorics_selection {

    //Uses the combinatorics formula to count every combination over 1000000
    public static int distinctCombinatorics(BigInteger n) {
        BigInteger mil = BigInteger.valueOf(1000000);
        int count = 0;

        //Loop from numbers 1 to n (total numbers)
        for (BigInteger i = BigInteger.valueOf(1); i.compareTo(n) <= 0; i = i.add(BigInteger.valueOf(1))) {

            //Loop from numbers 1 to i
            for (BigInteger r = BigInteger.valueOf(1); r.compareTo(i) <= 0; r = r.add(BigInteger.valueOf(1))) {

                //Check if the combinatoric from i and j exceeds 1000000
                if (cFormula(i, r).compareTo(mil) == 1) {
                   count++; //Increment count
                }
            }
        }
        return count;
    }

    //Get the factorial of n
    public static BigInteger factorial(BigInteger n) {
        BigInteger fact = BigInteger.valueOf(1);

        //Loop from numbers 1 to n
        for (BigInteger i = BigInteger.valueOf(1); i.compareTo(n) <= 0; i = i.add(BigInteger.valueOf(1))) {
            fact = fact.multiply(i);
        }
        return fact;
    }

    //Get the total combinations of n of size r
    public static BigInteger cFormula(BigInteger n, BigInteger r) {

        //Checks if the given combination size is larger than the total (does not make logical sense)
        if (r.compareTo(n) == 1) {
            System.out.println("Logic error: The amount of numbers chosen exceeds the total amount");
            System.exit(1);
        }

        BigInteger fact = factorial(n).divide(factorial(r).multiply(factorial(n.subtract(r)))); //Combinatorics formula
        return fact;
    }

    public static void main(String[] args) {
        BigInteger n = BigInteger.valueOf(Integer.parseInt(args[0])); //Argument is the total items
        System.out.println(distinctCombinatorics(n));
    }
}