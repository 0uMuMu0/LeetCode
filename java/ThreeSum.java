package Medium;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class ThreeSum {
	public static void main(String[] args){
		int[] nums = {-1,0,2,1,-1,-4};
		System.out.println(threeSum(nums));
		System.out.println(threeSum2(nums));
	}
	
	public static List<List<Integer>> threeSum(int[] nums){
		//解法1：暴力，O(N3)会超时
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		Arrays.sort(nums);
		for(int i=0;i<nums.length-2;i++){
			for(int j=i+1;j<nums.length-1;j++){
				for(int k=j+1;k<nums.length;k++){
					if(nums[i]+nums[j]+nums[k]==0){
						List<Integer> tmp = new ArrayList<Integer>();
						tmp.add(nums[i]);
						tmp.add(nums[j]);
						tmp.add(nums[k]);
						if(!result.contains(tmp)){
							result.add(tmp);
						}
					}
				}
			}
		}
		return result;
		
	}
	
	public static List<List<Integer>> threeSum2(int[] nums){
		//解法2：O(N2)
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		for(int i=0;i<nums.length-2;i++){
			if(i>0 && nums[i]==nums[i-1]){   //过滤掉一样的情况
				continue;
			}
			int target = -nums[i];
			int left = i+1;
			int right = nums.length-1;
			while(left<right){
				if(nums[left]+nums[right]==target){
					result.add(Arrays.asList(nums[i],nums[left],nums[right]));
					left++;
					right--;
					while(left<right && nums[left]==nums[left-1]){
						left++;
					}
					while(left<right && nums[right]==nums[right+1]){
						right--;
					}
				}else if(nums[left]+nums[right]>target){
					right--;
				}else{
					left++;
				}
			}
		}
		return result;
		
	}

}
