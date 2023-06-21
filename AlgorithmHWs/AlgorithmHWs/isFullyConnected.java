import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//import javax.lang.model.util.ElementScanner14;

import java.io.BufferedReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class HW3_Q3 {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(in, out);
        out.close();
    }
    static class TaskA {
        //Methods I wrote.
        private class Node{
            LinkedList<Node> edges;
            Node parentToRepresentator;
            int rank;
            public Node(){
                edges = new LinkedList<>();
                parentToRepresentator = null;
                rank = 0;
            }
        }
        private void makeSet(HW3_Q3.TaskA.Node node) {
            node.parentToRepresentator = node;
        }
        private void union(HW3_Q3.TaskA.Node node1, HW3_Q3.TaskA.Node node2) {
            link(findSet(node1), findSet(node2));
        }
        private void link(HW3_Q3.TaskA.Node parent1, HW3_Q3.TaskA.Node parent2){
            if(parent1.rank > parent2.rank){
                parent2.parentToRepresentator = parent1;
            }
            else{
                parent1.parentToRepresentator = parent2;
                if(parent1.rank == parent2.rank){
                    parent2.rank++;
                }
            }
        }
        private HW3_Q3.TaskA.Node findSet(HW3_Q3.TaskA.Node node) {
            if(node != node.parentToRepresentator){
                node.parentToRepresentator = findSet(node.parentToRepresentator);
            }
            return node.parentToRepresentator;
        }
        //End of methods I wrote.
        public boolean[] PrisonDemolition(int n,int m,int u[],int v[],int order[]) {
            boolean[] results = new boolean[n];
            int num_of_sets = 0;
            Node[] nodes = new Node[n];
            for(int i = 0; i < n; i++){
                nodes[i] = new Node();
            }
            for(int i = 0; i < m; i++){
                nodes[u[i] - 1].edges.add(nodes[v[i] - 1]);
            }
            for(int i = 0; i < n; i++){
                Node currentNode = nodes[order[n - 1 - i] - 1];
                makeSet(currentNode);
                num_of_sets++;
                for(int j = 0; j < currentNode.edges.size(); j++){
                    if(currentNode.edges.get(j).parentToRepresentator != null){
                        if(findSet(currentNode.edges.get(j)) != findSet(currentNode)){
                            num_of_sets--;
                            union(currentNode, currentNode.edges.get(j));
                        }
                    }
                }
                if(num_of_sets == 1){
                    results[n - 1 - i] = true;
                }
                else{
                    results[n - 1 - i] = false;
                }
            }
            return results;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int u[] = new int [m];
            int v[] = new int [m];
            int order[] = new int [n];
            boolean results[];
            for (int i = 0 ; i < m ; i++) {
                u[i] = in.nextInt();
                v[i] = in.nextInt();
            }
            for (int i = 0 ; i < n ; i++)
                order[i] = in.nextInt();
            results = PrisonDemolition(n,m,u,v,order);
            for (int i = 0 ; i < n ; i++)
                if (results[i] == true)
                    System.out.println("YES");
                else
                    System.out.println("NO");
            System.out.println();
        }
 
    }
 
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
 

    }
}