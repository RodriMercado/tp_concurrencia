import java.util.Random; // Para generar números aleatorios
import java.util.concurrent.ForkJoinPool; // Pool de hilos para tareas paralelas
import java.util.concurrent.RecursiveAction; // Clase base para tareas que no retornan valor

public class ConcurrentQuickSort {

    public static void main(String[] args) {
        int size = 100_000; // Tamaño del arreglo
        int[] arr = generateRandomArray(size); // Generar array aleatorio

        ForkJoinPool pool = new ForkJoinPool(); // Pool de hilos para la concurrencia

        long start = System.currentTimeMillis(); // Guarda el tiempo inicial
        pool.invoke(new QuickSortTask(arr, 0, arr.length - 1)); // Ejecuta la concurrencia
        long end = System.currentTimeMillis(); // Guarda el tiempo final
        
        // Print para mostrar el tiempo total 
        System.out.println("Concurrente: " + (end - start) + " ms");
    }

    // Recursividad con ForkJoinPool 
    private static class QuickSortTask extends RecursiveAction {
    	private static final long serialVersionUID = 1L;
    	
    	// Atributos
        private final int[] array; // Arreglo a ordernar
        private final int begin; // Indice inicial
        private final int end; // Indice Final
 
        // Constructor para inicializar los atributos
        public QuickSortTask(int[] array, int begin, int end) {
            this.array = array;
            this.begin = begin;
            this.end = end;
            
        }

        // Ejecucion del argoritmo de ordenamiento
        @Override
        protected void compute() {
            if (begin < end) {
                int partitionIndex = partition(array, begin, end);

                // Concurrencia
                QuickSortTask leftTask = new QuickSortTask(array, begin, partitionIndex - 1);
                QuickSortTask rightTask = new QuickSortTask(array, partitionIndex + 1, end);

                // Ejecucion de 2 tareas al mismo tiempo (Concurrencia)
                invokeAll(leftTask, rightTask);
            }
        }

        // Método de partición 
        private int partition(int[] arr, int begin, int end) {
            int pivot = arr[end]; // Eleccion del pivote
            int i = begin - 1; // Indice con elemenos menores al pivote

         // Recorrer el subarreglo
            for (int j = begin; j < end; j++) {
            	// Si el elemento es menor o igual al pivote
                if (arr[j] <= pivot) {
                    i++; // Incrementamos índice
                    swap(arr, i, j); // Intercambiamos elementos
                }
            }
         // Intercambiar el pivote con el primer elemento mayor
            swap(arr, i + 1, end);
            return i + 1; // Devolvemos la nueva posición del pivote
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Método auxiliar para generar array aleatorio
    public static int[] generateRandomArray(int size) {
    	
        int[] array = new int[size]; // Crear array con el tamaño asignado
        Random rand = new Random(); // Generar números aleatorios
        
        // Llenar el array con numeros aleatorios
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10_000);
        }
        return array; // Devolver el arreglo generado
    }
}
