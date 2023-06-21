import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.util.Comparator;
import java.util.Stack;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class HW4_Q3 {

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
        public long Holiday(int n,int[] a,int[] b,int[] c) {
            long[][] result = new long[n][3];
            result[0][0] = a[0];
            result[0][1] = b[0];
            result[0][2] = c[0];
            for(int day = 1; day < n; day++){
                result[day][0] = Long.max(result[day - 1][1], result[day - 1][2]) + a[day];
                result[day][1] = Long.max(result[day - 1][0], result[day - 1][2]) + b[day];
                result[day][2] = Long.max(result[day - 1][0], result[day - 1][1]) + c[day];
            }
            return Long.max(Long.max(result[n - 1][0], result[n - 1][1]), result[n - 1][2]);
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int a[] = new int [n];
            int b[] = new int [n];
            int c[] = new int [n];            
            for (int i = 0 ; i < n ; i++) {
                a[i] = in.nextInt();
                b[i] = in.nextInt();
                c[i] = in.nextInt();
            }
            System.out.println(Holiday(n,a,b,c));
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