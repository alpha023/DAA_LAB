public class Heaps{
    public static void main(String[] args) {

        int arr[]={98,34,12,101,321};
        System.out.println();
        printArray(arr);
        heapsort(arr);
        System.out.println();
        printArray(arr);
        
    }

    public static void heapsort(int arr[]){
        BuildMaxHeap(arr);
        System.out.println();
        printArray(arr);
        int end=arr.length-1;
        for(int i=end;i>0;i--){
            System.out.println("i = "+i);
            printArray(arr);
            swap(arr,0,i);
            heapify(arr, 0, i);
        }
        if(arr[0]>arr[1])swap(arr, 0, 1);
        
    }
    public static void heapify(int arr[],int id,int end){

        int left =2*id+1;
        int right=2*id+2;
        int max=left;
        if(left<end && right<end){
            max=arr[left]>arr[right]?left:right;
            if(arr[id]<arr[max]){
                swap(arr,id,max);
                heapify(arr,max,end);
            }
        }

    }

    public static void BuildMaxHeap(int arr[]){
        int size=arr.length;
        for(int i=(int)(size/2-1);i>=0;i--){
            heapify(arr,i,size);
        }
    }

    //SWAP FUNCTION
    public static void swap(int arr[],int idx1,int idx2){
        int temp=arr[idx1];
        arr[idx1]=arr[idx2];
        arr[idx2]=temp;
    }

    public static void printArray(int arr[]){
        System.out.println();
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" -> ");
        }
    }
}