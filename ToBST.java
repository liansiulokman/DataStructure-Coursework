package comp2011.lec6;
import comp2011.lec9.BinarySearchTree;
import comp2011.lec9.TreeNode;

public class ToBST {
	public static void printList(DoubleLinkedList list){
		DoubleNode head = list.head;
		System.out.println("Double Linked List: ");
		while(head!=null){
			System.out.print(head.data.toString()+" ");
			head = head.next;
		}
	}
	
	public static void printTree(BinarySearchTree tree){
		System.out.println("\nPreorder: ");
		preorder(tree.root);
		System.out.println("\nInorder: ");
		inorder(tree.root);
	}
	
	public static void preorder(TreeNode curRoot){
		if(curRoot!=null){
			System.out.print(curRoot.data.toString()+" ");
			preorder(curRoot.leftChild);
			preorder(curRoot.rightChild);
		}
	}
	
	public static void inorder(TreeNode curRoot){
		if(curRoot!=null){
			inorder(curRoot.leftChild);
			System.out.print(curRoot.data.toString()+" ");
			inorder(curRoot.rightChild);
		}
	}
	
	public static BinarySearchTree toBST(DoubleLinkedList list){
		BinarySearchTree tree = new BinarySearchTree();
		DoubleNode cur = list.head;
		int count = 0;
		while(cur!=null){
			cur = cur.next;
			Object add = list.deleteFirst();
			tree.insert(count++, add);
			checkBalance(tree,tree.root,tree.root);

		}
		return tree;
	}	
	
	public static int countLeftChild(TreeNode cur){
		int leftChild=0;
		while(cur.leftChild!=null){
			cur = cur.leftChild;
			leftChild++;
		}
		return leftChild;
	}
	
	public static int countRightChild(TreeNode cur){
		int rightChild=0;
		while(cur.rightChild!=null){
			cur = cur.rightChild;
			rightChild++;
		}
		return rightChild;
	}
	
	public static void checkBalance(BinarySearchTree tree,TreeNode parent, TreeNode superParent){
		if(parent!=null){
			balanceTree(tree,parent,superParent);
			checkBalance(tree,parent.leftChild,parent);
			checkBalance(tree,parent.rightChild,parent);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void balanceTree(BinarySearchTree tree,TreeNode parent, TreeNode superParent){
		int left = countLeftChild(parent);
		int right = countRightChild(parent);
		if(right-left>1){
			if(parent.equals(tree.root)){
				tree.root = parent.rightChild;
				if(parent.rightChild.leftChild!=null){
					parent.rightChild = parent.rightChild.leftChild;
					tree.root.leftChild = parent;
				}
				else{
					tree.root.leftChild = parent;
					parent.rightChild=null;
				}
			}
			else {
				TreeNode switchRoot = parent.rightChild;
				switchRoot.leftChild = parent;
				superParent.rightChild = switchRoot;
				parent.rightChild = null;
			}
		}
		else if(left-right>1){
			if(parent.equals(tree.root)){
				tree.root = parent.leftChild;
				if(parent.leftChild.rightChild!=null){
					parent.leftChild = parent.leftChild.rightChild;
					tree.root.rightChild = parent;
				}
				else{
					tree.root.rightChild = parent;
					parent.leftChild=null;
				}
			}
			else {
				TreeNode switchRt = parent.leftChild;
				switchRt.rightChild = parent;
				superParent.leftChild = switchRt;
				parent.leftChild = null;
			}
		}
	}
	
	public static void main(String[] args){
		
		DoubleLinkedList list = new DoubleLinkedList();
		list.insertAtFront(2);
		list.insertAtEnd(3);
		list.insertAtEnd(4);
		list.insertAtEnd(5);
		list.insertAtEnd(6);
		list.insertAtEnd(7);
		list.insertAtEnd(8);
		list.insertAtEnd(9);
		list.insertAtEnd(10);
		list.insertAtEnd(15);
		list.insertAtEnd(18);
		
		DoubleLinkedList list1 = new DoubleLinkedList();
		list1.insertAtFront(5);
		list1.insertAtEnd(10);
		list1.insertAtEnd(15);
		list1.insertAtEnd(20);
		list1.insertAtEnd(25);
		list1.insertAtEnd(30);
		
		printList(list);
		BinarySearchTree bst = new BinarySearchTree();
		bst = toBST(list);
		printTree(bst);
		System.out.println("\n");
		printList(list1);
		BinarySearchTree bst1 = new BinarySearchTree();
		bst1 = toBST(list1);
		printTree(bst1);		
	}
}
