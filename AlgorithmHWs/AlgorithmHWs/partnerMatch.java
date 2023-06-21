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
public class HW2_Q1 {

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
        public static int[] quickSort(int[] inputArray){
            divideQuickSort(inputArray, 0, inputArray.length - 1);
            return inputArray;
        }
        private static void divideQuickSort(int[] inputArray, int startIndex, int endIndex){
            if(startIndex < endIndex){
                int pivotIndex = partitionQuickSort(inputArray, startIndex, endIndex);
                divideQuickSort(inputArray, startIndex, pivotIndex - 1);
                divideQuickSort(inputArray, pivotIndex + 1, endIndex);
            }
        }
        private static int partitionQuickSort(int[] inputArray, int startIndex, int endIndex){
            int pivot = inputArray[endIndex];
            int pivotIndex = startIndex - 1;
            for(int arrayIndex = startIndex; arrayIndex < endIndex; arrayIndex++){
                if(inputArray[arrayIndex] < pivot){
                    pivotIndex++;
                    exchangeElements(inputArray, pivotIndex, arrayIndex);
                }
            }
            exchangeElements(inputArray, pivotIndex + 1, endIndex);
            return pivotIndex + 1;
        }
        private static void exchangeElements(int[] inputArray, int index1, int index2){
            int temp = inputArray[index1];
            inputArray[index1] = inputArray[index2];
            inputArray[index2] = temp;
        }
        //End of methods I wrote.
        public int match(int [] M,int[] W,int k) {
            int numberOfMatch = 0;
            int groupSize = M.length;
            Boolean[] whichMenMatched = new Boolean[groupSize];
            Boolean[] whichWomenMatched = new Boolean[groupSize];
            for(int index = 0; index < groupSize; index++){
                whichMenMatched[index] = false;
                whichWomenMatched[index] = false;
            }
            quickSort(M);
            quickSort(W);
            int lastWomenMatched = -1;
            int tempWomenIndex;
            int ageDifference;
            //Iterate men.
            for(int index = 0; index < groupSize && lastWomenMatched != groupSize - 1; index++){
                //First step is finding first woman.
                tempWomenIndex = lastWomenMatched + 1;
                while(M[index] > W[tempWomenIndex] && M[index] - W[tempWomenIndex] > k && tempWomenIndex < groupSize - 1){
                    tempWomenIndex++;
                }
                ageDifference = Math.abs(W[tempWomenIndex] - M[index]);
                while(ageDifference <= k && tempWomenIndex < groupSize){
                    //They can matched. Women is not matched, men can matched, ages are okay.
                    //Update match status of both man and woman, lastWomanMatched and numberOfMatch.
                    if(whichWomenMatched[tempWomenIndex] == false){
                        lastWomenMatched = tempWomenIndex;
                        whichWomenMatched[lastWomenMatched] = true;
                        whichMenMatched[index] = true;
                        numberOfMatch++;
                        break;
                    }
                    tempWomenIndex++;
                    ageDifference = Math.abs(W[tempWomenIndex] - M[index]);
                }
            }
            return numberOfMatch;
        }
        
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] M = new int[n];
            int[] W = new int[n];
            for (int i = 0 ; i < n ; i++)
                M[i] = in.nextInt();
            for (int i = 0 ; i < n ; i++)
                W[i] = in.nextInt();                
            int maxMatch = match(M,W,k);
            out.println(maxMatch);            
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