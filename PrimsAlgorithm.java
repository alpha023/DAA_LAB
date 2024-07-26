import java.util.Arrays;

public class PrimsAlgorithm {
    private static final int INF = Integer.MAX_VALUE; // Representation of no connection
    
    // Function to find the vertex with the minimum key value, from the set of vertices not yet included in the MST
    private static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = INF, minIndex = -1;
        
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        
        return minIndex;
    }
    
    // Function to print the constructed MST
    private static void printMST(int[] parent, int[][] graph, int V) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }
    
    // Function to construct and print the MST for a graph represented using adjacency matrix representation
    private static void primMST(int[][] graph, int V) {
        int[] parent = new int[V]; // Array to store the constructed MST
        int[] key = new int[V];    // Key values used to pick the minimum weight edge in cut
        boolean[] mstSet = new boolean[V]; // To represent the set of vertices included in MST
        
        // Initialize all keys as infinite
        Arrays.fill(key, INF);
        
        // Always include the first 1st vertex in MST
        key[0] = 0;     // Make key 0 so that this vertex is picked as the first vertex
        parent[0] = -1; // First node is always the root of MST
        
        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum key vertex from the set of vertices not yet included in MST
            int u = minKey(key, mstSet, V);
            
            // Add the picked vertex to the MST Set
            mstSet[u] = true;
            
            // Update key value and parent index of the adjacent vertices of the picked vertex
            // Consider only those vertices which are not yet included in MST
            for (int v = 0; v < V; v++) {
                // graph[u][v] is non-zero only for adjacent vertices of u
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        
        // Print the constructed MST
        printMST(parent, graph, V);
    }
    
    public static void main(String[] args) {
        // Number of vertices in the graph
        int V = 5;
        
        // Example graph represented as an adjacency matrix
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };
        
        // Running Prim's algorithm to find and print the MST
        primMST(graph, V);
    }
}