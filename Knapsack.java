package com.sai.test_spring;

import java.util.Arrays;

public class Knapsack {
    public static void main(String[] args) {
        System.out.println("Hello World");
        int weights[] = {0,1,2,4,2,5};
        int values[] = {0,5,3,5,3,2};
        int capacity = 10;
        RecursiveSolution sol1 = new RecursiveSolution(weights,values,capacity);
        //System.out.println(sol1.solve());
        int valuesdp[] = {0,1,2,5,6};
        int weightsdp[] = {0,2,3,4,5};
        int capacitydp = 8;
        DPSolution sol2 = new DPSolution(weightsdp,valuesdp,capacitydp);
        System.out.println(sol2.solve());

    }
}

class DPSolution {
    int weights[] ;
    int values[];
    int n = 0;
    int capacity = 0;
    public static int NOT_SET = -1;
    int dp[][] ;
    public DPSolution(int [] weights , int [] values,int capacity) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
        this.n = weights.length -1;
        System.out.println("n=" + n);
        dp = new int [n+1][capacity+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, NOT_SET));
    }

    public int solve() {

        for (int i = 0 ; i <= n  ;i++) {
            for (int w = 0  ; w <= capacity;w++) {

                if ( i == 0 || w == 0)
                    dp[i][w] = 0;

                else if ( weights[i] > w)
                    dp[i][w] = dp[i-1][w];
                else {
                    // Find Max  1-> Don't Include current weight 2 -> Include current weight
                    dp[i][w]  = Math.max ( dp[i-1][w]  , values[i] + dp[i-1][w - weights[i]]);
                }
            }
        }
        int i = n , j = capacity;
        while ( i> 0  && j > 0) {
            if ( dp[i][j] == dp[i-1][j]) {
                System.out.println( "" + i + "=0");
                i--;
            }
            else {
                System.out.println("" + i + "=1");

                j = j - weights[i];
                i--;
            }
        }
        return dp[n][capacity];
    }

}

class RecursiveSolution {
    /*Method 1: Recursion.

        Approach: A simple solution is to consider all subsets of items and calculate the total weight and value of all subsets. Consider the only subsets whose total weight is smaller than W. From all such subsets, pick the maximum value subset.

        Optimal Sub-structure: To consider all subsets of items, there can be two cases for every item.
            Case 1: The item is included in the optimal subset.
            Case 2: The item is not included in the optimal set.
            Therefore, the maximum value that can be obtained from ‘n’ items is the max of the following two values.

                1.Maximum value obtained by n-1 items and W weight (excluding nth item).
                2.Value of nth item plus maximum value obtained by n-1 items and W minus the weight of the nth item (including nth item).
        If the weight of ‘nth’ item is greater than ‘W’, then the nth item cannot be included and Case 1 is the only possibility.

        Time Complexity = O(2^N)
        Space Complexity = O(N) , as at any given time we would have max N items on stack

In the following recursion tree, K() refers
to knapSack(). The two parameters indicated in the
following recursion tree are n and W.
The recursion tree is for following sample inputs.
wt[] = {1, 1, 1}, W = 2, val[] = {10, 20, 30}
                       K(n, W)
                       K(3, 2)
                   /            \
                 /                \
            K(2, 2)                  K(2, 1)
          /       \                  /    \
        /           \              /        \
       K(1, 2)      K(1, 1)        K(1, 1)     K(1, 0)
       /  \         /   \          /   \
     /      \     /       \      /       \
K(0, 2)  K(0, 1)  K(0, 1)  K(0, 0)  K(0, 1)   K(0, 0)
Recursion tree for Knapsack capacity 2
units and 3 items of 1 unit weight.

    */

    int weights[] ;
    int values[];
    int n = 0;
    int capacity = 0;
    public static int NOT_SET = -1;
    int dp[][] ;
    public RecursiveSolution(int [] weights , int [] values,int capacity) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
        this.n = weights.length - 1;
        dp = new int [n+1][capacity+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, NOT_SET));
    }
    public int solve() {
        return KP(this.n,this.capacity);

    }
    public int  KP(int n , int capacity) {
        if ( dp[n][capacity] != NOT_SET) return dp[n][capacity];
        // Base case
        System.out.println("KP:n=" + n  + "|capacity=" + capacity);
        if ( n <= 0 || capacity <= 0) {
            System.out.println("$$$$>KP:n=" + n  + "|capacity=" + capacity + "|max="+ 0);
            return 0;
        }

        // If weight is greater than available  capacity
        if (weights[n] > capacity) {

            int max =  KP (n -1 , capacity);
            System.out.println("===>KP:n=" + n  + "|capacity=" + capacity + "|max="+ max);
            dp[n][capacity] = max;
            return max;
        }
        else {
            int maxWithoutN = KP(n-1, capacity);
            System.out.println("==>KP:n=" + n  + "|capacity=" + capacity + "|maxWithoutN="+ maxWithoutN);
            int maxWithN =  values[n]  + KP (n-1 , capacity-weights[n]);
            int max= Math.max(maxWithoutN,maxWithN);
            dp[n][capacity] = max;
            System.out.println("####>KP:n=" + n  + "|capacity=" + capacity + "|max="+ max);
            return max;

        }

    }

}
