import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by hgoldstien on 11/23/14.
 */
public class Nested {
    static Scanner sc;
    public static class Graph{
        int n;
        int[][] adjacency;

        public void createGraph(int numOfVertices){
            n = numOfVertices;
            adjacency = new int[n][];
            int[][] verticies = new int[n][];
            for(int i = 0; i < n; i++){
                verticies[i]= new int[0];
                adjacency[i]= new int[0];
            }
            readInAndAssignData(verticies);

            UpdateAdjacency(verticies);
        }

        private void UpdateAdjacency(int[][] verticies) {
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if ((i != j) && ((verticies[i][0] < verticies[j][0]) && (verticies[i][1] < verticies[j][1]) && (verticies[i][2] < verticies[j][2]))) {
                        appendToArray(i, j);
                    }
                }
            }
        }

        private void appendToArray(int i, int j) {
            int[] temp = adjacency[i];
            int length=0;
            if (temp != null) {
                length = temp.length;
            }
            int[] newArray = new int[length+1];
            int counter = 0;
            if (temp!=null) {
                for (int x : temp) {
                    newArray[counter] = x;
                    counter++;
                }
            }
            newArray[counter] = j;
            adjacency[i] = newArray;
        }

        private void readInAndAssignData(int[][] verticies) {
            for(int i = 0; i < n; i++){
                int temp1 = sc.nextInt();
                int temp2 = sc.nextInt();
                int temp3 = sc.nextInt();
                int[] temp = {temp1, temp2, temp3};
                Arrays.sort(temp);
                verticies[i] = temp;
            }
        }

        public void depthFristSearch(int vertex, int[] distances){
            distances[vertex] = 0;
            for (int i:adjacency[vertex])
            {
                if (distances[i] == -1){
                    depthFristSearch(i, distances);
                }
                distances[vertex] = Math.max(distances[vertex], distances[i]+1);
            }
            
        }

        public int longestDistance(){
            int[] distances = new int[n];
            for (int i=0; i<n; i++){
                distances[i] = -1;
            }
            for (int i=0; i<n; i++){
                if (distances[i] == -1){
                    depthFristSearch(i, distances);
                }
            }
            return getMaxFromArray(distances);

        }

        private int getMaxFromArray(int[] distances) {
            int max = -1;
            for (int i : distances){
                if (i > max){max = i;}
            }
            return max;
        }
    }

    public static void main(String[] args){
        sc = new Scanner(System.in);
        int caseNumber = 0;
        int size = sc.nextInt();
        while (size != -1) {
            caseNumber++;
            Graph theGraph = new Graph();
            theGraph.createGraph(size);
            if (caseNumber > 1){System.out.println();} //Line between cases
            int temp = 1 + theGraph.longestDistance();
            String string = "";
            if (temp != 1)
                string = "es";
            System.out.println("Case "+Integer.toString(caseNumber)+": "+temp+" box"+string);
            size = sc.nextInt();
        }
    }
}
