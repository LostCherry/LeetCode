package cn.cherry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(s.findDisappearedNumbers(nums));
        s.findDisappearedNumbers(nums);
    }
}
class Solution {
    // 448
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while(nums[i] != nums[nums[i] - 1]){
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if(nums[i - 1] != i){
                res.add(i);
            }
        }
        return res;
    }

    //1247
    public int minimumSwap(String s1, String s2) {
        if(s1.length() != s2.length())
            return -1;
        int xCount = 0, count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
                if(s1.charAt(i) == 'x')
                    xCount++;
            }
        }
        if((count & 1) == 0){
            if((xCount & 1) == 0){
                return count / 2;
            }else{
                return count / 2 + 1;
            }
        } else
            return -1;
        // xxxy  xxyy
        // yyyx  yyxx
    }
}