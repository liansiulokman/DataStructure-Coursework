package comp2011.lecA;

/*
 * the heap structure
 * warning: this is NOT the heap in memory management.
 * */
public class Heap<T> {
    private HeapNode<T>[] arr;
    int size; 

    @SuppressWarnings("unchecked")
    public Heap(int capacity) {
        arr = new HeapNode[capacity];
        size = 0;
    }
    
    public void swap(int a, int b) {
        HeapNode<T> temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void down(int index) {
        if (size < 2 * index + 1) return;
        int leftChild = index * 2 + 1;
        int rightChild = leftChild + 1;
        int largerChild = leftChild;
        if (rightChild < size && arr[leftChild].key < arr[rightChild].key)
            largerChild = rightChild;
        if (arr[index].key >= arr[largerChild].key) return;
        swap(index, largerChild);
        down(largerChild);
    }
    
    public HeapNode<T> deleteMax() {
        HeapNode<T> root = arr[0];
        arr[0] = arr[--size];
        down(0);
        return root;
    }

    public void up(int index) {
        int parent = (index - 1) / 2;
        if (index == 0 || arr[parent].key > arr[index].key) return;
        swap(index, parent);
        up(parent);
    }
    
    public void insert(HeapNode<T> node) {
        // a more friendly way is to double the size of arr
        if (size == arr.length) {
            System.out.println("oops...overflow");
            return;
        }
        arr[size++] = node;
        up(size - 1);
    }
    
    public void insert(int key, T data) {
        insert(new HeapNode<T>(key, data));
    }

    // as said, using a key variable is unnatural.
    // but it saves us of trouble of using comparator.
    public void heapSort(int[] keys, T[] data) {
        Heap<T> heap = new Heap<T>(keys.length);
        for (int i = 0; i < keys.length; i++)
            heap.insert(keys[i], data[i]);
        for (int i = 0; i <keys.length; i++) {
            HeapNode<T> node = heap.deleteMax();
            keys[i] = node.key;
            data[i] = node.data;
        }
    }
    
    public void inplaceHeapSort(int[] keys, T[] data){
    	Heap<T> heap = new Heap<T>(keys.length);
    	for (int i = 0; i < keys.length; i++){
            heap.insert(keys[i], data[i]);
    	}
    	for(int x = 0; x<keys.length;x++){
    		HeapNode<T> node = heap.deleteMax();
    		int pos=0;
    		for(int z=0;z<keys.length;z++){
    			if(keys[z]==node.key){
    				pos=z;
    			}
    		}
    		//swap z,pos
    		int temp = keys[x];
    		keys[x] = keys[pos];
    		keys[pos] = temp;
    		T t = data[x];
    		data[x] = data[pos];
    		data[pos] = t;
    	}
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) 
            sb.append(arr[i]).append('\n');
        return sb.toString();
    }
}

class HeapNode<T> {
    // The explicit use of variable key is non-standard.
    // We use it here to avoid the use of Comparator:
    // http://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
    int key; 
    T data;
    
    public HeapNode(int key, T data) {
        this.key = key; 
        this.data = data;
    }

    public String toString() {
        return key + ": " + data;
    }
}
