package com.sai.test_spring;

import java.util.*;
import java.util.Collections.*;

public class CollabrativeFiltering {
    Map<String, Integer> productMap = new HashMap<>();
    Map<String, List<Integer>> userMap = new HashMap<>();
    Map< Integer,List<String>> inversionMap = new TreeMap<>();

    public void add(String line, int row) {
        String [] arr = line.split(":");
        if ( arr.length < 2) return;
        List<String> products = Arrays.asList(arr[1].split(","));
        if (row == 1) {
            for ( int i = 0 ; i < products.size() ; i++) {
                productMap.put(products.get(i),i);
            }
        }
        List<Integer> productEnums = new ArrayList<>();
        for ( int i = 0 ; i < products.size() ; i++) {
            productEnums.add(productMap.get(products.get(i)));
        }
        userMap.put(arr[0],productEnums);

    }

    public void calculateInversion() {
        for (Map.Entry<String, List<Integer>> set : userMap.entrySet()) {
            System.out.println(set.getKey() + " = " + set.getValue());
            List<Integer> productEnums = set.getValue();
            int inversion = 0;
            for ( int i = 0 ; i < productEnums.size() ; i++) {
                for (int j = i+1 ; j <productEnums.size() ; j++) {
                    if ( productEnums.get(i) > productEnums.get(j)) inversion++;
                }
            }
            if ( inversion == 0)
                continue;
            if (! inversionMap.containsKey(inversion))
                inversionMap.put(inversion,new ArrayList<>());
            inversionMap.get(inversion).add(set.getKey());
        }
    }
    public static void main(String[] args) {
        String str1 = "rabbit:carrot,cabbage,fish,meat";
        String str2 = "turtle:cabbage,carrot,fish,meat";
        String str3 = "cat:fish,meat,carrot,cabbage";
        CollabrativeFiltering filter = new CollabrativeFiltering();
        filter.add(str1, 1);
        filter.add(str2,2);
        filter.add(str3,2);

        filter.calculateInversion();
        filter.printInversionMap();

    }

    void printInversionMap() {
        List<String> users = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : inversionMap.entrySet())
        {
            List<String> values = entry.getValue();
            Collections.sort(values);
            users.addAll(values);
        }
        String str = String.join(",",users);
        System.out.println(str);
    }
}
