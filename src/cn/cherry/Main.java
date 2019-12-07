package cn.cherry;

import java.util.*;

public class Main {
    public static void main(String[] args) {
	    Solution s = new Solution();
        int[] nn = {1,1,2,2,3,3,4,5,5};
        int[] nn2 = {1,3,4,2};
        System.out.println(s.singleNonDuplicate(nn));
    }
}

class Solution {
    // 376
    public int wiggleMaxLength(int[] nums) {
        return 0;
    }
    // 540
    public int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1, mid = (start + end) / 2;;
        while(start < end){
            int half = mid - start;
            if(half % 2 == 0){
                if(nums[mid] == nums[mid - 1]){
                    end = mid;
                }else if(nums[mid] == nums[mid + 1]){
                    start = mid;
                }else{
                    break;
                }
            }else{
                if(nums[mid] == nums[mid - 1]){
                    start = mid + 1;
                }else if(nums[mid] == nums[mid + 1]){
                    end = mid - 1;
                }else{
                    break;
                }
            }
            mid = (start + end) / 2;
        }
        return nums[mid];
    }
    // 771
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if(set.contains(S.charAt(i)))
                count++;
        }
        return count;
    }
    // 496
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if(nums2.length == 0) return res;
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(nums2[0]);
        for (int i = 0; i < nums2.length; i++) {
            while(!stack.isEmpty()){
                if(stack.peek() < nums2[i]){
                    map.put(stack.pop(), nums2[i]);
                }else{
                    break;
                }
            }
            stack.push(nums2[i]);
        }
        while(!stack.isEmpty()){
            map.put(stack.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
    // 590
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> resStack = new Stack<>();
        stack.push(root);
        List<Integer> res = new LinkedList<>();
        if(root == null) return res;
        while(!stack.isEmpty()){
            Node node = stack.pop();
            resStack.push(node);
            List<Node> list = node.children;
            for (Node n : list) {
                stack.push(n);
            }
        }
        while(!resStack.isEmpty()) {
            res.add(resStack.pop().val);
        }
        return res;
    }

    // 498
    public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        int n = 0;
        if(m != 0)
           n = matrix[0].length;
        int[] res = new int[m * n];
        int x = 0, y = 0;
        boolean dire = true;
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[y][x];
            if(dire){
                if(x == n - 1){
                    y++;dire = !dire;
                }else if(y == 0){
                    x++;dire = !dire;
                }else{
                    x++;y--;
                }
            }else{
                if(y == m - 1){
                    x++;dire = !dire;
                }else if(x == 0){
                    y++;dire = !dire;
                }else{
                    x--;y++;
                }
            }
        }
        return res;
    }

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
    }

    // 220
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> map = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long left = map.floor((long)nums[i]);
            if(left != null && Math.abs(nums[i] - left) <= t)
                return true;
            Long right = map.ceiling((long)nums[i]);
            if(right != null && Math.abs(nums[i] - right) <= t)
                return true;
            map.add((long)nums[i]);
            if(map.size() > k)
                map.remove((long)nums[i - k]);
        }
        return false;
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};