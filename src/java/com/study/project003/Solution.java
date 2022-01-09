package com.study.project003;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        // 用HashMap存储遍历过的元素的位置，减少时间复杂度
        Map<Character, Integer> map = new HashMap<>();
        for (int l = 0, r = 0; r < s.length(); r++) {
            Integer index = map.get(s.charAt(r));
            // 如果重复了，则将左指针指向重复位置的下一位
            // 当然，重复的元素位置必须在l~r的范围之内才算
            if (index != null && index >= l) {
                l = index + 1;
            }
            map.put(s.charAt(r), r);
            max = Math.max(max, r - l + 1);
        }
        return max;
    }


    // 超过时间限制~~~
    public int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = s.length();
        for (int i = 0; i < max; i++) {
            char key = s.charAt(i);
            Integer x = map.get(key);
            if (x != null) {
                int max1 = Math.max(lengthOfLongestSubstring1(s.substring(0, i)),
                        lengthOfLongestSubstring1(s.substring(i, max)));
                if (i == x + 1) {
                    max = max1;
                }else {
                    int max2 = Math.max(lengthOfLongestSubstring1(s.substring(0, x + 1)),
                            lengthOfLongestSubstring1(s.substring(x + 1, max)));
                    max = Math.max(max1, max2);
                }
                break;
            } else {
                map.put(key, i);
            }
        }
        return max;
    }
}