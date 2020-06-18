package com.interview.prep.company.amazom;

import com.interview.prep.company.InterviewPrepApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AmazonLeetCode extends InterviewPrepApplicationTests {

    // 53. Maximum Subarray (Dynamic Programming)
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int maxSum = nums[0];
        for(int i=1; i < len; ++i){
            if(nums[i - 1] > 0) nums[i] += nums[i - 1];
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }

    // Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        int a_point = 0, b_point = 0;
        int max = 0;

        HashSet<Character> hash_set = new HashSet<>();
        while(b_point < s.length()){
            if(!hash_set.contains(s.charAt(b_point))){
                hash_set.add(s.charAt(b_point));
                b_point++;
                max = Math.max(hash_set.size(), max);
            }else{
                hash_set.remove(s.charAt(a_point));
                a_point++;
            }
        }
        return max;
    }

    // String to Integer (atoi)
    public int myAtoi(String str) {
        if("" == str || null == str) return 0;

        int sign = 1, i = 0, len = str.length();

        while(i < len && str.charAt(i) == ' ') ++i;

        if(i >= len) return 0;

        if(str.charAt(i) == '+' || str.charAt(i) == '-')
            sign = str.charAt(i++) == '+' ? 1 : -1;

        long result = 0;
        while(i < len && Character.isDigit(str.charAt(i))){
            result = result * 10 + (str.charAt(i++) - '0');
            if(result * sign > Integer.MAX_VALUE || result * sign < Integer.MIN_VALUE)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return (int) (result * sign);
    }

    // Container With Most Water
    public int maxArea(int[] height){
        int max = 0;
        int a_point = 0;
        int b_point = height.length - 1;

        while(a_point < b_point){
            if(height[a_point] < height[b_point]){
                max = Math.max(max, height[a_point] * (b_point - a_point));
                a_point += 1;
            }else{
                max = Math.max(max, height[b_point] * (b_point - a_point));
                b_point -= 1;
            }
        }
        return max;
    }

    // Convert Roman to Integer
    public int romanToInt(String s){
        HashMap<Character,Integer> map = populateHashMap();
        int nums = 0, i = 0;
        char[] chars = s.toUpperCase().toCharArray();
        while(i < chars.length && map.containsKey(chars[i])){
            if(i + 1 < chars.length && map.get(chars[i + 1]) > map.get(chars[i])){
                nums += map.get(chars[i + 1]) - map.get(chars[i]);
                i = i + 2;
            }else{
                nums += map.get(chars[i]);
                i++;
            }
        }
        return nums;
    }
    // Helper function for Roman to Integer problem
    private HashMap<Character,Integer> populateHashMap(){
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        return map;
    }

    // Convert Integer to Roman
    public String intToRoman(int num){
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < values.length && num >= 0; i++){
            while(values[i] <= num){
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    // 3Sum
    public List<List<Integer>> threeSum(int[] nums){
        // First sort the array
        Arrays.sort(nums);
        List<List<Integer>> output = new LinkedList<>();
        // loop through the array to stop 2 positions before the array length to find the pair
        for(int i = 0; i < nums.length - 2; i++){
            // first condition of this if is for the first pass when i == 0 and
            // the other condition is to check for no duplicates. if found we just skip the duplicate
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
                // from here the program is similar to 2sum having two pointer low and high like a sliding window
                // The low pointer is set one position ahead of current position of i
                int low = i + 1;
                // The high pointer always starts from the end.
                int high = nums.length - 1;
                // Sum is going to store the value of compliment of the current value of i - 0.
                // This will give us the sum to find the two elements which add up to the sum.
                int sum = 0 - nums[i];
                while(low < high){
                    if(nums[low] + nums[high] == sum){
                        output.add(Arrays.asList(nums[i],nums[low],nums[high]));
                        // Skipping any duplicate values from the low pointer as the array is sorted
                        while(low < high && nums[low] == nums[low + 1]) low++;
                        // Skipping any duplicate values from the high pointer as the array is sorted
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        // Incrementing the low and decementing the high pointer to the non duplicate values
                        low++;
                        high--;
                        // if addition of values at low and high is greater than sum then decrementing high pointer,
                        // if addition of values at low and high is less than sum then incrementing the low
                    }else if(nums[low] + nums[high] > sum)
                        high--;
                    else
                        low++;
                }
            }
        }
        return output;
    }

    @Test
    public void testMaxSubArray(){
        int intArr1[] = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println( "Input: " + Arrays.toString(intArr1) +
                ", Maximum contiguous sub-array sub, Output: " + maxSubArray(intArr1));
    }

    @Test
    public void testLengthOfLongestSubstring(){
        String long_sub_array_string = "pwwkew";
        System.out.println("Input: " + long_sub_array_string + ", Longest Substring Without Repeating Characters: " +
                lengthOfLongestSubstring(long_sub_array_string));
    }

    @Test
    public void testmyAtoI(){
        String aToI_string = "-123";
        System.out.println("Input: " + aToI_string + ", Convert String to Integer: " + myAtoi(aToI_string));
    }

    @Test
    public void testMaxArea(){
        int intArr2[] = {1,8,6,2,5,4,8,3,7};
        System.out.println("Input: " + Arrays.toString(intArr2) + ", Container with most water: " + maxArea(intArr2));
    }

    @Test
    public void testRomanToInt(){
        String roman = "MCMXCIV";
        System.out.println("Input: " + roman + ", Convert Roman to Integer: " + romanToInt(roman));
    }

    @Test
    public void testIntToRoman(){
        int num = 2020;
        System.out.println("Input: " + num + ", Convert Integer to Roman: " + intToRoman(num));
    }

    @Test
    public void testThreeSum(){
        int intArray3[] = {-1, 0, 1, 2, -1, -4};
        System.out.println("Input: " + Arrays.toString(intArray3) +
                ", unique triplets in the array which gives the sum of zero: " + threeSum(intArray3));
    }

}
