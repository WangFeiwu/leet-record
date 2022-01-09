package com.study.project001;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 使用HashMap存放遍历过的元素
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                // 满足要求直接返回
                return new int[]{map.get(target - nums[i]), i};
            }
            // 没找到，将数组中的元素放入HashMap，key为当前元素，value为数组下标
            // 继续遍历
            map.put(nums[i], i);

        }
        return null;
    }
}
