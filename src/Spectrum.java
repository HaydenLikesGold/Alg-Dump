import com.sun.xml.internal.xsom.impl.scd.Iterators;
import sun.security.provider.certpath.AdjacencyList;

import java.util.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by hgoldstien on 11/23/14.
 */
public class Spectrum {
    public static class Graph{
        private HashMap<String, Integer> vertices;
        private HashMap<String, ArrayList<String>> adj;

        public void addVertex(String vertex){
            if (!vertices.containsKey(vertex)){
                vertices.put(vertex, vertices.size());
                adj.put(vertex, new ArrayList<String>());
            }
        }
        public void addEdge(String vertex1, String vertex2){
            if (!vertices.containsKey(vertex1)) addVertex(vertex1);
            if (!vertices.containsKey(vertex2)) addVertex(vertex2);
            if ((vertex1 != vertex2) && (!adj.get(vertex1).contains(vertex2))){
                adj.get(vertex1).add(vertex2);
                adj.get(vertex2).add(vertex1);
            }
        }
        public boolean contains(String vertex){
            return vertices.containsKey(vertex);
        }

        public HashMap<String, Integer> search(String vertex){
            HashMap<String, Integer> distances = new HashMap<String, Integer>();
            for (int v=0; v<vertices.size(); v++)
            {
                distances.put(v, 100000);
            }
            distances.put(vertex, 0);
            Queue<String> queue= new ArrayDeque<String>();
            queue.add(vertex);
            while (queue.size() > 0) {
                String v = queue.remove();
                for (String w : adj.get(v)) {
                    if (distances.get(w) == 100000) {
                        distances.put(w, distances.get(v) + 1);
                        queue.add(w);
                    }

                }
            }
            return distances;
        }
    }

    public static void main(String[] args){ß
        Graph graph = new Graph();
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        if (line != "") {
            int caseNum = 1;
            System.out.println("Case "+caseNum+":");
        }
        while (line!= "") {
            String[] split = line.split("\\s+");
            if (split[0] == "add"){
                String vertex1 = split[1];
                if (split.length == 2)
                {
                    graph.addVertex(vertex1);
                }
                else{
                    String vertex2 = split[2];
                    graph.addEdge(vertex1, vertex2);
                }
            }
        }


    }
}
