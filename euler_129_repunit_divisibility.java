import java.math.BigInteger;

public class euler_129_repunit_divisibility {

    //Generates a repunit of size k
    public static BigInteger R(BigInteger k) {
        BigInteger r = BigInteger.valueOf(0);

        // Loop as long as i is less than k
        for (BigInteger i = BigInteger.valueOf(0); i.compareTo(k) < 0; i = i.add(BigInteger.valueOf(1))) {
            r = r.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(1));
        }
        return r;
    }

    // Checks if a repunit of length k is divisible by n
    public boolean isDivisible(BigInteger k, BigInteger n) {
        return k.mod(n).compareTo(BigInteger.valueOf(0)) == 0;
    }

    // Finds the least value of k that is divisible by n
    public BigInteger A(BigInteger n) {
        BigInteger k = BigInteger.valueOf(1);

        //While repunit k is not divisible by n
        while (!isDivisible(R(k), n)) {

            //Break if n is divisible by 5 (repunits are never divisible by 5)
            if (isDivisible(n, BigInteger.valueOf(5))) {
                break;
            }
            k = k.add(BigInteger.valueOf(1));
        }
        return k;
    }

    public static void main(String[] args) {
        BigInteger arg = new BigInteger(args[0]); //Argument is
        euler_129_repunit_divisibility lValue = new euler_129_repunit_divisibility();
        BigInteger n = BigInteger.valueOf(3);

        // Loop from numbers 3 to arg until a least value of n is found that exceeds arg
        while (lValue.A(n).compareTo(arg) <= 0) {
            n = n.add(BigInteger.valueOf(2)); //Increment by 2 (repunit is never even)
        }
        System.out.println(n);
    }
}
