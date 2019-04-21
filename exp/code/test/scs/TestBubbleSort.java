package lab4;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBubbleSort {
	private BubbleSort bs;
	private int[]arr= new int[]{1,6,2,2,5};
	private int[]ar= new int[]{1,2,2,5,6};

	@Test
	public void test() {
		bs=new BubbleSort();
		assertArrayEquals(ar,bs.BubbleSort(arr));
	}

}
