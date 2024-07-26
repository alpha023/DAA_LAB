import java.util.Arrays;

public class DijkstraAlgorithm {
    // Function to find the vertex with the minimum distance value, from the set of vertices not yet included in the shortest path tree
    private static int minDistance(int[] dist, boolean[] sptSet, int V) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        
        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        
        return minIndex;
    }
    
    // Function to print the constructed distance array
    private static void printSolution(int[] dist, int V) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }
    
    // Function to implement Dijkstra's shortest path algorithm for a graph represented using adjacency matrix representation
    private static void dijkstra(int[][] graph, int src, int V) {
        int[] dist = new int[V]; // The output array. dist[i] will hold the shortest distance from src to i
        
        // sptSet[i] will be true if vertex i is included in the shortest path tree or the shortest distance from src to i is finalized
        boolean[] sptSet = new boolean[V];
        
        // Initialize all distances as INFINITE and stpSet[] as false
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);
        
        // Distance of the source vertex from itself is always 0
        dist[src] = 0;
        
        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed. u is always equal to src in the first iteration.
            int u = minDistance(dist, sptSet, V);
            
            // Mark the picked vertex as processed
            sptSet[u] = true;
            
            // Update dist value of the adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) {
                // Update dist[v] if it is not in sptSet, there is an edge from u to v, and the total weight of the path from src to v through u is smaller than the current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        
        // Print the constructed distance array
        printSolution(dist, V);
    }
    
    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        int[][] graph = {
            {0, 10, 0, 0, 0, 0},
            {10, 0, 5, 0, 0, 0},
            {0, 5, 0, 20, 1, 2},
            {0, 0, 20, 0, 2, 0},
            {0, 0, 1, 2, 0, 3},
            {0, 0, 2, 0, 3, 0}
        };
        
        // Running Dijkstra's algorithm from the source vertex 0
        dijkstra(graph, 0, graph.length);
    }
}