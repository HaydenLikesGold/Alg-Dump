import com.sun.xml.internal.xsom.impl.scd.Iterators;
import sun.security.provider.certpath.AdjacencyList;

import java.util.*;

/**
 * Created by hgoldstien on 11/23/14.
 */
public class Spectrum {
    public class Graph{
        private Set<String> vertices;
        private HashMap<String, ArrayList<String>> adj;

        public void addVertex(String vertex){
            if (!vertices.contains(vertex)){
                vertices.add(vertex);
                adj.put(vertex, new ArrayList<String>());
            }
        }
        public void addEdge(String vertex1, String vertex2){
            if (!vertices.contains(vertex1)) addVertex(vertex1);
            if (!vertices.contains(vertex2)) addVertex(vertex2);
            if ((vertex1 != vertex2) && (!adj.get(vertex1).contains(vertex2))){
                adj.get(vertex1).add(vertex2);
                adj.get(vertex2).add(vertex1);
            }
        }
        public boolean contains(String vertex){
            return vertices.contains(vertex);
        }

        public int search(String vertex){
            ArrayList<Integer> distances = new ArrayList<Integer>(vertex.length());
            for (int v=0; v<vertex.length(); v++)
            {
                distances.set(v, 1000000); //Temp, should be infinity
            }

        }
    }

    main()
}
