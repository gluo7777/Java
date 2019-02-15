package ds;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public abstract class BinaryHeap{
	
	public static class BinaryHeapTest{
		@Test
		public void testMinHeap() {
			BinaryHeap.MinHeap mh = new BinaryHeap.MinHeap(8);
			mh.insert(3,20,30,24,80,37);
			mh.insert(13);
			assertThat(mh.getTree()).containsExactly(3,20,13,24,80,37,30);
			assertThat(new int[] {mh.remove(),mh.remove(),mh.remove()}).containsExactly(3,13,20);
			assertThat(mh.getTree()).containsExactly(24,37,30,80);
		}	
		
		@Test
		public void testMaxHeap() {
			BinaryHeap.MaxHeap mh = new BinaryHeap.MaxHeap(8);
			mh.insert(3,20,30,24,80,37);
			mh.insert(13);
			assertThat(mh.getTree()).containsExactly(80,30,37,3,24,20,13);
			assertThat(new int[] {mh.remove(),mh.remove(),mh.remove()}).containsExactly(80,37,30);
			assertThat(mh.getTree()).containsExactly(24,13,20,3);
		}	
	}

	public static class MinHeap extends BinaryHeap {
		public MinHeap(int capacity) {
			super(capacity);
		}

		@Override
		boolean canSwap(int child, int parent) {
			return this.arr[child] < this.arr[parent];
		}
	}

	public static class MaxHeap extends BinaryHeap {
		public MaxHeap(int capacity) {
			super(capacity);
		}

		@Override
		boolean canSwap(int child, int parent) {
			return this.arr[child] > this.arr[parent];
		}
	}
	
    int [] arr;
    int last;

    public BinaryHeap(int capacity){
        arr = new int[capacity];
        last = -1;
    }

    void insert(int...keys){
        for(int key: keys) insert(key);
    }

    int peak(){ if(last < 0) throw new RuntimeException("Heap is empty."); else return arr[last]; }

    /**
     * O(logn)
     * @return
     */
    int remove(){
        peak();
        int root = arr[0];
        swap(0,last--);
        heapifyDown();
        return root;
    }

    /**
     * O(logn)
     * @param key
     */
    void insert(int key){
        if(++last >= arr.length) doubleCapacity();
        arr[last] = key;
        heapifyUp();
    }

    void heapifyDown(){
        int c = 0;
        while(c < last){
            int left = c * 2 + 1, right = c * 2 + 2;
            if(left > last) break;
            int pos = right > last || this.canSwap(left,right) ? left : right;
            if(this.canSwap(pos,c)){
                swap(c,pos);
                c = pos;
            }else break;
        }
    }

    abstract boolean canSwap(int child, int parent);

    void heapifyUp(){
        int c = last;
        while(c > 0){
            int p = (c - 1) / 2; // parent index
            if(this.canSwap(c,p)) swap(c,p);
            else break;
            c = p;
        }
    }

    void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void doubleCapacity(){
        int [] temp = new int[2 * arr.length];
        for(int i=0; i<arr.length; i++) temp[i] = arr[i];
        arr = temp;
    }

    void print(){ System.out.println(Arrays.toString(this.getTree()));};
    
    int[] getTree() {
    	int [] tree = new int[last + 1];
    	for(int i=0; i<tree.length; i++) tree[i] = arr[i];
    	return tree;
    }
}