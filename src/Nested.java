import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hgoldstien on 11/23/14.
 */
public class Nested {
    public class Graph{
        int n;
        ArrayList<ArrayList<Integer>> adjacency;

        public int depthFristSearch(int vertex, ArrayList<Integer> distances)
        {
            distances[vertex] = 0;
            
        }

        public int longestDistance(){
            ArrayList<Integer> distances = new ArrayList<Integer>(n);
            for (int i=0; i<n; i++){
                distances.set(i, -1);
            }

            for (int i=0; i<n; i++){
                if (distances.get(i) == -1){
                    depthFristSearch(i, distances);
                }
            }

        }

    }
}
