import java.util.*;

/**
 * Created by hgoldstien on 11/23/14.
 */
public class Spectrum {
    public static class Graph {
        private ArrayList<String> vertices;
        private HashMap<String, ArrayList<String>> edges;
        
        public void init(){
            vertices = new ArrayList<String>();
            edges = new HashMap<String, ArrayList<String>>();
        }

        public void addVertex(String vertex) {
            if (!vertices.contains(vertex)) {
                vertices.add(vertex);
                edges.put(vertex, new ArrayList<String>());
            }
        }

        public void addEdge(String vertex1, String vertex2) {
            if (!vertices.contains(vertex1)) addVertex(vertex1);
            if (!vertices.contains(vertex2)) addVertex(vertex2);
            if (!(vertex1.equals(vertex2)) && (!edges.get(vertex1).contains(vertex2))) {
                edges.get(vertex1).add(vertex2);
                edges.get(vertex2).add(vertex1);
            }
        }

        public boolean hasVertex(String vertex) {
            return vertices.contains(vertex);
        }

        public HashMap<String, Integer> bredthFirstSearch(String sourceVertex)
        {
            HashMap<String, Integer> distances = new HashMap<String, Integer>();
            initDistanceHashMap(sourceVertex, distances);
            Queue<String> queue = new ArrayDeque<String>();
            queue.add(sourceVertex);
            workThroughQueue(distances, queue);
            return distances;
        }

        private void workThroughQueue(HashMap<String, Integer> distances, Queue<String> queue) {
            while(queue.size() > 0)
            {
                String vertexInQuestion = queue.poll();
                for (String x : edges.get(vertexInQuestion)){
                    if (distances.get(x) == 100000)
                    {
                        distances.put(x, distances.get(vertexInQuestion) +1);
                        queue.add(x);
                    }
                }
            }
        }

        private void initDistanceHashMap(String sourceVertex, HashMap<String, Integer> distances) {
            for (String vertex : vertices)
            {
                distances.put(vertex, 100000);
            }
            distances.put(sourceVertex, 0);
        }

        public HashMap<Integer, Integer> connections(String from)
        {
            if (edges.get(from).size() == 0){
                return new HashMap<Integer, Integer>();
            }
            HashMap<String,Integer> mapping = bredthFirstSearch(from);
            HashMap<Integer, Integer> connections = new HashMap<Integer, Integer>();

            generateConnections(mapping, connections);
            return connections;
        }

        private void generateConnections(HashMap<String, Integer> mapping, HashMap<Integer, Integer> connections) {
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
        }

        public int hops(String vertex1, String vertex2)
        {
            HashMap<String,Integer> mapping = bredthFirstSearch(vertex1);
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
                callAddVertex(graph, split);
            }
            if (split[0].equals("connections")) {
                callConnections(graph, split[1]);
            }
            if (split[0].equals("associated")) {
                findAssociatedVertices(graph, split);
            }
            if (split[0].equals("reset")) {
                System.out.println("----------");
                caseNum++;
                graph = new Graph();
                graph.init();
                System.out.println("Case " + caseNum + ":");
            }
            if (sc.hasNextLine()){
                line = sc.nextLine();
            } else{line ="";}
        }
        System.out.println("----------");
    }

    private static void callAddVertex(Graph graph, String[] split) {
        String vertex1 = split[1];
        if (split.length == 2) {
            graph.addVertex(vertex1);
        } else {
            String vertex2 = split[2];
            graph.addEdge(vertex1, vertex2);
        }
    }

    private static void findAssociatedVertices(Graph graph, String[] split) {
        String vertex1 = split[1];
        String vertex2 = split[2];
        if (!graph.hasVertex(vertex1) || !graph.hasVertex(vertex2)) {
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

    private static void callConnections(Graph graph, String s) {
        String vertex1 = s;
        if (!graph.hasVertex(vertex1)) {
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
}
