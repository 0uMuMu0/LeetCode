package Medium;

public class ContainerWithMostWater {
	public static void main(String[] args){
		int[] height = {2,4,1,3,12,2,8,1};
		System.out.println(maxArea1(height));
		System.out.println(maxArea2(height));
	}
	
	public static int maxArea1(int[] height){
		//解法1：暴力,O(N2),会超时
		int max = 0;
		int area;
		int h;
		for(int i=0;i<height.length-1;i++){
			for(int j=i+1;j<height.length;j++){
				h = height[i]<height[j]?height[i]:height[j];
				area = h*(j-i);
				if(max<area){
					max=area;
				}
			}
		}
		return max;
	}
	
	public static int maxArea2(int[] height){
		//解法2:O(N)
		int max = 0;
		int area;
		int left = 0;
		int right = height.length-1;
		while(left<right){
			if(height[left]<height[right]){
				area = height[left]*(right-left);
				left++;
			}else{
				area = height[right]*(right-left);
				right--;
			}
			if(max<area){
				max = area;
			}
		}
		return max;
	}
	
	

}
