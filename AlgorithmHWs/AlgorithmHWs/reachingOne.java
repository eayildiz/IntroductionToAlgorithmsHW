import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
//import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//import javax.lang.model.util.ElementScanner14;

import java.io.BufferedReader;
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.io.InputStream;
//import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class HW3_Q2 {

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
        //Methods I wrote starts here.
        /**Array's indexes will be matched with the integers subtracted by 1.
         * For example, index 0 will represent number 1. So, index n - 1 will represent number n.
         * @param n
         * @param a
         * @param b
         * @param c
         * @return Array reference
         */
        private long[] buildingGraph(int n, int a, int b, int c){
            long adjList[] = new long[n];
            for(int vertex = n - 2; vertex >= 0; vertex--){
                adjList[vertex] = Long.MAX_VALUE;
            }
            adjList[n - 1] = 0;
            return adjList;
        }
        private void relax(long[] graph, int index1, int index2, int edgeWeight){
            if(graph[index2] > graph[index1] + edgeWeight){
                graph[index2] = graph[index1] + edgeWeight;
            }
        }
        //Methods I wrote ends here.
        public int ReachingOne(int n,int a,int b,int c) {
            long adjList[] = buildingGraph(n, a, b, c);
            for(int vertex = n; vertex > 1; vertex--){
                relax(adjList, vertex - 1, vertex - 2, a);
                if(vertex % 2 == 0){
                    relax(adjList, vertex - 1, vertex / 2 - 1, b);
                }
                if(vertex % 3 == 0){
                    relax(adjList, vertex - 1, vertex / 3 - 1, c);
                }
            }
            return (int)adjList[0];
        }
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            System.out.println(ReachingOne(n, a, b, c));
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