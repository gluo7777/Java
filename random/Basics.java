import java.util.List;
import java.util.LinkedList;

public class Basics{
    public static void main(String[] args) {
        // char and int conversion
        // get ascii or distance between 2 ascii characters
        int pos = Math.abs('d' - 'a');
        System.out.println("Distance between a and d: " + pos);
        // get char from int
        char c = (char) ('a' + pos);
        System.out.printf("3 from a is %s\n", c);

        // trial division
        int n = 1259;
        System.out.printf("Prime factors for %d=%s\n",n,findPrimeFactors(n));

        // Sieve of Erastothenes
        int n2 = 312;
        System.out.printf("Prime numbers less than or equal to %d=%s\n",n2,findAllPrimeNumbers(n2));
    }

    // math stuff
    // use trial division to compute all prime factors of n
    // use prime numbers > 2
    static List<Integer> findPrimeFactors(int n){
        List<Integer> factors = new LinkedList<>();
        int f = 2;
        while(n > 1){
            if(n % f == 0) {
                factors.add(f);
                while(n % f == 0) n /= f;
            }
            f++;
        }
        return factors;
    }

    /**
     * Sieve of Eratosthenes
     * Running time: O(n * log(logn))
     */
    static List<Integer> findAllPrimeNumbers(int n){
        // bitmap initialize to false
        // assume all i <= n is a prime
        boolean[] notPrime = new boolean[n+1]; // n+1 so you can store n as well
        // loop through possible primes
        // stop when pass square root b/c n's largest prime is either it's square or itself
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(!notPrime[i]){
                // for prime factors of n, strike off all multiples of that factor
                // starting with the 2nd multiple since you know first is a multiple
                for(int j=2; i*j <= n; j++) notPrime[i*j] = true;
            }
        }
        // loop through and add all i not striked off
        List<Integer> primes = new LinkedList<>();
        for (int i = 2; i < notPrime.length; i++) {
            if(!notPrime[i]) primes.add(i);
        }
        return primes;
    }
}