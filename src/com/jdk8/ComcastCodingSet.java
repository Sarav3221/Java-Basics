package com.jdk8;

import java.util.*;

public class ComcastCodingSet {

    public static void main(String[] args) {
        System.out.println("=== Comcast-style Java Coding Set (Full Logs Mode) ===\n");

        problemHeader(1, "Two Sum");
        twoSumExample();

        problemHeader(2, "Best Time to Buy and Sell Stock (single transaction)");
        bestTimeToBuySellExample();

        problemHeader(3, "Print Unique Elements in Array");
        printUniqueElementsExample();

        problemHeader(4, "Longest Substring Without Repeating Characters");
        longestSubstringWithoutRepeatingExample();

        problemHeader(5, "String Manipulation: IP to Hex Example");
        ipToHexExample();

        problemHeader(6, "Count Occurrences / Frequency in String / Array");
        countFrequenciesExample();

        problemHeader(7, "Remove Duplicates from List (retain order)");
        removeDuplicatesFromListExample();

        problemHeader(8, "Anagram Check");
        anagramCheckExample();

        problemHeader(9, "Reverse String / Reverse Words");
        reverseStringAndWordsExample();

        problemHeader(10, "Basic backend / REST-API explanation (conceptual)");
        restApiExplanationExample();

        problemHeader(11, "Count Substring Occurrences (including overlaps)");
        countSubstringOccurrencesExample();

        problemHeader(12, "Dynamic Programming Example: Maximum Subarray (Kadane's)");
        kadaneExample();

        problemHeader(13, "Greedy/Two-pointer Example: Pair with given sum in sorted array");
        twoPointerPairSumExample();

        problemHeader(14, "Collections & Java fundamentals quick demo");
        collectionsFundamentalsExample();

        problemHeader(15, "Utility: Palindrome check & Vowel count & Remove duplicates from string");
        utilitiesExample();

        System.out.println("\n=== All problems executed. Practice explanations above. Good luck! ===");
    }

    // ----------------------------------------
    // Helpers
    // ----------------------------------------
    private static void problemHeader(int num, String title) {
        System.out.println("--------------------------------------------------");
        System.out.println("Problem " + num + ": " + title + "\n");
    }

    // ----------------------------------------
    // Problem 1: Two Sum
    // ----------------------------------------
    private static void twoSumExample() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println("Problem: Given array and target, return indices of two numbers that add up to target.");
        System.out.println("Approach: HashMap for value -> index, single pass O(n) time.");

