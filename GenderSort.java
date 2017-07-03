//Student ID: 14040502D
//GenderSort.java

import java.util.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;


public class GenderSort {
    // sort arr such that all female students come before male students. 
    public static void GenderSort (Student[] arr) {
        mergeSort(arr,0, arr.length-1);
    }
    
    private static void mergeSort(Student[] a, int begin, int end){
        int mid=0;
        if(begin<end){
            mid = (begin+end)/2;
            mergeSort(a,begin,mid);
            mergeSort(a,mid+1,end);
        }
        int left = begin;
        int right = mid+1;
        
        while(left<=mid && right<=end){
            if(a[left].gender<a[right].gender)
                left++;
            else{
                Student temp = a[right];
                System.arraycopy(a, left, a, left+1, right-left);
                a[left] = temp;
                
                left++;
                mid++;
                right++;
            }
        }
    }
    
    public static void arrayPrint(Student[] arr){
        //System.out.println("Printing the array...\n");
        for(int loop=0;loop<arr.length;loop++){
            //System.out.print("Student " + (loop+1) + ": ID: " + arr[loop].id + "  Name: " + arr[loop].name + "  Gender: " + arr[loop].gender);
            
            String sex = null;
            System.out.format("Student %3d ", (loop+1));
            System.out.format("  ID: %12d ", arr[loop].id);
            //System.out.format("  Name: %4s ", arr[loop].name);
            if(arr[loop].gender==0) sex = "female ";
            if(arr[loop].gender==1) sex = "male   ";
            if(arr[loop].gender==2) sex = "unknown";
            System.out.print("  Gender: " + sex);
            
            
            if(loop!=(arr.length-1)){
                System.out.print(" , ");
            }
            
            //print 3 records in a row
            if((loop+1)%3==0){
               System.out.println();
            }
            //print 1 record in a row
            //if((loop+1)%1==0){
            //  System.out.println();
            //}
        }
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int size = 100;
        Student[] arr = new Student[size];
        // build 100 students with random id and gender.
        for (int i= 0; i < size; i++ ) {
            int id = Math.abs(random.nextInt());
            arr[i] = new Student(id, (byte) (id % 3));
        }
        
        
        System.out.println("Press Enter to print the array before sorting.");
        keyboard.nextLine();
        System.out.println("Before sorting:\n");
        arrayPrint(arr);
        
        System.out.println("\n\nTime (starts sorting): " + Calendar.getInstance().getTime());
        GenderSort(arr);
        System.out.println("Time (finish sorting): " + Calendar.getInstance().getTime());

        System.out.println("\nPress Enter to print the array after sorting.");
        keyboard.nextLine();
        System.out.println("After sorting:\n");
        arrayPrint(arr);
        
        /*
        System.out.println(Arrays.stream(arr).
                map(Student::toString).
               collect(Collectors.joining("\n")));
        GenderSort(arr);
        System.out.println("\nSorted\n");
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
        String sex = "";
        if(gender==0) sex = "female";
        if(gender==1) sex = "male";
        if(gender==2) sex = "unknown";
        return sex + " student " + id;
    }
}