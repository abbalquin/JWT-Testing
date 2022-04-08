package com.jwt.token.sample.loginApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.*;
import java.util.Arrays;


@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan("com.jwt.token.sample.loginApp")
@EntityScan("com.jwt.token.sample.loginApp.domain")
@EnableJpaRepositories(basePackages = "com.jwt.token.sample.loginApp.repository")
//@EnableWebMvc
public class LoginAppApplication {//extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(LoginAppApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(LoginAppApplication.class);
    }


























    public static int getInteger(int n){
        String strN = String.valueOf(n);
        return Integer.parseInt(strN.replaceFirst("5", ""));
    }


//    private static Class<LoginAppApplication> applicationClass = LoginAppApplication.class;

    public static boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int firstWordValue = getNumericValue(firstWord);
        int secondWordValue = getNumericValue(secondWord);
        int targetWordValue = getNumericValue(targetWord);
        return (firstWordValue + secondWordValue) ==  targetWordValue;
    }

    private static int getNumericValue(String word){
        char[] charArr = word.toCharArray();
        String numStr = "";
        for(char chr: charArr){
            numStr += chr;
        }

        return Integer.parseInt(numStr);
    }

    public static int minimumSum(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        Arrays.sort(charArray);
        return Integer.parseInt(""+charArray[0] + charArray[2]) + Integer.parseInt(""+charArray[1] + charArray[3]);
    }

    public static int[] shuffle(int[] nums, int n) {
        int[] result = new int[n*2];
        for(int i=0, idx = 0; i<result.length; i+=2, idx++){
            result[i] = nums[idx];
            result[i+1] = nums[n+idx];
        }

        return result;
    }

    public static int minOperations(int[] nums) {
        int result = 0;
        boolean hasChanges= false;
        for (int j = nums.length - 2; j >= 0; j--) {
            if (nums[j] >= nums[j + 1]) {
                nums[j + 1]++;
                result++;
                hasChanges = true;
            }

            if(j==1 && hasChanges){
                j= nums.length-2;
            }
            hasChanges = false;
        }
        return result;
    }

    public static String restoreString(String s, int[] indices) {
        char[] arr = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[indices[i]] = s.charAt(i);
        }
        return String.valueOf(arr);
    }

    public static int maximum69Number2(int num) {
        int temp = num;
        int idx = 0;
        int multiplier = Integer.MIN_VALUE;
        while (temp > 0) {
            int mod = temp % 10;
            if (mod == 6) {
                multiplier = idx;
            }
            idx++;
            temp /= 10;
        }
        return multiplier != Integer.MIN_VALUE ? num + 3 * (int) Math.pow(10, multiplier) : num;
    }

    public static int maximum69Number(int num) {
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '9') {
                sb.setCharAt(i, '9');
                break;
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public static int largestAltitude(int[] gain) {
        int max = 0;
        for (int i = 0, total = 0; i < gain.length; i++)
            max = Math.max(max, total += gain[i]);
        return max;
    }

//    public List<List<Integer>> generate(int numRows) {
//        List<List<Integer>> result = new ArrayList<>();
//
//        //iterate number of rows
//        for(int i = 0; i<numRows; i++){
//
//            for(int j = i; j>=0; j--){
//
//            }
//        }
//    }

    public static int fib(int n) {
        int tail = 0, head = 1, compute = 0;
        for (int i = 0; i < n; i++) {
            compute = tail + head;
            tail = head;
            head = compute;
        }
        return tail;
    }

    public static int numOfStrings(String[] patterns, String word) {
        int result = 0;
        for (String s : patterns)
            if (word.contains(s)) result++;
        return result;
    }

    public static int[][] flipAndInvertImage(int[][] image) {
        for (int row = 0; row < image.length; row++) {
            for (int column = 0; column < image[row].length / 2; column++) {
                int temp = image[row][column];
                image[row][column] = image[row][image[row].length - 1 - column];
                image[row][image[row].length - 1 - column] = temp;
            }

            for (int column = 0; column < image[row].length; column++) {
                image[row][column] ^= image[row][column];
            }
        }

        return image;
    }

    public static int countPairs(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1 + i; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    if ((i * j) % k == 0) ++result;
                }
            }
        }
        return result;
    }

    public static int uniqueMorseRepresentations(String[] words) {
        String[] morse = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--"
                , "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Set<String> concatMorse = new HashSet<>();
        for (String word : words) {
            StringBuilder s = new StringBuilder();
            for (char ch : word.toCharArray()) {
                s.append(morse[ch - 'a']);
            }
            concatMorse.add(s.toString());
        }

        return concatMorse.size();
    }

    public static String replaceDigits(String s) {
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isAlphabetic(c)) {
                int val = (s.charAt(i - 1) + c - '0');
                result[i] = (char) val;
            } else {
                result[i] = c;
            }
        }

        return String.valueOf(result);
    }

    public String firstPalindrome(String[] words) {
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            if (sb.reverse().toString().equals(words[i])) return words[i];
        }
        return "";
    }

    public static List<Integer> targetIndices(int[] nums, int target) {
        int count = 0, lessthan = 0;
        for (int n : nums) {
            if (n == target) count++;
            if (n < target) lessthan++;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(lessthan++);
        }
        return result;
    }

    public String truncateSentence(String s, int k) {
        String[] result = new String[k];
        String[] sArr = s.split(" ");
        for (int i = 0; i < k; i++) result[i] = sArr[i];
        return String.join(" ", result);
    }

