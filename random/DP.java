import java.util.Arrays;

public class DP{
    public static void main(String[] args) {
        int [] coins = {1,2,3,4};
        int amount = 20;
        MinimumCoins.calculate(coins,amount,"naive");
        MinimumCoins.calculate(coins,amount,"recursive");
        MinimumCoins.calculate(coins,amount,"iterative");
    }

    static class MinimumCoins{
        static int methodCalls = 0;
        static void calculate(int[] coins, int amount, String alg){
            methodCalls = 0;
            int result = -1;
            switch (alg) {
                case "recursive":
                    result = MinimumCoins.topDownDFS(coins,amount);
                    break;
                case "iterative":
                    result = MinimumCoins.bottomUpDFS(coins,amount);
                    break;
                default:
                    result = MinimumCoins.naiveDFS(coins,amount);
                    break;
            }
            System.out.printf("Running %s.\n", alg);
            System.out.printf("Minimum combination of coins in %s to create %d is %d.\n", Arrays.toString(coins), amount, result);
            System.out.printf("This took %d method calls.\n", methodCalls);
        }

        /**
         * Time: O(C^C)
         * Space: O(C^C)
         */
        static int naiveDFS(int[] coins, int amount){
            methodCalls++;
            if(amount == 0)  return 0; // reached end of path
            if(amount < -1) return -1; // previous choice resulted in invalid path
            int minCount = Integer.MAX_VALUE;
            // for each coin choose one and calculate height
            // pick coin resulting in smallest height (fewest number of coins)
            for (int coin : coins) {
                int count = naiveDFS(coins, amount - coin);
                if(count >= 0 && count < minCount) minCount = count;
            }
            return minCount == Integer.MAX_VALUE ? -1 : minCount + 1; // add 1 to minCount b/c you selected 1 coin that resulted in the minCount
        }

        /**
         * Time: O(coins.length * amount)
         * Space: O(amount + coins.length * amount) // if including recursive call stack
         * minCounts caches previous topDownDFS results e.g. minCounts[i] = fewest combinations of coin in coins that equal i or -1 if none
         * keep in mind that amount corresponds to i - 1 since array uses 0-based indexing
         * don't forget to set -1 in minCounts if invalid path...
         */
        static int topDownDFS(int[] coins, int amount){
            return MinimumCoins.topDownDFS(coins,amount,new int[amount + 1]);
        }

        static int topDownDFS(int[] coins, int amount, int[] minCounts){
            methodCalls++;
            if(amount == 0)  return 0; // reached end of path
            if(amount < 0) return -1; // previous choice resulted in invalid path
            if(minCounts[amount] != 0) return minCounts[amount]; // return cached result to avoid unnecessary calls
            int minCount = Integer.MAX_VALUE;
            // for each coin choose one and calculate height
            // pick coin resulting in smallest height (fewest number of coins)
            for (int coin : coins) {
                int count = topDownDFS(coins, amount - coin, minCounts);
                if(count >= 0 && count < minCount) {
                    minCount = count + 1; // add 1 to minCount b/c you selected 1 coin that resulted in the minCount
                }
            }
            minCounts[amount] = (minCount == Integer.MAX_VALUE) ? -1 : minCount; // cache result
            return minCounts[amount];
        }

        /**
         * Time: O( coins.length * amount ): for each amount loop through all the coins
         * Space: O( amount + 1 ): array of size amount + 1
         */
        static int bottomUpDFS(int[] coins, int amount){
            if(amount == 0) return 0;
            if(amount < 0) return -1;
            int[] dp = new int[amount + 1]; // base case dp[0] = 0
            int nextAmt = 1;
            while(nextAmt <= amount){ // O(amount)
                int min = Integer.MAX_VALUE;
                for (int coin : coins) { // O(coins)
                    methodCalls++; // everything below is O(1)
                    if(coin <= nextAmt){
                        int prevAmt = dp[nextAmt - coin];
                        if(prevAmt != -1 && prevAmt < min)
                            min = prevAmt + 1; // add this coin to previous solution
                    }
                }
                // cache solution
                if(min == Integer.MAX_VALUE)    dp[nextAmt] = -1;
                else                            dp[nextAmt] = min;
                nextAmt++;
            }
            return dp[amount]; // after all solutions found, return last
        }
    }
}