package com.ab.sort;

import java.util.Arrays;

public class Bubble {
	public static void main(String[] args) {
		int[] arr = {1,2,5,8,5,3,2,5,6};
		System.out.println(Arrays.toString(arr));
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void sort(int[] arr){
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-1-i;j++){
				if(arr[j]>arr[j+1]){
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
}
