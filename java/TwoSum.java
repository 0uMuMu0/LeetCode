import java.util.HashMap;

public class TwoSum {
	public static void main(String[] args){
		int[] nums = {3,5,1,10};
		int target = 6;
		int[] result = twoSum(nums, target);
		for(int i=0; i<result.length; i++){
			System.out.println(result[i]);
		}
		
	}
	
	public static int[] twoSum(int[] nums, int target){
		int[] result = new int[2];
		HashMap<Integer, Integer> number = new HashMap<Integer, Integer>();
		for(int i=0; i<nums.length; i++){
			number.put(nums[i], i);
		}
		for(int i=0; i<nums.length; i++){
			if(number.get(target-nums[i])!=null){
				result[0] = i;
				result[1] = number.get(target-nums[i]);
				if(result[0]==result[1]){
					continue;
				}
				break;
			}
		}
		return result;
	}

}
