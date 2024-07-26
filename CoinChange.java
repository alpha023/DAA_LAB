import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange {
    public static List<Integer> minCoins(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        int[] lastCoin = new int[amount + 1]; // To track the last coin used to achieve dp[i]
        
        Arrays.fill(dp, max); // Fill dp array with a large number (greater than amount)
        Arrays.fill(lastCoin, -1); // Fill lastCoin array with -1 to indicate no coin is used
        dp[0] = 0; // Base case: no coins needed to make 0 amount

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    lastCoin[i] = coin; // Store the last coin used
                }
            }
        }

        if (dp[amount] > amount) {
            // It is not possible to make the amount with the given coins
            return null;
        } else {
            // Backtrack to find the coins used
            List<Integer> result = new ArrayList<>();
            for (int i = amount; i > 0; i -= lastCoin[i]) {
                result.add(lastCoin[i]);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] coins ={1,2,3}; // Coin denominations
        int amount = 4; // Amount to make
        List<Integer> result = minCoins(coins, amount);

        if (result == null) {
            System.out.println("It is not possible to make the amount with the given coins.");
        } else {
            System.out.println("The minimum number of coins required: " + result.size());
            System.out.println("The coins used are: " + result);
        }
    }
}