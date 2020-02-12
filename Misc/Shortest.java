
/**
 * Shortest
 */
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

class Vertex {
    String name;
    List<Edge> edges;
    Vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }
}

class Edge {
    Vertex from;
    Vertex to;
    int distance;
    Edge(Vertex from, Vertex to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}

class Graph {
    Map<String, Vertex> vertexMap;
    Graph() {
        this.vertexMap = new HashMap<>();
    }
    void addVertex(String name) {
        this.vertexMap.put(name, new Vertex(name));
    }
    void addEdge(String from, String to, int distance) {
        Vertex fromV = this.vertexMap.get(from);
        Vertex toV = this.vertexMap.get(to);
        fromV.edges.add(new Edge(fromV, toV, distance));
        toV.edges.add(new Edge(toV, fromV, distance));
    }
    Vertex getVertex(String name) {
        return this.vertexMap.get(name);
    }
    Collection<Vertex> getVertices() {
        return this.vertexMap.values();
    }
}

public class Shortest {
    public static LinkedList<Vertex> getShortest(String from, String to, Graph g) {
        Map<String, Integer> distanceMap = new HashMap<>();
        Map<String, Vertex> previousMap = new HashMap<>();
        for (Vertex v : g.getVertices()) {
            distanceMap.put(v.name, Integer.MAX_VALUE);
        }

        Set<String> visited = new HashSet<>();
        int numVertices = g.getVertices().size();
        distanceMap.put(from, 0);
        while (visited.size() < numVertices) {
            Vertex v = getShortest(distanceMap, visited, g);
            for (Edge e : v.edges) {
                if (visited.contains(e.to.name)) {
                    continue;
                }
                int distance = distanceMap.get(e.from.name) + e.distance;
                if (distanceMap.get(e.to.name) > distance) {
                    distanceMap.put(e.to.name, distance);
                    previousMap.put(e.to.name, e.from);
                }
            }
            visited.add(v.name);
        }

        LinkedList<Vertex> path = new LinkedList<>();
        for (Vertex v = g.getVertex(to); v != null; v = previousMap.get(v.name)) {
            path.addFirst(v);
        }
        return path;
    }

    private static Vertex getShortest(Map<String, Integer> distanceMap, Set<String> visited, Graph g) {
        int minDistance = Integer.MAX_VALUE;
        Vertex minVertex = null;
        for (Map.Entry<String,Integer> entry : distanceMap.entrySet()) {
            if (visited.contains(entry.getKey())) {
                continue;
            }
            if (minDistance > entry.getValue()) {
                minDistance = entry.getValue();
                minVertex = g.getVertex(entry.getKey());
            }
        }
        return minVertex;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addEdge("A", "B", 6);
        g.addEdge("A", "D", 1);
        g.addEdge("B", "D", 2);
        g.addEdge("B", "E", 2);
        g.addEdge("B", "C", 5);
        g.addEdge("E", "D", 1);
        g.addEdge("E", "C", 5);
        String path = getShortest("A", "C", g)
            .stream()
            .map(v -> v.name)
            .collect(Collectors.joining(" -> "));
        System.out.println(path);
    }
}