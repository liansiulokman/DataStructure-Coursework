
import java.util.Arrays;

public class MergeSort {
	static int count=1;
	
	public static void main(String[] args){
		int[] a = {6,2,10,8,4,3,5,1,9};
		int[] b = {46,22,50,78,104,32,1,99};
		System.out.println("Before merge sort: ");
		System.out.println(Arrays.toString(a));
		mergeSort(a);
		System.out.println("After merge sort: ");
		System.out.println(Arrays.toString(a));
		System.out.println();
		
		System.out.println("Before merge sort: ");
		System.out.println(Arrays.toString(b));
		mergeSort(b);
		System.out.println("After merge sort: ");
		System.out.println(Arrays.toString(b));
	}
	
	public static void mergeSort(int[] a){
		mergeSort(a,a.length);
	}
	
	private static void mergeSort(int[] a, int n){
		for(int size=1; size<=n-1;size=2*size){
			for(int left=0;left<n-1;left+=2*size){
				int mid = left+size-1;
				int t = left+(2*size)-1;
				int right = (t<(n-1))?t:(n-1);
				merge(a, left, mid, right);
			}
		}
	}
	
	private static void merge(int[] a, int left, int mid, int right){
		int i,j,k;
		int leftSize = mid-left+1;
		int rightSize = right - mid;
		
		int[] workspace = new int[(a.length+1)/2];
		
		i=0;
		j=leftSize;
		//k=left;//starting point
		int s =0;
		int half = (a.length)/2;
		
		if(leftSize<=half && rightSize<half){
			for(int pt = 0; pt<(right-left+1);pt++ ){
				//copy left and right to workspace
				workspace[pt]=a[pt+left];
			}
		
			while(i<leftSize && j<(rightSize+leftSize)){
				if(workspace[i]>workspace[j]){
					a[s+left] = workspace[j];
					j++;
				}
				else{
					a[s+left]=workspace[i];
					i++;
				}
				s++;
			}
			while(i<leftSize){
				a[s+left]=workspace[i];
				i++;
				s++;
			}
			while(j<(rightSize+leftSize)){
				a[s+left]=workspace[j];
				j++;
				s++;
			}
			
		}
		
		else{
			for(int pt = leftSize; pt<a.length;pt++ ){
				//copy left to workspace
				workspace[pt-leftSize]=a[pt];
				
			}
			int x = 0;//pt to workspace
			int y = 0;//pt to a
			int cur = 0;
			int tail = (leftSize==rightSize)?rightSize:leftSize;
			int tailSize = tail;
			while(x<rightSize && y<leftSize){
				if(workspace[x]<a[y]){
					for(int h = tail-1+y;h>=y;h--){
						a[h+1]=a[h];
					}
					a[cur]=workspace[x];
					x++;
					
				}
				else{
					tail--;
				}
				y++;
				cur++;
			}
			if(a.length%2!=0){
				while(x<rightSize){
					for(int h = tail-1+y;h>=y;h--){
						a[h+1]=a[h];
					}
					a[cur]=workspace[x];
					x++;
					cur++;
				}
			}
			else{
				while(x<rightSize){
					a[x+y]=workspace[x];
					x++;
					cur++;
				}
			}
		}
	}
}