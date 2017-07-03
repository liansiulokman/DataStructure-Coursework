//Student ID: 14040502D
//BinarySearch.java

import java.util.*;//for scanning input

public class BinarySearch {
    
    //method binarySearch is called to find a target number in a given integer array,
    //with return value as the index position of the target in the array
    public static int binarySearch (int[] arr, int target) {
        int front = 1;//store the front position index in the array
        int end = arr.length;//store the tail position index in the array
        int current = 0;//store the current position index in the array (index starts from 1 here)

        while(front<=end){//while target not found
            current = (front+end)/2;//attain new current element position
            if(arr[current-1]>target){//target smaller than the current element
                end = current-1;
            }
            else if(arr[current-1]<target){//target bigger than the current element
                front = current+1;
            }
            else{//target found
                return current-1;//return found target's index position
            }
        }
        return -1;
    }
    
  //method to print array
    public static void printArray(int[] arr){
        System.out.print("Array:  ");
        for(int count=0;count<arr.length;count++){
            System.out.print("" + arr[count]);
            if(count!=(arr.length-1)){
                System.out.print(", ");
            }
        }
    }
    
    public static void main(String[] args) {
        
        int[] arr1 = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] arr2 = {2,4,6,8,10,12,14,16,18,20};
        int[] arr3 = {11,22,33,44,55,66,77,88,99};
        
        int[][] arrays = {arr1,arr2,arr3};
        
        //test cases
        int index = 0;
        for(int x=0;x<arrays.length;x++){
            System.out.println("Test case #" + (x+1) + "\nTarget: 10");
            printArray(arrays[x]);
            
            //call binarySearch method to perform binary search on the array
            index = binarySearch(arrays[x], 10);
            
            if(index<0){
                //print the message saying that the target item cannot be found in the given array
                System.out.println("\nResult: Target cannot be found in the given array.\n");
            }

            else{
                System.out.println("\nResult: Target found. Index (array index starts from 0): "+ index);
            }
            System.out.println();
        }
    }
}