//    public static List<List<Integer>> generate(int numRows) {
//        List<List<Integer>> result = new ArrayList<>();
//        for(int i = 0; i< numRows; i++){
//            List<Integer> holder = new ArrayList<>();
//            int num = 1;
//            for(int j=0; j<=i; j++){
//                num = num*(i - j)/(j + 1);
//                holder.add(num);
//            }
//            result.add(holder);
//        }
//        return result;
//    }

//    public static int maxDepth(String s) {
//        int result=Integer.MIN_VALUE, ctr = 0;
//        for(int i=0; i<s.length(); i++){
//            if(s.charAt(i) == '('){
//                ctr++;
//                result = Math.max(result, ctr);
//            }else if(s.charAt(i) == ')'){
//                ctr--;
//            }
//        }
//
//        return result;
//    }

//    public static int countKDifference(int[] nums, int k) {
//        var n = nums.length;
//        var ans = 0;
//
//        var counts = new int[100 + 1];
//        for (var num : nums) {
//            if (num > k)
//                ans += counts[num - k]; // [1,2], k=1
//            if (num + k < 101)
//                ans += counts[k + num]; // [2,1], k=1
//            counts[num]++;
//        }
//        return ans;
//    }


//    public static int[] getConcatenation(int[] nums) {
//        int[] answer = new int[nums.length * 2];
//        for(int i=0; i< nums.length; i++){
//            answer[i] = nums[i];
//            answer[i + nums.length] = nums[i];
//        }
//        return answer;
//    }

//    public static String addBinary(String a, String b) {
//
//        int iterator = Math.max(a.length(), b.length()) - 1;
//        char remainder = '0';
//        StringBuilder answer = new StringBuilder();
//        for(int j=0; j< iterator ; j++) {
//            char charA = a.charAt(j);
//            char charB = b.charAt(j);
//
//            String initial = String.valueOf(Math.abs(charA - charB));
//            answer.insert(0,(Math.abs(initial.charAt(0) - remainder)));
//
//            if(initial.equals("0") && charA == '1'
//                    || initial.equals("1") && remainder == '1'){
//                remainder = '1';
//            }else{
//                remainder = '0';
//            }
//
//            if(remainder == '1' && j==iterator-1){
//                answer.insert(0, "1");
//            }
//        }
//        return answer.toString();
//    }


//    public static int strStr(String haystack, String needle) {
//        if ("".equals(needle)) return 0;
//
//        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
//
//            int ctr = needle.length();
//            for(int j = 0; j< needle.length(); j++){
//                if(haystack.charAt(i+j) != needle.charAt(j)) break;
//                ctr--;
//            }
//
//            if(ctr == 0) return i;
//        }
//
//        return -1;
//    }
//
//    private static boolean matches(int index, String haystack, String needle){
//        for(int i = 0; i<needle.length(); i++){
//            if(haystack.charAt(i + index) != needle.charAt(i)){
//                return false;
//            }
//        }
//
//        return true;
//    }


//	public static int removeDuplicates(int[] nums) {
//		//Check num array if null or empty return 0
//		//Check if array length is equals to 1 then return 1
//		if(nums == null || nums.length == 0) return 0;
//		if(nums.length == 1) return 1;
//
//		//We start with 1 since we already validated at the top that array has at least 1 numbers
//		int countOfDistinctNumbers = 1;
//
//		//Were going to use tail comparison
//		int tail = 0;
//		for(int i=1; i<nums.length; i++){
//			if(nums[i] != nums[tail]){
//				nums[++tail] = nums[i];
//				countOfDistinctNumbers++;
//			}
//		}
//
//		return countOfDistinctNumbers;
//	}

//	public int maxProduct(String[] words) {
//
//		for(int i=0; i<words.length; i++){
//			for(int j=i+1; j<words.length; j++){
//				if(hasSimilarity(words[i] , words[j])){
//					continue;
//				}
//			}
//		}
//	}
//
//	public static boolean hasSimilarity(String s1, String s2){
//		char[] charArray = s1.toCharArray();
//		for(int i=0; i<s1.length(); i++){
//			if(s2.contains(String.valueOf(charArray[i]))){
//				return true;
//			}
//		}
//		return false;
//	}

}
