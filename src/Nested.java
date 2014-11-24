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
            for(int i = 0; i < n; i++){
                int temp1 = sc.nextInt();
                int temp2 = sc.nextInt();
                int temp3 = sc.nextInt();
                int[] temp = {temp1, temp2, temp3};
                Arrays.sort(temp);
                verticies[i] = temp;
            }

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if ((i != j) && (less(verticies[i], verticies[j]))) {
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
                }
            }
        }

        public void depthFristSearch(int vertex, int[] distances){
            distances[vertex] = 0;
            for (int i:adjacency[vertex])
            {
                System.out.println("DFS:"+i);
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
                System.out.println(i);
            }
            for (int i=0; i<n; i++){
                if (distances[i] == -1){
                    System.out.println(" Call DFS");
                    depthFristSearch(i, distances);
                }
            }
            int max = -1;
            for (int i : distances){
                System.out.println(i);
                if (i > max){max = i;}
            }
            return max;

        }

    }

    public static boolean less(int[] one, int[] two){
        return (one[0] < two[0]) && (one[1] < two[1]) && (one[2] < two[2]);
    }

    public static void main(String[] args){
        sc = new Scanner(System.in);
        int caseNumber = 0;
        int size = sc.nextInt();
        while (size != -1) {
            caseNumber++;
            Graph theGraph = new Graph();
            theGraph.createGraph(size);
            if (caseNumber > 1){System.out.println();}
            int temp = 1 + theGraph.longestDistance();
            String string = "";
            if (temp != 1)
                string = "es";
            System.out.println("Case "+Integer.toString(caseNumber)+": "+temp+" box"+string);
            size = sc.nextInt();

        }
    }
}
