public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        int  w[] = new int[] {1,2,3,4};
        int  c[] = new int[] {5,13,9,6};
        int  out[] = new int[w.length];

        callKS(new int[] {1,2,3,4} , new int [] {5,13,9,6},4);
        callKS(new int[] {1,2,3} , new int [] {5,3,9},3);
    }
    public static void callKS(int[] weight , int []profit , int capacity) {
        System.out.println("Recursive KP :" + knapsack(weight,profit,capacity,weight.length));
        System.out.println("DP :"+knapsackDP(weight,profit,capacity));
    }
    public static int knapsack(int[]w , int[]p , int capacity, int index) {

        // Base Case
        if ( index == 0 || capacity == 0) {
            return 0;
        }
        // When weight > pending capacity
        if ( w[index -1] > capacity) {
            return knapsack(w,p,capacity,index-1);
        }
        // When weight < pending capacity, we have 2 option to add or to skip
        else {
            int addIndex = p[index -1] + knapsack(w,p,capacity - w[index-1], index-1);
            int skipIndex = knapsack(w,p,capacity,index-1);
            return Math.max(addIndex,skipIndex);
        }

    }
    public static int knapsackDP(int weight[],int[] profit, int capacity) {
        int dp[][] = new int[profit.length+1][capacity+1];
        for (int row = 0 ; row < profit.length+1; row++ ) {
            for (int col =0 ; col < capacity+1 ; col++) {
                // Base Case
                if (row == 0 || col == 0 ) {
                    dp[row][col] = 0;
                }
                // If weight > col, take previous row results
                else if ( weight[row -1] > col) {
                    dp[row][col] = dp[row-1][col];
                }
                // if weight < col  , 2 option add the profit or skip
                else {
                    int addIndex = profit[row-1] + dp[row-1][col - weight[row-1]];
                    int skipIndex = dp[row-1][col];
                    dp[row][col] = Math.max(addIndex,skipIndex);
                }
                //print(dp,row,col);
            }
        }
        return dp[profit.length][capacity];
    }
    public static void print(int[][] dp,int r, int c) {
        System.out.printf("Computed row=%d|col-%d\n",r,c);
        for ( int row  = 0; row < dp.length;row++ ) {
            for ( int col = 0 ; col < dp[row].length ; col++) {
                System.out.printf(" %d ",dp[row][col]);
            }
            System.out.printf("\n");
        }
    }
}
