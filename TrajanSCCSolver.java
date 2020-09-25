package com.sai.test_spring;
import java.util.List;
import java.util.*;


public class TrajanSCCSolver {
    private List<List<Integer>> graph;
    private int sccCount,  id = 0;
    private int ids[];
    private int low[];
    private int n =0;
    private boolean onStack[];
    private Deque<Integer> stack ;
    public static int UN_VISITED = -1;
    public boolean solved = false;
    public TrajanSCCSolver(List<List<Integer>> graph){
        if (graph == null)
            throw new IllegalArgumentException("Graph cannot be null");
        this.graph= graph;
        this.n = graph.size();
    }
    public int[] getSccs() {
        if (!solved) solve();
        return low;
    }

    public int sccCount() {
        return sccCount;
    }

    public void solve() {
        ids = new int [n];
        low = new int [n];
        onStack = new boolean [n];
        stack = new ArrayDeque<> ();

        Arrays.fill(ids, UN_VISITED);

        for ( int i = 0; i < n ; i++){
            if ( ids[i] == UN_VISITED) dfs(i);
        }
        solved = true;
    }
    public void dfs(int at) {
        stack.push(at);
        onStack[at] = true;
        ids[at] = low[at] = id++;

        for (int to : graph.get(at)){
            if ( ids[to] == UN_VISITED) {
                dfs(to);
                low[at] = Math.min(low[at], low[to]);
            }
            else if (onStack[to]){
                low[at] = Math.min(low[at], low[to]);
            }
        }

        // On recursive callback, if we're at the root node (start of SSC
        // empty the seen stack until back to root
        if ( ids[at] == low[at]) {
            for (int node = stack.pop();; node  = stack.pop()) {
                onStack[node] = false;
                low[node] = low[at];
                if ( node == at ) break;
            }
            sccCount++;
        }

    }
    // Initializes adjacency list with n nodes.
    public static List<List<Integer>> createGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        return graph;
    }

    // Adds a directed edge from node 'from' to node 'to'
    public static void addEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
    }



    public static void main(String[] arg) {
        int n = 8;
        List<List<Integer>> graph = createGraph(n);

        addEdge(graph, 6, 0);
        addEdge(graph, 6, 2);
        addEdge(graph, 3, 4);
        addEdge(graph, 6, 4);
        addEdge(graph, 2, 0);
        addEdge(graph, 0, 1);
        addEdge(graph, 4, 5);
        addEdge(graph, 5, 6);
        addEdge(graph, 3, 7);
        addEdge(graph, 7, 5);
        addEdge(graph, 1, 2);
        addEdge(graph, 7, 3);
        addEdge(graph, 5, 0);

        TrajanSCCSolver solver = new TrajanSCCSolver(graph);
        Map<Integer, List<Integer>>  multiMap = new HashMap<>();
        int[] sccs = solver.getSccs();
        for (int i = 0; i < sccs.length;i++) {
            if (!multiMap.containsKey(sccs[i])) multiMap.put(sccs[i],new ArrayList<>());
            multiMap.get(sccs[i]).add(i);
        }
        for (List<Integer> scc : multiMap.values())
            System.out.println("Nodes:" + scc + " form Strongly connected component");
        System.out.printf("Number of Strongly Connected Components: %d\n", solver.sccCount());
    }
}
