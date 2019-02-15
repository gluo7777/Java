package ds.trees;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class BinaryHeapTest{
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
