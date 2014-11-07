import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by hgoldstien on 11/4/14.
 */
public class graph {
    private static HashMap<String, ArrayList<Set<String>>> graphMatrix;

    public static void init()
    {
        graphMatrix = new HashMap<String, ArrayList<Set<String>>>();
    }

    public static void addVertex(String name)
    {
        ArrayList<Set<String>> dataHolding = new ArrayList<Set<String>>();
        if (graphMatrix.containsKey(name)){return;}
        graphMatrix.put(name, dataHolding);
    }

    private static void oneWayLink(String from, String to, int hopSize)
    {
        ArrayList<Set<String>> fromData = graphMatrix.get(from);
        Set<String> newPathArray = fromData.get(hopSize);
        if (newPathArray.contains(to)){return;}
        newPathArray.add(to);
        fromData.set(hopSize, newPathArray);
        graphMatrix.replace(from, fromData);
    }
    private static void addLinkInfo(String x, String y, int hopSize)
    {
        oneWayLink(x, y, hopSize);
        oneWayLink(y, x, hopSize);
    }
    public static void addEdge(String x, String y)
    {
        addVertex(x);
        addVertex(y);
        addLinkInfo(x, y, 0);
    }

    private static void printVertices()
    {
        System.out.println("Printing Matrix:");
        for(String x : graphMatrix.keySet())
        {
            System.out.print(x + ": ");
            Integer i = 0;
            for (Set<String> y : graphMatrix.get(x))
            {
                System.out.print(i.toString()+": "+y+" ");
                i++;
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        init();
        addEdge("A", "B");
        addEdge("B", "C");
        printVertices();
        System.out.println("End of Program");
    }
}
