import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class foursums {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
         HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        int n = nums.length;
        int i = 0; int j = 0; int k=0; int h = n-1;
        
        if(n<4 || nums[n-1]<target){
            return res;
        }
        int key = 0;
        while(i< n-2){
           
         j = i+1;          
            while(j<n-1){
                k=j+1;
                h=n-1;
              
                while(k<h){

                    if((nums[i]+nums[j]+nums[k]+nums[h])==target)
                    {
                        List<Integer> list = Arrays.asList(nums[i],nums[j],nums[k],nums[h]);
                        if(!map.containsValue(list)){
                            res.add(list);
                            map.put(key++, list);
                        }
                    
                        k++;
                    }
                    else if((nums[i]+nums[j]+nums[k]+nums[h])>target){
                        h--;
                    }
                    else {
                        k++;
                    }
                }
                j++;
                
            }
            i++;
        }
     return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = {0,1,5,0,1,5,5,-4};
		int target = 11;
		
		List<List<Integer>> res = fourSum(nums, target);
		System.out.println(res.toString());

	}

}
