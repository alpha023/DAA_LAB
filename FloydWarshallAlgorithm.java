public class FloydWarshallAlgorithm {
    public static void main(String[] args) {
        // int[] C=int[]{1,4,6};
        // int C[][]={{0,3,8,999},{999,0,4,11},{999,999,0,7},{4,999,999,0}};
        int C[][]={{0,1,43},{1,0,6},{999,999,0}};
       
        printMatrix(APSP(C, 3));
        // System.out.println("Hello World...");
    }
    public static int[][] APSP(int Cost[][],int N){

        int[][] A=new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j]=Cost[i][j];
            }
        }

        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    A[i][j]=min(A[i][j],A[i][k]+A[k][j]);
                }
            }
        }
        return A;

    }
    public static int min(int x,int y){
        return x<y?x:y;
    }

    public static void printMatrix(int arr[][]){
        System.out.println();
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
