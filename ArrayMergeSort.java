package test;

import java.util.Arrays;

public class ArrayMergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[] = {69,10,30,2,16,8,31,22};
		
		mergeSort(data, 0, data.length-1);
		
		System.out.println(Arrays.toString(data));
	}
	
	public static void mergeSort(int[] d, int left, int right) {
		if(left < right) {
			int mid = (left+right)/2;
			
			mergeSort(d, left, mid);
			mergeSort(d, mid+1, right);
					
			merge(d, left, mid, right);		
		}
	}
	
	public static int [] merge(int[] d, int left, int mid, int right) {
		int l[] = new int[mid - left + 1];
		int r[] = new int[right - mid];
		
		for (int i = 0; i < l.length; i++) {
			l[i] = d[left+i];
		}
		
		for (int i = 0; i < r.length; i++) {
			r[i] = d[mid+i+1];
		}
		
		int left_i = 0;
		int right_i = 0;
		int arr_i = left;
		
		while(left_i < l.length && right_i < r.length) {
			if(l[left_i] < r[right_i]) {
				d[arr_i] = l[left_i];
				left_i++;
			}else {
				d[arr_i] = r[right_i];
				right_i++;
			}
			arr_i++;
		}
		
		while(left_i < l.length) {
			d[arr_i] = l[left_i];
			arr_i++;
			left_i++;
		}
		
		while(right_i < r.length) {
			d[arr_i] = r[right_i];
			arr_i++;
			right_i++;
		}
		return d;
	}
}
