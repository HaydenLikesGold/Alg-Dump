import java.util.*;

/**
 * Created by hgoldstien on 11/23/14.
 */
public class Spectrum {
    public static class Graph {
        private HashMap<String, Integer> vertices;
        private HashMap<String, ArrayList<String>> adj;
        public void init(){
            vertices = new HashMap<String, Integer>();
            adj = new HashMap<String, ArrayList<String>>();
        }
        public void addVertex(String vertex) {
            if (!vertices.containsKey(vertex)) {
                vertices.put(vertex, vertices.size());
                adj.put(vertex, new ArrayList<String>());
            }
        }

        public void addEdge(String vertex1, String vertex2) {
            if (!vertices.containsKey(vertex1)) addVertex(vertex1);
            if (!vertices.containsKey(vertex2)) addVertex(vertex2);
            if (!(vertex1.equals(vertex2)) && (!adj.get(vertex1).contains(vertex2))) {
                adj.get(vertex1).add(vertex2);
                adj.get(vertex2).add(vertex1);
            }
        }

        public boolean contains(String vertex) {
            return vertices.containsKey(vertex);
        }

        public HashMap<String, Integer> bredthFirstSearch(String sourceVertex)
        {
            HashMap<String, Integer> distances = new HashMap<String, Integer>();
            for (String vertex : vertices.keySet())
            {
                distances.put(vertex, 100000);
            }
            distances.put(sourceVertex, 0);
            Queue<String> queue = new ArrayDeque<String>();
            queue.add(sourceVertex);
            while(queue.size() > 0)
            {
                String vertexInQuestion = queue.poll();
                for (String x : adj.get(vertexInQuestion)){
                    if (distances.get(x) == 100000)
                    {
                        distances.put(x, distances.get(vertexInQuestion) +1);
                        queue.add(x);
                    }
                }
            }
            return distances;
        }

        public HashMap<Integer, Integer> connections(String from)
        {
            Integer vertex = vertices.get(from);
            if (adj.get(from).size() == 0){
                return new HashMap<Integer, Integer>();
            }
            HashMap<String,Integer> mapping = bredthFirstSearch(from); //THIS IS WRONG
            HashMap<Integer, Integer> connections = new HashMap<Integer, Integer>();

            for (Integer distance : mapping.values()){
                if (0 < distance  &&  distance < 100000){
                    int hopLevel = distance -1;
                    if (!connections.containsKey(hopLevel)) connections.put(hopLevel, 1);
                    else {
                        Integer temp = connections.get(hopLevel);
                        connections.put(hopLevel, temp+1);
                    }
                }
            }
            return connections;
        }

        public int hops(String vertex1, String vertex2)
        {
            Integer v1 = vertices.get(vertex1);
            Integer v2 = vertices.get(vertex2);
            HashMap<String,Integer> mapping = bredthFirstSearch(vertex1); //THIS IS WRONG
            if (mapping.containsKey(vertex2)){
                if (mapping.get(vertex2) == 100000){
                    return 100000;
                }
                else{
                    return mapping.get(vertex2) - 1;
                }
            }
            return 0;
        }
    }
    public static void main(String[] args){
        Graph graph = new Graph();
        graph.init();
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int caseNum = 0;
        if (!line.equals("")) {
            caseNum = 1;
            System.out.println("Case "+caseNum+":");
        }
        while (!line.equals("")) {
            String[] split = line.split("\\s+");
            if (split[0].equals("add")) {
                String vertex1 = split[1];
                if (split.length == 2) {
                    graph.addVertex(vertex1);
                } else {
                    String vertex2 = split[2];
                    graph.addEdge(vertex1, vertex2);
                }
            }
            if (split[0].equals("connections")) {
                String vertex1 = split[1];
                if (!graph.contains(vertex1)) {
                    System.out.println("target does not exist");
                } else {
                    HashMap<Integer, Integer> temp = graph.connections(vertex1);
                    if (temp.size() == 0) {
                        System.out.println("no connections");
                    } else {
                        for (int i : temp.keySet()) {
                            System.out.println(i + ": " + temp.get(i));
                        }
                    }
                }
            }
            if (split[0].equals("associated")) {
                String vertex1 = split[1];
                String vertex2 = split[2];
                if (!graph.contains(vertex1) || !graph.contains(vertex2)) {
                    System.out.println("target does not exist");
                } else {
                    int degree = graph.hops(vertex1, vertex2);
                    if (degree == 100000) {
                        System.out.println("no");
                    } else {
                        System.out.println("yes: " + degree);
                    }
                }
            }
            if (split[0] == "reset") {
                System.out.println("----------");
                caseNum++;
                graph = new Graph();
                System.out.println("Case " + caseNum + ":");
            }
            if (sc.hasNextLine()){
                line = sc.nextLine();
            } else{line ="";}
        }
        System.out.println("----------");
    }
}
