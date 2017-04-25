import java.util.Arrays;
public class MedianOfTwoSortedArrays {
	public static void main(String[] args){
		double median1,median2;
		int[] num1 = {1,2,3};
		int[] num2 = {1};
		median1 = findMedianSortedArrays(num1,num2);
		median2 = findMedianSortedArrays2(num1,num2);
		System.out.println(median1+"  "+median2);
	}
	
	public static double findMedianSortedArrays(int[] num1, int[] num2){
		int n = num1.length;
		int m = num2.length;
		int k = (n+m)/2;
		int i=0,j=0,p=0;
		double median;
		double[] tmp = new double[k+1];
		while((i<n || j<m) && p<=k){
			if(i==n){
				while(j<m&&p<=k){
					tmp[p++] = num2[j++];
				}
				break;
			}
			if(j==m){
				while(i<n&&p<=k){
					tmp[p++] = num1[i++];
				}
				break;
			}
			if(num1[i]<num2[j]){
				tmp[p++] = num1[i++];
			}else{
				tmp[p++] = num2[j++];
			}
		}
		if((n+m)%2==0){
			median = (tmp[k-1]+tmp[k])/2;
		}else{
			median = tmp[k];
		}
		return median;		
	}
	
	public static double findMedianSortedArrays2(int[] num1, int[] num2){
		//更好的解法：转化为求第k小(这种方法用于C++比较好)
		int n = num1.length;
		int m = num2.length;
		int k = (n+m)/2;
		double m1,m2,median;
		if((n+m)%2==0){
			m1 = findKth(num1,0,n-1,num2,0,m-1,k);
			m2 = findKth(num1,0,n-1,num2,0,m-1,k+1);
			median = (m1+m2)/2;
		}else{
			median = findKth(num1,0,n-1,num2,0,m-1,k+1);
		}
		return median;
	}
	
	public static double findKth(int[] a, int astart, int aend, int[] b, int bstart, int bend, int k){
		int n = aend-astart+1;
		int m = bend-bstart+1;
		//确保a是较短的那个
		if(n>m){
			return findKth(b, bstart, bend, a, astart, aend, k);
		}
		if(n==0){
			return b[bstart+k-1];
		}
		if(m==0){
			return a[astart+k-1];
		}
		if(k==1){
			return Math.min(a[astart], b[bstart]);
		}
		//分成两部分
		int pa,pb;
		pa = Math.min(k/2, n);
		pb = k-pa;
		if(a[astart+pa-1]<b[bstart+pb-1]){
			return findKth(a, astart+pa, aend, b, bstart, bend, k-pa);
		}else if(a[astart+pa-1]>b[bstart+pb-1]){
			return findKth(a, astart, aend, b, bstart+pb, bend, k-pb);
		}else{
			return a[astart+pa-1];
		}
	}

}