        System.out.println("Input: " + Arrays.toString(nums) + ", target=" + target);
        int[] res = twoSum(nums, target);
        if (res != null) {
            System.out.println("Result indices: [" + res[0] + ", " + res[1] + "]");
            System.out.println("Values: " + nums[res[0]] + " + " + nums[res[1]] + " = " + target);
        } else {
            System.out.println("No solution found.");
        }
        System.out.println();
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            System.out.println("Index=" + i + ", value=" + nums[i] + ", need=" + need);
            if (map.containsKey(need)) {
                System.out.println("Found need in map at index " + map.get(need));
                return new int[]{map.get(need), i};
            }
            map.put(nums[i], i);
            System.out.println("Putting value " + nums[i] + " into map with index " + i);
        }
        return null;
    }

    // ----------------------------------------
    // Problem 2: Best Time to Buy and Sell Stock
    // ----------------------------------------
    private static void bestTimeToBuySellExample() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Problem: Max profit with one buy and one sell (buy before sell). Approach: track min price and max profit.");
        System.out.println("Input prices: " + Arrays.toString(prices));

        int profit = maxProfit(prices);
        System.out.println("Max profit achievable: " + profit + "\n");
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                System.out.println("New min price at day " + i + " = " + minPrice);
            }
            int profit = prices[i] - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
                System.out.println("New maxProfit=" + maxProfit + " (sell at day " + i + ")");
            }
        }
        return maxProfit;
    }

    // ----------------------------------------
    // Problem 3: Print Unique Elements in Array
    // ----------------------------------------
    private static void printUniqueElementsExample() {
        Integer[] arr = {1, 2, 3, 2, 4, 1, 5};
        System.out.println("Problem: Print unique elements from array in original order. Approach: LinkedHashSet to preserve order.");
        System.out.println("Input: " + Arrays.toString(arr));

        List<Integer> unique = uniqueElements(Arrays.asList(arr));
        System.out.println("Unique elements (order preserved): " + unique + "\n");
    }

    public static <T> List<T> uniqueElements(List<T> list) {
        Set<T> set = new LinkedHashSet<>();
        for (T item : list) {
            System.out.println("Checking item: " + item + ", seen before? " + set.contains(item));
            set.add(item);
        }
        return new ArrayList<>(set);
    }

    // ----------------------------------------
    // Problem 4: Longest Substring Without Repeating Characters
    // ----------------------------------------
    private static void longestSubstringWithoutRepeatingExample() {
        String s = "abcabcbb";
        System.out.println("Problem: Longest substring without repeating characters. Approach: sliding window with index map.");
        System.out.println("Input: \"" + s + "\"");

        String longest = longestUniqueSubstr(s);
        System.out.println("Longest unique substring: \"" + longest + "\", length=" + longest.length() + "\n");
    }

    public static String longestUniqueSubstr(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int start = 0, maxLen = 0, startOfMax = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println("Inspecting char '" + c + "' at index " + i);
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= start) {
                System.out.println("Found duplicate '" + c + "' previously at index " + lastIndex.get(c) + ". Move start from " + start + " to " + (lastIndex.get(c) + 1));
                start = lastIndex.get(c) + 1;
            }
            lastIndex.put(c, i);
            if (i - start + 1 > maxLen) {
                maxLen = i - start + 1;
                startOfMax = start;
                System.out.println("New max length " + maxLen + " from index " + startOfMax + " to " + i);
            }
        }
        return s.substring(startOfMax, startOfMax + maxLen);
    }

    // ----------------------------------------
    // Problem 5: Example string manipulation - convert dotted IP to hex string
    // ----------------------------------------
    private static void ipToHexExample() {
        String ip = "1.0.1.1";
        System.out.println("Problem: Convert dotted decimal IP segments to hexadecimal representation. Approach: split, parse, toHex.");
        System.out.println("Input IP: " + ip);

        try {
            String hex = ipToHex(ip);
            System.out.println("Hex output (joined): " + hex + "\n");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage() + "\n");
        }
    }

    public static String ipToHex(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) throw new IllegalArgumentException("Invalid IP format");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            int val = Integer.parseInt(parts[i]);
            if (val < 0 || val > 255) throw new IllegalArgumentException("Invalid IP segment: " + parts[i]);
            String hx = Integer.toHexString(val);
            if (hx.length() == 1) hx = "0" + hx;
            sb.append(hx);
            if (i < parts.length - 1) sb.append(":");
            System.out.println("Segment " + parts[i] + " -> 0x" + hx);
        }
        return sb.toString().toUpperCase();
    }

    // ----------------------------------------
    // Problem 6: Count frequencies
    // ----------------------------------------
    private static void countFrequenciesExample() {
        String text = "hello world";
        System.out.println("Problem: Count character frequencies. Input: \"" + text + "\"");

        Map<Character, Integer> freq = charFrequencies(text);
        System.out.println("Frequencies: " + freq + "\n");
    }

    public static Map<Character, Integer> charFrequencies(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue; // skip spaces for clarity
            map.put(c, map.getOrDefault(c, 0) + 1);
            System.out.println("Char '" + c + "' -> count now " + map.get(c));
        }
        return map;
    }

    // ----------------------------------------
    // Problem 7: Remove duplicates from List
    // ----------------------------------------
    private static void removeDuplicatesFromListExample() {
        List<String> list = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        System.out.println("Problem: Remove duplicates from list while preserving insertion order. Input: " + list);

        List<String> distinct = removeDuplicatesPreserveOrder(list);
        System.out.println("Result: " + distinct + "\n");
    }

    public static <T> List<T> removeDuplicatesPreserveOrder(List<T> input) {
        Set<T> seen = new LinkedHashSet<>();
        for (T item : input) {
            System.out.println("Visiting: " + item + ", already seen? " + seen.contains(item));
            seen.add(item);
        }
        return new ArrayList<>(seen);
    }

    // ----------------------------------------
    // Problem 8: Anagram Check
    // ----------------------------------------
    private static void anagramCheckExample() {
        String a = "listen";
        String b = "silent";
        System.out.println("Problem: Check if two strings are anagrams. Input: \"" + a + "\", \"" + b + "\"");
        System.out.println("Approach: sort chars or frequency map. We'll use frequency map.");

        boolean isAnagram = areAnagrams(a, b);
        System.out.println("Are anagrams? " + isAnagram + "\n");
    }

    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] counts = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            counts[s1.charAt(i) - 'a']++;
            counts[s2.charAt(i) - 'a']--;
        }
        for (int c : counts) if (c != 0) return false;
        return true;
    }

    // ----------------------------------------
    // Problem 9: Reverse String / Reverse Words
    // ----------------------------------------
    private static void reverseStringAndWordsExample() {
        String s = "Hello World from Comcast";
        System.out.println("Problem: Reverse full string and reverse words separately. Input: \"" + s + "\"");

        String reversed = new StringBuilder(s).reverse().toString();
        System.out.println("Reversed entire string: \"" + reversed + "\"");

        String[] words = s.split(" ");
        Collections.reverse(Arrays.asList(words));
        String reversedWords = String.join(" ", words);
        System.out.println("Reversed word order: \"" + reversedWords + "\"\n");
    }

    // ----------------------------------------
    // Problem 10: REST API conceptual (print explanation)
    // ----------------------------------------
    private static void restApiExplanationExample() {
        System.out.println("Problem: Explain HTTP methods and when to use them (conceptual).\n");
        System.out.println("GET: retrieve resources, should be idempotent and safe");
        System.out.println("POST: create resources, not necessarily idempotent");
        System.out.println("PUT: update/replace a resource, idempotent");
        System.out.println("PATCH: partial update, not always idempotent");
        System.out.println("DELETE: remove resource, idempotent in effect\n");
    }

    // ----------------------------------------
    // Problem 11: Count substring occurrences (including overlapping)
    // ----------------------------------------
    private static void countSubstringOccurrencesExample() {
        String text = "abababa";
        String pattern = "aba";
        System.out.println("Problem: Count occurrences (including overlaps) of '" + pattern + "' in '" + text + "'.");
        System.out.println("Approach: sliding or indexOf loop moving start by 1 if overlaps allowed.");

        int count = countOverlappingOccurrences(text, pattern);
        System.out.println("Occurrences found: " + count + "\n");
    }

    public static int countOverlappingOccurrences(String text, String pattern) {
        if (pattern.isEmpty()) return 0;
        int count = 0;
        for (int idx = text.indexOf(pattern, 0); idx != -1; idx = text.indexOf(pattern, idx + 1)) {
            System.out.println("Found at index " + idx);
            count++;
        }
        return count;
    }

    // ----------------------------------------
    // Problem 12: Dynamic Programming Example (Kadane's Maximum Subarray)
    // ----------------------------------------
    private static void kadaneExample() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Problem: Maximum subarray sum (Kadane's). Input: " + Arrays.toString(nums));
        System.out.println("Approach: keep currentSum and maxSum, if currentSum drops below 0 reset it.");

        int max = kadane(nums);
        System.out.println("Max subarray sum: " + max + "\n");
    }

    public static int kadane(int[] nums) {
        int maxSoFar = Integer.MIN_VALUE;
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            System.out.println("Index " + i + ", value=" + nums[i] + ", current before=" + current);
            current = Math.max(nums[i], current + nums[i]);
            maxSoFar = Math.max(maxSoFar, current);
            System.out.println("current after=" + current + ", maxSoFar=" + maxSoFar);
        }
        return maxSoFar;
    }

    // ----------------------------------------
    // Problem 13: Two-pointer greedy example (pair sum in sorted array)
    // ----------------------------------------
    private static void twoPointerPairSumExample() {
        int[] sorted = {1, 2, 3, 5, 7, 9};
        int target = 10;
        System.out.println("Problem: Given sorted array, find a pair that sums to target using two pointers. Input: " + Arrays.toString(sorted) + ", target=" + target);
        System.out.println("Approach: left and right pointers, move inward based on sum.");

        int[] pair = twoPointerPair(sorted, target);
        if (pair != null) {
            System.out.println("Found pair: " + pair[0] + ", " + pair[1] + " (values)");
        } else {
            System.out.println("No pair found.");
        }
        System.out.println();
    }

    public static int[] twoPointerPair(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            System.out.println("Checking arr[" + l + "]=" + arr[l] + " + arr[" + r + "]=" + arr[r] + " => sum=" + sum);
            if (sum == target) {
                return new int[]{arr[l], arr[r]};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }

    // ----------------------------------------
    // Problem 14: Collections & Java fundamentals demo
    // ----------------------------------------
    private static void collectionsFundamentalsExample() {
        System.out.println("Problem: Quick demo of List vs Set vs Map and example tradeoffs (performance notes).\n");
        System.out.println("ArrayList: random access O(1), insert at end amortized O(1), insert/remove in middle O(n)");
        System.out.println("LinkedList: good for frequent insert/remove at ends, poor random access O(n)");
        System.out.println("HashSet: O(1) average add/contains/remove, no order guaranteed");
        System.out.println("LinkedHashSet: preserves insertion order");
        System.out.println("TreeSet: sorted set, O(log n) ops");
        System.out.println("HashMap: key->value, average O(1) get/put; LinkedHashMap preserves insertion order; TreeMap is sorted map.\n");

        // small live example
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);
        Set<Integer> set = new HashSet<>(list);
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        System.out.println("List contents: " + list);
        System.out.println("Set contents (constructed from list): " + set);
        System.out.println("Map contents: " + map + "\n");
    }

    // ----------------------------------------
    // Problem 15: Utilities (palindrome check, vowel count, remove duplicates from string)
    // ----------------------------------------
    private static void utilitiesExample() {
        String s = "racecar";
        System.out.println("Problem A: Palindrome check. Input: \"" + s + "\"");
        System.out.println("Approach: two-pointer compare.");
        System.out.println("Is palindrome? " + isPalindrome(s) + "\n");

        String text = "Hello Universe";
        System.out.println("Problem B: Count vowels in: \"" + text + "\"");
        System.out.println("Vowel count: " + countVowels(text) + "\n");

        String dup = "banana";
        System.out.println("Problem C: Remove duplicate chars from string while preserving order. Input: \"" + dup + "\"");
        System.out.println("Result: \"" + removeDuplicateChars(dup) + "\"\n");
    }

    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++; r--; 
        }
        return true;
    }

    public static int countVowels(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    public static String removeDuplicateChars(String s) {
        Set<Character> seen = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
