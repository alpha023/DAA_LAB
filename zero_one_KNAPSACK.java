import java.util.ArrayList;
import java.util.List;

public class zero_one_KNAPSACK {
    public static void main(String[] args) {
        int weights[]={95,4,60,32,23,72,80,62,65,46};
        int profit[]={55,10,47,5,4,50,8,61,85,87};

        int M=269;
        int N=weights.length-1;
        List<Integer> selectedItems = new ArrayList<>();
        int maxProfit = knapsack(N, M, profit, weights, selectedItems);

        System.out.println("Maximum Profit: " + maxProfit);
        System.out.println("Selected Items: " + selectedItems);



    }
    public static int knapsack(int N,int M,int p[],int w[], List<Integer> selectedItems){
        // int N=w.length;
        if(N==0 || M==0)return 0;
        if(w[N]>M) return knapsack(N-1, M, p, w,selectedItems);
        else{
            List<Integer> includedItems = new ArrayList<>(selectedItems);
            int include = p[N] + knapsack(N - 1, M - w[N], p, w, includedItems);
            includedItems.add(N+1);

            List<Integer> excludedItems = new ArrayList<>(selectedItems);
            int exclude = knapsack(N - 1, M, p, w, excludedItems);

            if (include > exclude) {
                selectedItems.clear();
                selectedItems.addAll(includedItems);
                return include;
            } else {
                selectedItems.clear();
                selectedItems.addAll(excludedItems);
                return exclude;
            }

        }
        // return max(knapsack(N-1, M, p, w),p[N]+knapsack(N-1, M-w[N], p, w));

    }

    public static int max(int x,int y){
        return x>y?x:y;
    }
}
