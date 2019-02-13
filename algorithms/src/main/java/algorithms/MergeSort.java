package algorithms;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MergeSort {
	public static <T extends Comparable<T>> void sort(T[] lst) {
		sort(lst, 0, lst.length - 1);
	}

	public static <T extends Comparable<T>> void sort(T[] lst, int l, int r) {
		if (l >= r) return;
		int m = (r - l) / 2 + l;
		sort(lst, l, m);
		sort(lst, m + 1, l);
		merge(lst, l, m, r);
	}

	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> void merge(T[] lst, int l, int m, int r) {
		int leftLen = m - l + 1, rightLen = r - m;
		Object[] left = new Object[leftLen];
		Object[] right = new Object[rightLen];
		for (int x = 0; x < leftLen; x++) left[x] = lst[l + x];
		for (int x = 0; x < rightLen; x++) right[x] = lst[m + 1 + x];
		int i = 0, j = 0, k = l;
		while(i < leftLen && j < rightLen) {
			T leftVar = (T) left[i], rightVar = (T) right[j];
			if(leftVar.compareTo(rightVar) > 0) { lst[k++] = rightVar; 	j++; }
			else								{ lst[k++] = leftVar; 	i++; }
		}
		while(i < leftLen) 	lst[k++] = (T) left[i++];
		while(j < rightLen) lst[k++] = (T) right[j++];
	}
	
	@Test
	public void testSortingIntegers() {
		Integer [] nums1 = {5,2,6,7,-5,1,3};
		MergeSort.sort(nums1);
		assertArrayEquals(new Integer[]{-5,1,2,3,5,6,7}, nums1);
	}
	
	@Test
	public void testSortingStrings() {
		String [] strings1 = {"abc","d","ef","ht"};
		MergeSort.sort(strings1);
		assertArrayEquals(new String[]{"abc","d","ef","ht"}, strings1);
	}
}
