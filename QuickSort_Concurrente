import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ConcurrentQuickSort {

    public static void main(String[] args) {
        int size = 10_000_000; // Tamaño del arreglo
        int[] arr = generateRandomArray(size); // Generar array aleatorio

        ForkJoinPool pool = new ForkJoinPool(); // Pool de hilos para trabajo paralelo

        long start = System.currentTimeMillis();
        pool.invoke(new QuickSortTask(arr, 0, arr.length - 1)); // Ejecuta la concurrencia
        long end = System.currentTimeMillis();

        System.out.println("Concurrente: " + (end - start) + " ms");
    }

    // Recursividad con ForkJoinPool 
    private static class QuickSortTask extends RecursiveAction {
        private final int[] array;
        private final int begin;
        private final int end;

        public QuickSortTask(int[] array, int begin, int end) {
            this.array = array;
            this.begin = begin;
            this.end = end;
        }

        
        protected void compute() {
            if (begin < end) {
                int partitionIndex = partition(array, begin, end);

                // Concurrencia
                QuickSortTask leftTask = new QuickSortTask(array, begin, partitionIndex - 1);
                QuickSortTask rightTask = new QuickSortTask(array, partitionIndex + 1, end);

                // Ejecutar paralelismo
                invokeAll(leftTask, rightTask);
            }
        }

        // Método de partición 
        private int partition(int[] arr, int begin, int end) {
            int pivot = arr[end];
            int i = begin - 1;

            for (int j = begin; j < end; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, end);
            return i + 1;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Método auxiliar para generar array aleatorio
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10_000);
        }
        return array;
    }
}
