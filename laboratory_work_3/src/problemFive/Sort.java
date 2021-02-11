package problemFive;

public class Sort {
	
	static <E> void swap (E[] array, int i, int j) {
		E a = array[i];
		array[i] = array[j];
		array[j] = a;
		
	}
	
	static <E extends Comparable<Object>> void bubbleSort(E[] array) {
	    for (int i = 0; i<array.length; i++) {
	    	for (int j = 0; j<array.length; j++) {
	    		if (array[i].compareTo(array[j]) == -1)
	    			swap(array, i, j);
	    	}
	    }
	}
	
	static <E extends Comparable<Object>> void quickSort(E[] array) {
		quickSortImplementation(array, 0, array.length -1);
	}
	
	static <E extends Comparable<Object>> void quickSortImplementation(E[] array, int l, int r) {
		if (l < r) {
			int pivot = partition(array, l, r);
			quickSortImplementation(array, l, pivot - 1);
			quickSortImplementation(array, pivot + 1, r);
		}
	}
	
	static <E extends Comparable<Object>> int partition(E[] array, int l, int r) {
		int mini = l;
		
		for (int i = l; i < r; i++) {
			if (array[i].compareTo(array[r]) == -1) {
				swap(array, i, mini);
				mini++;
			}
		}
		swap(array, mini, r);
		return mini;	
	}
}
