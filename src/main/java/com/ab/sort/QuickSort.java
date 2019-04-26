package com.ab.sort;

public class QuickSort {
	
	public void quickSort(String[] strDate, int left, int right) {
		String middle, tempDate;
		int i, j;
		i = left;
		j = right;
		middle = strDate[(i + j) / 2];
		do {
			while (strDate[i].compareTo(middle) < 0 && i < right)
				i++; // 找出左边比中间值大的数
			while (strDate[j].compareTo(middle) > 0 && j > left)
				j--; // 找出右边比中间值小的数
			if (i <= j) { // 将左边大的数和右边小的数进行替换
				tempDate = strDate[i];
				strDate[i] = strDate[j];
				strDate[j] = tempDate;
				i++;
				j--;
			}
		} while (i <= j); // 当两者交错时停止
		if (i < right) {
			quickSort(strDate, i, right);
		}
		if (j > left) {
			quickSort(strDate, left, j);
		}
	}

	public static void main(String[] args) {
		String[] strVoid = new String[] { "11", "66", "22", "0", "55", "22", "0", "32" };
		QuickSort sort = new QuickSort();
		sort.quickSort(strVoid, 0, strVoid.length - 1);
		for (int i = 0; i < strVoid.length; i++) {
			System.out.print(strVoid[i] + " ");
		}

		System.out.println("              ");
		int[] arr = {9,4,6,3,8};
		quickSort2(arr, 0, arr.length);
		for (int a: arr) {
			System.out.print(a + "  ");
		}
	}

	private static void quickSort2(int[] arr, int left, int right){
		if(left < right){
			int index = getIndex(arr, left, right-1);
			quickSort2(arr, 0, index);
			quickSort2(arr, index, right-1);
		}
	}

	private static int getIndex(int[] arr, int left, int right){

		int temp = arr[left];
		while(left < right){
			while(arr[right] > temp && left < right){
				right--;
			}
			arr[left] = arr[right];
			while(arr[left] < temp && left < right){
				left++;
			}
			arr[right] = arr[left];
		}
		arr[left] = temp;
		return left;
	}
}