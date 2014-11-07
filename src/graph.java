import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
        if (graphMatrix.containsKey(name)){return;}
        graphMatrix.put(name, new ArrayList<Set<String>>());
    }

    private static void addLink(String from, String to, int hopSize)
    {
        ArrayList<Set<String>> fromData = graphMatrix.get(from);
        try{
            fromData.get(hopSize);
        } catch (IndexOutOfBoundsException ex)
        {
            Set<String> temp = new HashSet<String>();
            temp.add(to);
            fromData.add(hopSize, temp);
            graphMatrix.replace(from, fromData);
            System.out.println("Added "+to+" to "+from+" (With out of bounds)");
            return;
        }
        fromData.get(hopSize).add(to);
        System.out.println("Added "+to+" to "+from);
        graphMatrix.replace(from, fromData);
    }
    private static void addHop(String x, String y, int hopSize)
    {
        System.out.println("AddHop: " + x + " " + y + " " + hopSize);
        addLink(x, y, hopSize);
        addLink(y, x, hopSize);
    }
    public static void addEdge(String x, String y)
    {
        addVertex(x);
        addVertex(y);
        addHop(x, y, 0);
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
