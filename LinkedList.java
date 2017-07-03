package comp2011.lec6;
import java.util.Comparator;

class Node<T> {
    public T data;
    public Node<T> next;
    int key;
    Node (T data, int key) {
        this.data = data; 
        this.key = key;
    }
}

public class LinkedList<T> {
    Node<T> head; //firstNode
    Node<T> tail; //lastNode

    public LinkedList() {
        head = tail = null;
    }
    
    public boolean isEmpty() { 
        return tail == null; //or head == null 
    }
    
    public void insertAtFront(T element, int key) {
        Node<T> newNode = new Node<T>(element, key);
        newNode.next = head;
        head = newNode;
        if (tail == null) tail = head;
    }    
    
    public void insertAtEnd(T element, int key) {
        Node<T> newNode = new Node<T>(element,key);
        if (head == null) {
            head = tail = newNode;
            newNode.next = null;
        }
        else {
            newNode.next = null;
            tail.next = newNode;
            tail = newNode;
        }
    }
    
    public T deleteFirst() {
        if (head == null) 
            return null;
        Node<T> temp = head;
        if (head.next == null) 
            head = tail = null;
        else 
            head = head.next;
        return temp.data;
    }
    
    public T deleteLast() {
        if (head == null) 
            return null;
        if (head.next == null) {
            Node<T> temp = head;
            head = tail = null; // head = null;  
            return temp.data;
        }
        Node<T> curNode = head;
        Node<T> nextNode = head.next;
        while ( nextNode.next != null ) {
            curNode = nextNode;
            nextNode = curNode.next;
        }
        tail = curNode;
        curNode.next= null;
        return nextNode.data;
    }
    
    /*
     * you don't need to write such a method
     * */
    public void sort(Comparator<T> C) { 
        // try to think about this.
    }
    
    public String toString() {
        if (head == null) return "The list is empty.";
        StringBuilder sb = new StringBuilder();
        Node<T> cur = head;
        while ( cur != null ) {
            sb.append(cur.data + "\n");
            cur = cur.next;
        }
        return sb.toString();
    }
    
    public static Node mergeSort(Node head){
        if(head==null||head.next==null)
            return head;
        int count=0;
        Node p = head;
        while(p!=null){
            count++;
            p=p.next;
        }
        int mid = count/2;
        Node l = head;
        Node r = null;
        Node p2 = head;
        int countHalf = 0;
        while(p2!=null){
            countHalf++;
            Node next = p2.next;
            if(countHalf ==mid){
                p2.next = null;
                r = next;
            }
            p2 = next;
        }
        Node h1 = mergeSort(l);
        Node h2 = mergeSort(r);
        
        Node merged = merge(h1,h2);
        return merged;
    }
    public static Node merge(Node l, Node r){
        Node n1 = l;
        Node n2 = r;
        Node pHead = new Node(0,0);
        Node pNew = pHead;
        while(n1!=null || n2!=null){
            if(n1==null){
                pNew.next=new Node(n2.data,n2.key);
                n2 = n2.next;
                pNew = pNew.next;
            }
            else if(n2==null){
                pNew.next = new Node(n1.data,n1.key);
                n1=n1.next;
                pNew = pNew.next;
            }
            else{
                if(n1.key<n2.key){
                    pNew.next = new Node(n1.data,n1.key);
                    n1 = n1.next;
                    pNew = pNew.next;
                }
                else if(n1.key==n2.key){
                    pNew.next = new Node(n1.data,n1.key);
                    pNew.next.next = new Node(n1.data,n1.key);
                    pNew = pNew.next.next;
                    n1 = n1.next;
                    n2 = n2.next;
                }
                else{
                    pNew.next = new Node(n2.data,n2.key);
                    n2 = n2.next;
                    pNew = pNew.next;
                }
            }
        }
        return pHead.next;
    }
    
    
    public static void printList(LinkedList<Task> list){
        Node cur = list.head;
        while(cur!=null){
            System.out.println("Key: "+cur.key+", Task: "+cur.data);
            cur = cur.next;
        }
    }
    
    public static void main(String[] args) {
        LinkedList<Task> list = new LinkedList<Task>();
        list.insertAtEnd(new Task("1006 19", "proposal"),8);
        list.insertAtEnd(new Task("1006 23", "project for COMP2011"),7);
        list.insertAtEnd(new Task("1008 8", "lecture 6 slides of COMP2011"),6);
        list.insertAtEnd(new Task("1008 23", "mid-term exam paper of COMP2011"),5);
        list.insertAtEnd(new Task("1011 18", "mid-term exam paper of COMP201"),4);
        list.insertAtEnd(new Task("1014 18", "final exam paper of COMP2011"),3);
        list.insertAtEnd(new Task("1014 18", "final exam paper of COMP201"),2);
        list.insertAtEnd(new Task("1015 12", "soda paper camera-ready version"),1);
        list.insertAtEnd(new Task("1017 23", "IandC paper revision"),0);
        printList(list);
        list.head = mergeSort(list.head);
        System.out.println("\nAfter merge sort: \n");
        printList(list);
    }
}
