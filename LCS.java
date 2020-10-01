public class LCS {
    public static void main(String[] args) {
        System.out.println("Hello World");
        LargestCommonSubsequnce lcs = new LargestCommonSubsequnce();
        System.out.println(lcs.getLCS("AGTB","GTXAB"));
        LCSDP lcsdp = new LCSDP();
        System.out.println(lcsdp.solve("AGTB","GTXAB"));

    }


}

class LargestCommonSubsequnce {
    String s1;
    String s2;
    String lcs ="";
    public String getLCS(String s1,String s2) {
        this.s1 = s1;
        this.s2 = s2;

        int count = solve(0,0);
        System.out.println("Count=" + count);
        return lcs;
    }
    public int solve (int i, int j) {
        if ( i == s1.length() || j == s2.length())
            return 0;

        if ( s1.charAt(i) == s2.charAt(j)) {
            lcs = lcs + s1.charAt(i);
            return 1+ solve(i+1 , j+1);
        }
        return Math.max(solve(i+1,j) , solve(i,j+1));

    }
}

class  LCSDP {
    public String solve (String s1, String s2) {
        int [][] dp= new int [s1.length()+1][s2.length()+1];
        for ( int i = 1; i <= s1.length() ; i++) {
            for (int j = 1 ;j <= s2.length() ;j++) {

                if ( s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println("Count=" + dp[s1.length()][s2.length()]);

        // Print LCS
        String lcs= "";
        int i = s1.length() , j = s2.length();
        while ( i > 0 && j > 0) {
            if (dp[i][j-1] >= dp[i][j]) j--;
            else if (dp[i-1][j] >= dp[i][j]) i--;
            else {
                lcs = s1.charAt(i-1) + lcs;
                i--;
                j--;
            }
        }
        return lcs;
    }
}


