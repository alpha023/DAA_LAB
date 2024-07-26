import java.util.Arrays;

public class TravellingSalespersonProblem {
    // Function to calculate the total cost of the given path
    private static int calculateCost(int[][] graph, int[] path) {
        int totalCost = 0;
        for (int i = 0; i < path.length - 1; i++) {
            totalCost += graph[path[i]][path[i + 1]];
        }
        totalCost += graph[path[path.length - 1]][path[0]]; // Return to the starting point
        return totalCost;
    }

    // Function to generate permutations of the cities and calculate the minimum cost
    private static void tsp(int[][] graph, int[] path, boolean[] visited, int pos, int n, int count, int cost, int[] result, int[] bestPath) {
        if (count == n && graph[path[pos - 1]][path[0]] > 0) {
            int currentCost = cost + graph[path[pos - 1]][path[0]];
            if (currentCost < result[0]) {
                result[0] = currentCost;
                System.arraycopy(path, 0, bestPath, 0, path.length);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[path[pos - 1]][i] > 0) {
                visited[i] = true;
                path[pos] = i;
                tsp(graph, path, visited, pos + 1, n, count + 1, cost + graph[path[pos - 1]][i], result, bestPath);
                visited[i] = false;
            }
        }
    }

    // Function to solve TSP and return both the minimum cost and the path
    public static Result solveTSP(int[][] graph) {
        int n = graph.length;
        int[] path = new int[n];
        int[] bestPath = new int[n];
        boolean[] visited = new boolean[n];
        int[] result = { Integer.MAX_VALUE };

        Arrays.fill(visited, false);
        path[0] = 0;
        visited[0] = true;
        tsp(graph, path, visited, 1, n, 1, 0, result, bestPath);

        return new Result(result[0], bestPath);
    }

    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        // Solving TSP and printing the result
        Result result = solveTSP(graph);
        System.out.println("The minimum cost is: " + result.cost);
        System.out.println("The path is: " + Arrays.toString(result.path));
    }

    // Class to hold the result
    static class Result {
        int cost;
        int[] path;

        Result(int cost, int[] path) {
            this.cost = cost;
            this.path = path;
        }
    }
}