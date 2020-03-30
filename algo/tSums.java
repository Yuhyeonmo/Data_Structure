import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class tSums {
    public List<List<Integer>> threeSum(int[] nums) {
    	 
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int i = 0, j = 0, n = nums.length, k = n - 1;
        if (k < 2 || nums[k] < 0) {
            return res;
        }
        while (i < n - 2) {
            if (nums[i] > 0) {
                break;
            }
            int target = -nums[i];
            j = i + 1;
            k = n - 1;
            while (j < k) {
                if (nums[k] < 0) {
                    break;
                }
                if (nums[j] + nums[k] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while(j < k && nums[j] == nums[++j]);
                    while(j < k && nums[k] == nums[--k]);
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
            while (i < n - 2 && nums[i] == nums[++i]);
        }
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<List<Integer>> answer = new LinkedList<List<Integer>>();
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        List<Integer> list = Arrays.asList(1,2,3);
        List<Integer> a = new LinkedList<Integer>();
        HashSet<Integer> sum = new HashSet<Integer>();
 
        a.add(-1);
        a.add(0);
        a.add(1);
        List<Integer> b = new LinkedList<Integer>();
        b.add(-1);
        b.add(0);
        b.add(-1);

	}

}
