import java.util.*;
public class Graph
{
    HashMap<Integer, Node> nodeLookup = new HashMap<>();

    private Node getNode(int id)
    {
        return nodeLookup.get(id);
    }

    public void addEdge(int source, int destination, int weight)
    {
        if(getNode(source)==null)
            nodeLookup.put(source, new Node(source));
        if(getNode(destination)==null)
            nodeLookup.put(destination, new Node(destination));

        Node a = getNode(source);
        Node b = getNode(destination);

        Edge edge = new Edge(a, b, weight);

        a.edges.add(edge);
        b.edges.add(edge);
    }

    //Prim's algorithm
    //https://www.youtube.com/watch?v=cplfcGZmX7I
    public int minSpanningTreeWeight()
    {
        int totalEdgeWeight = 0;
        HashSet<Node> visited = new HashSet<>();
        PriorityQueue<Edge> edgesToCheck = new PriorityQueue<>();

        //pick any node, and add it to visited
        //add the single smallest-weight unvisited child of all nodes in visited to visited
        //keep doing all that until nodeLookup.size() = visited.size()

        Node current = nodeLookup.get(nodeLookup.keySet().toArray()[0]);
        while(visited.size() < nodeLookup.size())
        {
            visited.add(current);
            for(Edge edge : current.edges)
            {
                edgesToCheck.add(edge);
            }

            Edge shortest = edgesToCheck.poll();
            while(shortest != null && (visited.contains(shortest.a) && visited.contains(shortest.b)))
            {
                shortest = edgesToCheck.poll();
            }

            if(shortest == null)
                break;
            if(!visited.contains(shortest.a))
                current = shortest.a;
            else
                current = shortest.b;
            totalEdgeWeight += shortest.weight;
        }
        return totalEdgeWeight;
    }

    static class Node
    {
        int id;
        LinkedList<Edge> edges = new LinkedList<>();
        private Node(int id)
        {
            this.id = id;
        }
    }

    static class Edge implements Comparable<Edge>
    {
        int weight;
        Node a, b;
        private Edge(Node a, Node b, int weight)
        {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        public int compareTo(Edge otherEdge)
        {
            return this.weight - otherEdge.weight;
        }
    }
}
