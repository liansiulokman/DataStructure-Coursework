package comp2011.lec9;

//FindNumberOfLeaves.java

// algorithm to find the number of leaves of a binary tree.
// One in recursion
// One  in non-recursion
// Run in O(n) time, where n = number of nodes in the tree.
// use classes in comp2011.lec9

public class FindNumberOfLeaves {
	
	static int leaves=0;
	static int count = 0;
	
	public static int findLeavesRecursion(TreeNode root){
		TreeNode cur = root;
		if(cur!=null){
			if(cur.leftChild==null && cur.rightChild==null){
				leaves++;
			}
			findLeavesRecursion(cur.leftChild);
			findLeavesRecursion(cur.rightChild);
		}
		return leaves;
	}
	
	public static int findLeavesNonRecursion(TreeNode root){
		count=0;
		countLeaves(root);
		return count;
	}
	
	public static void countLeaves(TreeNode cur){
		if(cur!=null){
			if(cur.leftChild==null && cur.rightChild==null){
				count++;
			}
			countLeaves(cur.leftChild);
			countLeaves(cur.rightChild);
		}
	}
	
	public static void main(String[] args){
		TreeNode root = new TreeNode(40,40);
    	root.leftChild = new TreeNode(50,50);
    	root.rightChild = new TreeNode(70,70);
    	root.leftChild.leftChild = new TreeNode(30,30);
    	root.leftChild.rightChild = new TreeNode(80,80);
    	root.rightChild.leftChild = new TreeNode(60,60);
    	root.rightChild.rightChild = new TreeNode(90,90);
    	leaves = 0;//set leaves to 0 before calling findLeavesRecursion
    	System.out.println("Number of leaves: "+findLeavesRecursion(root));
    	System.out.println("\nUsing Non-Recursion:\nNumber of leaves: "+findLeavesNonRecursion(root));
	}
}
