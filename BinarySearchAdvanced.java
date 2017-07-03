//Student ID: 14040502D
//BinarySearchAdvanced.java

import java.util.*;//for scanning input

public class BinarySearchAdvanced {
    
    //method binarySearch is called to find a target number in a given integer array, 
    //with return value as the index position of the target in the array
    public static int binarySearchAdvanced (int[] arr, int target) {
        
        //initialization of variables
        int front = 1;//store the front position index in the array
        int end = arr.length;//store the tail position index in the array
        int current = 0;//store the current position index in the array (index starts from 1 here)
        int first = 0;//store the first index value if the array had repeated target items
        int last = 0;//store the last index value if the array had repeated target items

        while(front<=end){//while target not found
            
            current = (front+end)/2;//attain new current element position
            
            if(arr[current-1]>target){//target smaller than the current element
                end = current-1;
            }
            
            else if(arr[current-1]<target){//target bigger than the current element
                front = current+1;
            }
            
            else{//target found
                current=current-1;
                first = current;
                last = current;
                int x=0;
                
                //if the element on the left has the same value, set its index position as first
                while(arr[current-(x+1)]==arr[current]){
                    x++;
                    first=current-x;
                    if(first==0){//if the search reaches one end of the array, quit the loop
                        break;
                    }
                }
                
                int y=0;
                
                //if the element on the right has the same value, set its index position as last
                while(arr[current+(y+1)]==arr[current]){
                    y++;
                    last=current+y;
                    if(last==(arr.length-1)){//if the search reaches one end of the array, quit the loop
                        break;
                    }
                }
                
                current = (first+last)/2;//set current to the middle index position
                
                //return found target's (middle) index position, index starts counting from 1 here
                return current+1;
            }
        }
        return -1;//target cannot be found in the given array, return -1 as indicator
    }
    

    //method to print array
    public static void printArray(int[] arr){
        System.out.print("\nArray: ");
        for(int count=0;count<arr.length;count++){
            System.out.print("" + arr[count]);
            if(count!=(arr.length-1)){
                System.out.print(", ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        //5 testing arrays
        int[] arr = {2,2,2,4,4,4,10,10,10,10,10,10};
        int[] arr1 = {10,10,10,10,10,10,10,12,12,12,14};
        int[] arr2 = {10,10,10,10,10,10,10,10,10,10,10,10,10};
        int[] arr3 = {1,2,3,4,5,6,7,8,9,10,10,10,10,11,12,13,14,15};
        int[] arr4 = {1,2,3,4,5,6,7,8,9};

        int[][] arrays = {arr,arr1,arr2,arr3,arr4};

        int target = 0;
        int targetIndex = 0;

        //user input the target to be found here
        System.out.println("Enter the number to be found in the array : ");
        target = keyboard.nextInt();

        //test cases
        //use for loop to test the 5 test cases
        for(int loop=0;loop<arrays.length;loop++){
            printArray(arrays[loop]);
            targetIndex = binarySearchAdvanced(arrays[loop], target);//test arr
            if(targetIndex==-1){//if target cannot be found in the array
                System.out.println("\nTarget " + target + " not found.");//display error message
            }
            else{//target found
                //return the index position of the target in the array (middle index if there are 
                //more than one elements of the same target value in the array)
                System.out.println("\nTarget: " + target + ", (middle) index position (array index starts from 1): " + targetIndex);
            }
        }
    }
}