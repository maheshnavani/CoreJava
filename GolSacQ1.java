package com.sai.graphalgo;

import java.util.*;

// given a list produce Set<set> group
// group by string / reverse string 
public class GSQ1 {
    public static Set<Set<String>> solution(List<String> list) {
        Set<Set<String>> resultSet = new HashSet<>();
        for(String str : list) {
            String reverseStr  = new StringBuffer(str).reverse().toString();  //reverse a String
            boolean flag = false;
            for (Set<String> innerSet : resultSet) {
                if ( innerSet.contains(reverseStr)) {
                    flag = true;
                    innerSet.add(str);
                }
            }
            if (!flag) {
                Set<String> innerSet = new HashSet<>();
                innerSet.add(str);
                resultSet.add(innerSet);
            }

        }
        return resultSet;
    }
    public static void main(String[] args) {
        Set<Set<String>> groups = solution(Arrays.asList("cat","dog","god","cat"));
        for ( Set<String> innerSet: groups){
            System.out.println(Arrays.toString(innerSet.toArray()));
        }
        System.out.println(groups.contains(new HashSet<String>(new ArrayList<String>(Arrays.asList("cat")))));
    }
}
