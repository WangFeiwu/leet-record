package com.study.project004;

class Solution {
    // O(log min(m,n))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保持nums1元素较少
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int len1 = nums1.length, len2 = nums2.length;
        // 划中分线的时候，算出左边元素的个数
        // 如果是奇数，让左边多一个；如果是偶数，两边一样多
        // 比如总数5个和6个，左边都是3个
        int leftCount = (len1 + len2 + 1) / 2;
        int l = 0, r = len1;
        //
        // 核心代码在这个循环中，对nums1进行二分，左右调整位置找到最后一个小于num2的零界点
        while (l < r) {
            int nums1LeftCount = (l + r + 1) / 2;
            int nums2LeftCount = leftCount - nums1LeftCount;
            if (nums1[nums1LeftCount - 1] <= nums2[nums2LeftCount]) {
                // 下一次二分的数组为nums1的nums1LeftCount~r之间
                l = nums1LeftCount;
            }else {
                // 下一次二分的数组为nums1的l~nums1LeftCount-1之间
                r = nums1LeftCount - 1;
            }
        }
        int nums1LeftCount = l;
        int nums2LeftCount = leftCount - nums1LeftCount;
        int maxLeft, minRight;
        // maxLeft一定有
        if (nums1LeftCount == 0) {
            maxLeft = nums2[nums2LeftCount - 1];
        } else if (nums2LeftCount == 0) {
            maxLeft = nums1[nums1LeftCount - 1];
        } else {
            maxLeft = Math.max(nums1[nums1LeftCount - 1], nums2[nums2LeftCount - 1]);
        }
        // 总数为偶数的时候，中位数为（左边最大+右边最小）/2
        // 总数为奇数的时候，中位数为左边最大
        if ((len1 + len2) % 2 != 0) {
            return maxLeft;
        } else {
            // minRight不一定有，在总数为偶数的时候minRight则一定有，这个时候再算minRight
            if (nums1LeftCount == len1) {
                minRight = nums2[nums2LeftCount];
            } else if (nums2LeftCount == len2) {
                minRight = nums1[nums1LeftCount];
            } else {
                minRight = Math.min(nums1[nums1LeftCount], nums2[nums2LeftCount]);
            }
            return (maxLeft + minRight) / 2d;
        }
    }

    // O(min(m,n))
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 保持nums1元素较少
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int len1 = nums1.length, len2 = nums2.length;
        // 划中分线的时候，算出左边元素的个数
        // 如果是奇数，让左边多一个；如果是偶数，两边一样多
        // 比如总数5个和6个，左边都是3个
        int leftCount = (len1 + len2 + 1) / 2;
        int nums1LeftCount = 0, nums2LeftCount = leftCount - nums1LeftCount;
        while (nums1LeftCount <= len1) {
            // 看中分线左边，如果nums1分到了i个元素，那么nums2就可以分到leftCount-nums1LeftCount个元素
            nums2LeftCount = leftCount - nums1LeftCount;
            // nums1LeftCount和nums2LeftCount可以分别表示两个数组在中分线后的第一个元素的下标
            // 需要保证中分线左边的元素都小于右边的
            // 因为两个数组分别有序，同一个数组的左边一定小于右边
            // 只需要满足不同数组的左边最后一个小于右边第一个
            // 即nums1[nums1LeftCount-1]<nums2[nums2LeftCount] && nums2[nums2LeftCount-1]<nums1[nums1LeftCount]
            boolean b1 = false;
            boolean b2 = false;
            if (nums1LeftCount - 1 < 0 || nums2LeftCount >= len2) {
                b1 = true;
            } else if (nums1[nums1LeftCount - 1] <= nums2[nums2LeftCount]) {
                b1 = true;
            }
            if (nums2LeftCount - 1 < 0 || nums1LeftCount >= len1) {
                b2 = true;
            } else if (nums2[nums2LeftCount - 1] <= nums1[nums1LeftCount]) {
                b2 = true;
            }
            if (b1 && b2) {
                break;
            } else {
                nums1LeftCount++;
            }
        }
        int maxLeft, minRight;
        // maxLeft一定有
        if (nums1LeftCount == 0) {
            maxLeft = nums2[nums2LeftCount - 1];
        } else if (nums2LeftCount == 0) {
            maxLeft = nums1[nums1LeftCount - 1];
        } else {
            maxLeft = Math.max(nums1[nums1LeftCount - 1], nums2[nums2LeftCount - 1]);
        }
        // 总数为偶数的时候，中位数为（左边最大+右边最小）/2
        // 总数为奇数的时候，中位数为左边最大
        if ((len1 + len2) % 2 == 0) {
            // minRight不一定有，在总数为偶数的时候minRight则一定有，这个时候再算minRight
            if (nums1LeftCount == len1) {
                minRight = nums2[nums2LeftCount];
            } else if (nums2LeftCount == len2) {
                minRight = nums1[nums1LeftCount];
            } else {
                minRight = Math.min(nums1[nums1LeftCount], nums2[nums2LeftCount]);
            }
            return (maxLeft + minRight) / 2d;
        } else {
            return maxLeft;
        }
    }

    // O(m+n)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length, len3 = len1 + len2;
        int[] nums3 = new int[len3];
        int point1 = 0, point2 = 0;
        for (int i = 0; i < len3; i++) {
            if (point1 == len1) {
                // nums1遍历完了，把nums2剩下的直接放进来
                if (point2 < len2) {
                    nums3[i] = nums2[point2++];
                    continue;
                }
            }
            if (point2 == len2) {
                // nums2遍历完了，把nums1剩下的直接放进来
                if (point1 < len1) {
                    nums3[i] = nums1[point1++];
                    continue;
                }
            }

            if (nums1[point1] < nums2[point2]) {
                nums3[i] = nums1[point1++];
            } else {
                nums3[i] = nums2[point2++];
            }

        }
        // 取中位数
        return (nums3[(len3 - 1) / 2] + nums3[len3 / 2]) / 2d;
    }
}