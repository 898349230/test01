package com.ab.sort;

import java.util.Arrays;

public class erfenfa {
	public static void main(String[] args) {
		int[] arr = {3,5,2,7,4,8,66,1};
		find(arr,1);
	}
	
	public static void find(int[] arr,int search){
		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		int start = 0;
		int end = arr.length;
		for(int i=0;i< arr.length/2;i++){
			int index = (start+end)/2;
			if(arr[index]>search){
				end = index; 
				continue;
			}else if(arr[index]<search){
				start = index;
				continue;
			}else{
				System.out.println(index);
				break;
			}
		}
	}
}
