import java.util.Random;

public class SequentialQuickSort {

    public static void main(String[] args) {
        int size = 10_000; // Tamaño del arreglo
        int[] arr = generateRandomArray(size); // Generar un array aleatorio

        SequentialQuickSort sorter = new SequentialQuickSort(); // Creamos instancia para usar el método no estático

        long start = System.currentTimeMillis();
        sorter.quickSort(arr, 0, arr.length - 1); // Llamamos al método secuencial
        long end = System.currentTimeMillis();

        System.out.println("Secuencial: " + (end - start) + " ms"); // Print para calcular los MS
    }

    // Método auxiliar para generar array aleatorio
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10_000); // Números aleatorios entre 0 y 9999
        }
        return array;
    }
    
    // Metodo secuencial
    public void quickSort(int arr[], int begin, int end) {
    	
        if (begin < end) {
        	
        	// Particion
            int partitionIndex = partition(arr, begin, end); 
            
            // Ordenamiento Recursivo
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    // Reordenamiento del pivote
    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        
     // Recorre el subarreglo
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
             // Intercambia arr[i] y arr[j]
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        
        // Coloca el pivote en su posición final ordenada
        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }
}
