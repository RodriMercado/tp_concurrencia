package tp;

import java.util.Random; // Para generar números aleatorios

public class SequentialQuickSort {

    public static void main(String[] args) {
        int size = 10_000; // Tamaño del arreglo
        int[] arr = generateRandomArray(size); // Generar un array aleatorio

        SequentialQuickSort sorter = new SequentialQuickSort(); // Creamos instancia para usar el método no estático

        long start = System.currentTimeMillis(); // Tiempo inicial
        sorter.quickSort(arr, 0, arr.length - 1); // Llamamos al método secuencial
        long end = System.currentTimeMillis(); // Tiempo final

        // Print para mostrar el tiempo total 
        System.out.println("Secuencial: " + (end - start) + " ms"); 
    }

    // Método auxiliar para generar array aleatorio
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size]; // Creamos el arreglo
        Random rand = new Random(); // Generar números aleatorios
        
        
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10_000); // Números aleatorios entre 0 y 9999
        }
        return array; // Retornar array
    }
    
    // Metodo secuencial del algoritmo QuickSort
    public void quickSort(int arr[], int begin, int end) {
    	
        if (begin < end) {
        	
        	// Particion para obtener indice del pivote
            int partitionIndex = partition(arr, begin, end); 
            
            // Ordenamiento Recursivo
            quickSort(arr, begin, partitionIndex - 1); // Ordenar a la izquierda
            quickSort(arr, partitionIndex + 1, end); // Ordenar a la derecha
        }
    }

    // Reordenamiento del pivote
    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end]; // Eleccion del ultimo elemento como pivote
        int i = (begin - 1); // Indice de los elementos menores al pivote
        
     // Recorre el subarreglo
        for (int j = begin; j < end; j++) {
        	// Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivot) {
                i++; // Incrementar el índice de los menores
                
                
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

        return i + 1; // Retornar el indice donde quedo el pivote
    }
}
