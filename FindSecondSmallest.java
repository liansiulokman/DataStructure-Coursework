//Student ID: 14040502D
//findSecondSmallest.java

/*
This algorithm requires at most n + log(n) - 2 comparisons to find the second smallest number from a given array.
The idea of the algorithm is as follows:
    Using the divide and conquer method to find the smallest number used the n-1 comparisons. 
    E.g. 8 elements requires at most 7(n-1) comparisons to find the smallest number: 
            Number of levels are determined by log(n) (log with base2), i.e. log(8) = 3 levels
            Number of comparisons: 4(first level) + 2(second level) + 1(last level)
            
            1  2  3  4  5  6  7  8            8 elements initial
             \/    \/    \/    \/             4(n/2) comparisons at first level
              1     3     5     7             4 elements left
                 \/          \/               2(n/2) comparisons at second level
                  1           5               2 elements left
                        \/                    1(n/2) comparison at last level
                         1                    1 element left (the smallest number)
            
        In order to find the second smallest number in the array, I have to find the potential second smallest number which may be missed in the level comparisons.
        The potential second smallest number in the above case will be 5(last level), 3(second level), and 2(first level).
        They are all being compared with the smallest number before. Therefore, the number of potential numbers should be the same as the number of levels, which is log(n).
        After getting the 3(log(n)) potential numbers, comparisons should be performed in order to find the smallest among them, and that will be the second smallest number.
        The 3 numbers(5,3,2) requires n-1 comparisons for finding the smallest number:
            Assume 5 is the smallest, then compare 5 with 3 and 2, and find the smallest number.
        The number of comparisons used here is 2, which is log(n)-1 (3-1). 
        
        Therefore, the total number of comparisons required to find the second smallest number by divide and conquer is:
            n-1 + log(n)-1
          = n + log(n) - 1 - 1
          = n + log(n) - 2
          By at most n + log(n) - 2 comparisons, we can find the second smallest number from a given array.
*/

import java.util.*;
import java.lang.Math;


public class FindSecondSmallest {
    
    static int comparisons=0;

    //method to divide the problem into subproblems, then call another method to solve the subproblems
    public static int divideAndConquer (int[] arr) {
        
        if(arr.length==0){//check if given array is empty
            return -1;//return -1 as indicator
        }
        
        //Divide into smaller subproblems
        //initialization of variables and arrays
        int level = (int) Math.ceil((Math.log(arr.length))/Math.log(2));//calculate the number of comparison levels
        int currentLevel = 0;//store the value of current level, level starts from 0
        boolean odd = false;//variable to identify whether the number of groups in a level for comparisons is odd or even
        
        //create a temp integer array to store the smaller number of each groups in each level
        int[] smallerTemp = new int[arr.length];
        
        //create a temp integer array to store the index position of the numbers in smallerTemp
        int[] groupIndex = new int[arr.length];
        
        //a 2-D array storing the smaller number of each group in each level for finding the second smallest at later stage
        int[][] levelSmallest = new int[level][arr.length/2];
        
        int result = -1;//variable storing the result, i.e. the second smallest number returned from the method called
        
        
        
        for(int i = 0; i<arr.length; i++){//insert the index position of all elements in the array into groupIndex
            groupIndex[i]=i;
        }
        
        if(arr.length%2!=0){//set variable odd as true if the number of groups is an odd number
            odd = true;
        }

        if(arr.length>1){//if the given array has more than 1 elements, call method to find the second smallest number in the array
            //store the returned value at variable result
            result = findSmallest(arr.length, arr, groupIndex, levelSmallest, odd, level, currentLevel, arr);
        }
        
        return result;//return the result to main() for printing, if the array has less than 2 elements, return -1 as indicator
    }

    //method to find the second smallest number after finding the smallest number in the given array
    public static int findSecondSmallest(int[][] levelSmallest, int[] groupIndex, int smallest, int level, int smallestIndex, int[] arr){
        int secondSmallest=0;//create a variable secondSmallest for storing the result 
        int[] temp = new int[level];//create an integer array for storing the potential second smallest number from the array

        
        for(int x=level-1;x>=0;x--){//loop over all the array of each level
            
            for(int z=0;z<levelSmallest[x].length;z++){//loop over each elements in the array

                //if the element is the same of the found smallest number in the previous step, run this part
                if(arr[levelSmallest[x][z]]==arr[smallestIndex]){
                    
                    if(z%2==0){//if the found element's index position is even
                        //get its next element and stores in temp array as it maybe the potential second smallest number
                        temp[x]=arr[levelSmallest[x][z+1]];
                    }
                    
                    else{//if the found element's index position is odd
                        //gets its previous element and stores in the temp array as it maybe the potential second smallest number
                        temp[x]=arr[levelSmallest[x][z-1]];
                    }
                    
                    break;//once the potential second smallest number is found, break the current for loop and jump to the next level searching
                }
            }
        }

        //compare temp's items to get the smallest number in temp, i.e. the second smallest number in the given array
        secondSmallest=temp[0];//set the first element in temp as the second smallest number for comparison with the other elements in temp
        for(int n=1;n<temp.length;n++){//loop n-1 times for comparisons, where n is the number of elements in temp
            comparisons++;//increment on each comparison
            if(temp[n]<secondSmallest){//compare if the element is smaller than secondSmallest
                secondSmallest=temp[n];//if smaller than secondSmallest, assign its value to variable secondSmallest
            }
        }
        return secondSmallest;//return the result
    }

