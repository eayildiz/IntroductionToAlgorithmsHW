import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Comparator;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class HW4_Q2 {

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
        public int ConvertToPalindrome(String s) {
            int lengthOfString = s.length();
            int i, j, gap;
            int[][] table = new int[lengthOfString][lengthOfString];
            for(gap = 1; gap < lengthOfString; gap++){
                for(i = 0, j = gap; j < lengthOfString; i++, j++){
                    table[i][j] = (s.charAt(i) == s.charAt(j)) ? table[i + 1][j - 1] : (Integer.min(table[i][j - 1], table[i + 1][j]) + 1);
                }
            }
            return table[0][lengthOfString - 1];
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            String s = in.next();
            System.out.println(ConvertToPalindrome(s));
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