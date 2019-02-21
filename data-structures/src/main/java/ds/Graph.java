package ds;

import java.util.Set;
import java.util.HashSet;

public class Graph{

    public static void main(String[] args) {
        Node a = new Node(3), b = new Node(5), c = new Node(7), d = new Node(8), e = new Node(9);
        a.neighbors = new Node[]{b,c};
        b.neighbors = new Node[]{b,c,e};
        c.neighbors = new Node[]{a,d};
        d.neighbors = new Node[]{e};
        e.neighbors = new Node[0];
        System.out.println(Graph.dfs(a, 13));
    }

    public static class Node{
        public int key;
        public Node[] neighbors;
        Node(int key) {this.key = key;}
    }

    public static boolean dfs(Node root, int target){
        return dfs(root, target, new HashSet<>());
    }

    private static boolean dfs(Node n, int target, Set<Integer> visited){
        if(n == null || visited.contains(n.key)) return false;
        visited.add(n.key);
        boolean found = n.key == target;
        for(int i=0; i<n.neighbors.length && !found; i++){
            found = dfs(n.neighbors[i], target, visited);
        }
        return found;
    }

    public static boolean bfs(Node root, int target){
        return dfs(root, target, new HashSet<>());
    }

    private static boolean bfs(Node n, int target, Set<Integer> visited){
        if(n == null || visited.contains(n.key)) return false;
        visited.add(n.key);
        boolean found = n.key == target;
        for(int i=0; i<n.neighbors.length; i++){
            found = dfs(n.neighbors[i], target, visited);
        }
        return found;
    }
}