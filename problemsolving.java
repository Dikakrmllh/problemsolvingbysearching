import java.util.*;

class Graph {
    private Map<String, Map<String, Integer>> graph = new HashMap<>();

    public void addEdge(String start, String end, int weight) {
        graph.putIfAbsent(start, new HashMap<>());
        graph.putIfAbsent(end, new HashMap<>());
        graph.get(start).put(end, weight);
        graph.get(end).put(start, weight); // Jika ingin rute dua arah
    }

    public Map<String, Integer> dijkstra(String start) {
        Map<String, Integer> distances = new HashMap<>();
        for (String vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            Map<String, Integer> neighbors = graph.get(current);

            if (neighbors == null) continue;

            for (String neighbor : neighbors.keySet()) {
                int distance = distances.get(current) + neighbors.get(neighbor);
                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }
}

public class RoutingProgram {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Tambahkan edge antar kota dan bobotnya
        graph.addEdge("Kota A", "Kota B", 2);
        graph.addEdge("Kota A", "Kota C", 5);
        graph.addEdge("Kota B", "Kota C", 2);
        graph.addEdge("Kota B", "Kota D", 7);
        graph.addEdge("Kota C", "Kota D", 4);

        String startCity = "Kota A";
        Map<String, Integer> distances = graph.dijkstra(startCity);

        for (String city : distances.keySet()) {
            System.out.println("Jarak dari " + startCity + " ke " + city + " adalah " + distances.get(city));
        }
    }
}