    //method to find the smllaest number using n-1 comparisons, where n is the number of elements of the given array
    public static int findSmallest(int groups, int[] smallerTemp, int[] groupIndex, int[][] levelSmallest, boolean odd, int level, int currentLevel, int[] arr){
        
        if(currentLevel==level){//if current level exceeds the value of level, i.e. the smallest number is found
            //call the other method to find the second smallest number
            return findSecondSmallest(levelSmallest, groupIndex, smallerTemp[0], level, groupIndex[0], arr);
        }

        else{//the last level's comparisons not yet completed
            
            levelSmallest[currentLevel]=groupIndex;//copy the updated group index from last level's comparisons into levelSmallest
            groups = (groups/2)+(groups%2);//update the value of variable groups (the number of comparisons to be performed in the current level)
            int[] temp = new int[groups];//create an integer array temp to store the updated smaller numbers after comparison on each group
            int[] tempGroupIndex = new int[groups];//create an integer array tempGroupIndex to store the index position of each elements in temp          
            
            //update the value of odd as well as the value of variable groups is updated
            if(groups%2!=0){
                odd = true;
            }
            
            //loop n times for comparisons, where n equals to the value of variable groups
            for(int x = 0; x < groups ; x++){

                if((x==groups-1)&&(odd==true)){//if it is the last round in for loop and the number of groups is odd in the current level
                    temp[x]=smallerTemp[x*2];//store the last element (the remaining element, no comparison preformed) into temp
                    tempGroupIndex[x]=groupIndex[x*2];//store its index position as well
                }

                else{//perform normal comparisons
                    comparisons++;//increment on variable comparisons after each comparisons performed
                    
                    //compare and find the smaller one, then store the result into corresponding arrays
                    if(smallerTemp[x*2] <= smallerTemp[(x*2)+1]){
                        temp[x]=smallerTemp[x*2];
                        tempGroupIndex[x]=groupIndex[x*2];
                    }

                    else{
                        temp[x]=smallerTemp[(x*2)+1];
                        tempGroupIndex[x]=groupIndex[(x*2)+1];
                    }
                }
            }

            currentLevel++;//increment on cariable currentLevel, indicating the current level's comparisons had completed
            
            //call itself again for performing next round of comparisons
            return findSmallest(groups, temp, tempGroupIndex, levelSmallest, odd, level, currentLevel, arr);
        }
    }


    //method to print array, for easier view in test cases
    public static void printArray(int[] arr){
        System.out.print("\nArray: ");
        for(int count=0;count<arr.length;count++){
            System.out.print("" + arr[count]);
            if(count!=(arr.length-1)){
                System.out.print(", ");
            }
        }
    }
    
    //method to find index position of request item from a given array, for easier view in test cases
    public static int findIndexPosition(int[] arr, int target){
        int pos = -1;
        int count = 0;
        for(int s = 0; s < arr.length ; s++){
            if(arr[s]==target){
                pos=s;
                count++;
            }
        }
        
        //if target appears at least twice, return -1 as an indicator of failure to locate the index position
        if(count>1){
            return -1;
        }
        else{
            return pos;//return index position
        }
    }
    
    //main method
    public static void main(String[] args) {
        //3 testing arrays
        int[] arr1 = {2,5,7,9,1,6,1,21,57,40,8,10,5,6,16,23};//16 belements
        int[] arr2 = {44,55,66,11,22,33,77,88};//8 elements
        int[] arr3 = {100,300,500,700,900,800,600,400,200};//9 elements
        int[] arr4 = {5};//1 element
        int[] arr5 = {3,2,5,6,9,4,7};//7 elements
        int[] arr6 = {};//0 element
        int[][] arrays = {arr1,arr2,arr3,arr4,arr5,arr6};

        //test cases
        int result = 0;//create local variable result to store the returned value from called method
        
        for(int x=0;x<arrays.length;x++){//loop n times to test the n designed arrays
            
            comparisons=0;//set variable comparisons to 0 at the beginning of each test case
            
            System.out.println("Test case #" + (x+1));
            printArray(arrays[x]);//print out the elements of the array
            System.out.println("\nNumber of element(s) in the array: " + arrays[x].length);
            
            //call method to find the second smallest number in a given array
            result = divideAndConquer(arrays[x]);
            
            if(result<0){//result value less than 0 indicates the array has less than 2 elements
                System.out.println("\nArray has less than 2 elements.\n");
            }
            
            else{
                
                //print out the result if second smallest number is found
                System.out.println("Second smallest number: " + result);
                int index = findIndexPosition(arrays[x],result);//call method to find its index position
                
                if(index<0){//negative index value indicates there are more than 1 elements with the same value of target
                    System.out.println("Index position cannot be provided as there are more than one elements with the same value of the second smallest number.");
                }
                
                else{
                    //print out the found index position
                    System.out.println("Index position (index starts from 0) : "+ index);
                }
                
                //print out the number of comparisons used in this algorithm for finding the second smallest number
                System.out.println("Number of comparisons used: "+ comparisons);
                System.out.println("\n");
            }
        }
    }
}  