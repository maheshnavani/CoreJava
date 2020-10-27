package com.sai.graphalgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Goldman Sachs Q2
// Given a forest , find the root with max length ,
// if there are more than 1 root with max length , print the node with min value
public class GSQ2 {

    public static Integer findSolution(Map<Integer,Integer> forest) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for ( Map.Entry<Integer,Integer> entrySet : forest.entrySet()) {
            Integer parent = entrySet.getValue();
            while ( forest.containsKey(parent))
                parent = forest.get(parent);

            adjList.computeIfAbsent(parent, k -> new ArrayList<>()).add(entrySet.getKey());
        }
        int maxLen =0;
        int minNode = Integer.MAX_VALUE;

        for ( Map.Entry<Integer,List<Integer>> entrySet : adjList.entrySet()) {
            if ( entrySet.getValue().size() > maxLen) {
                maxLen = entrySet.getValue().size();
                minNode = entrySet.getKey();
            }
            else if ( maxLen ==  entrySet.getValue().size() && minNode  > entrySet.getKey()) {
                minNode = entrySet.getKey();
            }
        }
        return minNode;
    }
    public static void main(String[] args) {

        Map<Integer,Integer> forest = new HashMap<>();
        forest.put(1,2);
        forest.put(10,2);
        forest.put(3,4);
        forest.put(5,4);

        System.out.println(findSolution(forest)); // 2
    }
}

