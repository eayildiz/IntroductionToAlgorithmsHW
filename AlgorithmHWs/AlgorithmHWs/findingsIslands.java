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
import java.io.InputStream;
import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class HW3_Q1 {

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
        private void drawIsland(int row, int column, int n, int m, int[][] mat) {
            if(row < 0 || row >= n || column < 0 || column >= m)    return;
            if(mat[row][column] == 1){
                mat[row][column] = 2;
            }
            else    return;
            int coordinateChange_row[] = {-1, 0, +1, 0};
            int coordinateChange_column[] = {0, -1, 0, +1};
            for(int i = 0; i < 4; i++){
                drawIsland(row + coordinateChange_row[i], column + coordinateChange_column[i], n, m, mat);
            }
        }
        public int numberOfIslands(int n,int m,int mat[][]) {
            int numberOfIslands = 0;
            for(int row = 0; row < n; row++){
                for(int column = 0; column < m; column++){
                    if(mat[row][column] == 1){
                        numberOfIslands++;
                        drawIsland(row, column, n, m, mat);
                    }
                }
            }
            return numberOfIslands; 
        }


        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int map[][] = new int [n][m];
            for (int i = 0 ; i < n ; i++)
                for (int j = 0 ; j < m ; j++)
                    map[i][j] = in.nextInt();
            System.out.println(numberOfIslands(n,m,map));
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