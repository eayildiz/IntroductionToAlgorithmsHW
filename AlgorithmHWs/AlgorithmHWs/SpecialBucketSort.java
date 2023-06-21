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
import java.util.Collections;
import java.util.ArrayList;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {

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
        //Methods I wrote
        public static void insertionSort(ArrayList<Double> bucket){
            double key;
            int indexOfTempElement;
            //We will traverse array from start to end. At every member of array we place it in the correct order by moving it backwards. We start from the second member.
            for(int indexOfMainArray = 1; indexOfMainArray < bucket.size(); indexOfMainArray++){
                key = bucket.get(indexOfMainArray);                                          //elements before this key are sorted.
                indexOfTempElement = indexOfMainArray - 1;
                while(indexOfTempElement >= 0 && key < bucket.get(indexOfTempElement)){      //This loop is for moving the key backwards.
                    bucket.set(indexOfTempElement + 1, bucket.get(indexOfTempElement));
                    indexOfTempElement--;
                }
                bucket.set(indexOfTempElement + 1, key);
            }
        }
        //End of methods I wrote.
        public static double[] bucketSort(double[] arr) {
            int numberOfElements = arr.length;

            //Creating buckets.
            ArrayList<Double>[] buckets = new ArrayList[numberOfElements];
            for(int index = 0; index < numberOfElements; index++){
                buckets[index] = new ArrayList<Double>();
            }
            //Put every object to buckets.
            for(int index = 0; index < numberOfElements; index++){
                //Getting proper bucket then adding that element to the list.
                buckets[(int) (Math.pow(arr[index], 4) * numberOfElements)].add(arr[index]);
            }
            //Sorting buckets.
            for(int index = 0; index < numberOfElements; index++){
                Collections.sort(buckets[index]);
            }
            //Merging arrays
            int finalIndex = 0;
            for(int nthBucket = 0; nthBucket < numberOfElements; nthBucket++){
                for(int index = 0; index < buckets[nthBucket].size(); index++){
                    arr[finalIndex] = buckets[nthBucket].get(index);
                    finalIndex++;
                }
            }
            return arr;
        }

        public void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int testCase = 0 ; testCase < t ; testCase++) {
                int n = in.nextInt();
                double[] array = new double[n];
                for (int i = 0 ; i < n ; i++)
                    array[i] = in.nextDouble();
                array = bucketSort(array);
                for (int i = 0 ; i < n ; i++) {
                    out.print(array[i]);
                    out.print(' ');
                }
                out.println();
            }
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }
 

    }
}
