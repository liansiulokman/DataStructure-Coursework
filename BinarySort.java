//Student ID: 14040502D
//BinarySort.java
package comp2011.ass1;

import java.util.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BinarySort {
    // sort arr such that all female students come before male students. 
    public static void binarySort (Student[] arr) {
        
        //initialization of variables
        int pointer = 1;//pointing the index of arr, initially set as 1
        int count = 0;//count total number of loops required to sort the whole array
        byte tempGender;//temp variable to store the value of gender during swapping
        int tempId;//temp variable to store the value of ID during swapping
        String tempName;//temp variable to store the value of name during swapping
        
        while(count<arr.length-1){//while it is not the last loop
            
            //store the element to be moved in temp variables
            tempGender = arr[pointer].gender;
            tempId = arr[pointer].id;
            tempName = arr[pointer].name;
            
            if(arr[pointer].gender==1){//male student: put at the back of the array
                
                //move all the elements after pointer leftwards
                for(int a=pointer;a<arr.length-1;a++){
                    arr[a].gender=arr[a+1].gender;
                    arr[a].id=arr[a+1].id;
                    arr[a].name=arr[a+1].name;
                }
                
                //put the values of the male student to the last element
                arr[arr.length-1].gender=tempGender;
                arr[arr.length-1].id=tempId;
                arr[arr.length-1].name=tempName;
            }

            else{//female student: put at the front of the array
                
                //move all the elements before pointer rightwards
                for(int b=pointer;b>0;b--){
                    arr[b].gender=arr[b-1].gender;
                    arr[b].id=arr[b-1].id;
                    arr[b].name=arr[b-1].name;
                }
                
                //put the values of the female student to the first element
                arr[0].gender=tempGender;
                arr[0].id=tempId;
                arr[0].name=tempName;
                
                //move pointer forward
                pointer+=1;//pointer only moved when any element(s) moves to the front
            }
            count++;//increment on count
        }
    }
    
    //method to print array, for easier view in test cases
    public static void arrayPrint(Student[] arr){
        for(int loop=0;loop<arr.length;loop++){
            System.out.format("Student %3d ", (loop+1));
            System.out.format("  ID: %10d ", arr[loop].id);
            System.out.format("  Name: %4s ", arr[loop].name);
            System.out.format("  Gender: %2d", arr[loop].gender);
            
            if(loop!=(arr.length-1)){
                System.out.print(", ");
            }
            
            //print 3 records in a row
            if((loop+1)%3==0){
                System.out.println();
            }
        }
    }
    
    //main method
    public static void main(String[] args) {

        //initialization of variables
        Scanner keyboard = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int size = 100;
        Student[] arr = new Student[size];

        // build 100 students with random id and gender.
        for (int i= 0; i < size; i++ ) {
            int id = Math.abs(random.nextInt());
            arr[i] = new Student(id, (byte) (id % 2));
        }
        
        //the following printing pattern facilitates easier view for testing
        //all records randomly generated will be printed, then user shound press enter to start sorting
        //the time starts sorting and sorting finished will be displayed
        //User has to press enter again to print out all the records after sorting
        System.out.println("Before sorting: (3 records in a row for wider view)\n");
        arrayPrint(arr);
        System.out.println("\n\nPress Enter to start sorting.");
        keyboard.nextLine();
        System.out.println("Time (starts sorting): " + Calendar.getInstance().getTime());
        
        binarySort(arr);
        
        System.out.println("Time (finish sorting): " + Calendar.getInstance().getTime());

        System.out.println("\nPress Enter to print the array after sorting.");
        keyboard.nextLine();
        arrayPrint(arr);
        
        /*
        System.out.println(Arrays.stream(arr).
                map(Student::toString).
                collect(Collectors.joining("\n")));
        */
    }
}

class Student {
    int id;
    String name;
    byte gender; // 0 for girls and 1 for boys. 
    
    public Student (int id, byte gender) {
        this.id = id;
        this.gender = gender;
    }
    
    public String toString () {
        return (gender == 0? "female":"male") + " student " + id;
    }
}