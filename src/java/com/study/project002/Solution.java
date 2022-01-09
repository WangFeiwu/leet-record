package com.study.project002;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 初始化header
        ListNode res = new ListNode();
        // 往后移动的指针
        ListNode point = res;
        // 是否有进位
        boolean carry = false;
        // 三个条件满足其一就可以继续进行
        while (l1 != null || l2 != null || carry) {
            // 初始化三个数，分别代表l1的值、l2的值、进位
            int a = 0, b = 0, c = 0;
            if (l1 != null) {
                a = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                b = l2.val;
                l2 = l2.next;
            }
            // 如果有进位，则加1，并重置carry
            if (carry) {
                c = 1;
                carry = false;
            }
            int count = a + b + c;
            // 如果两数相加超过9，表示需要进位
            if (count > 9) {
                count = count - 10;
                carry = true;
            }

            point.next = new ListNode(count);
            point = point.next;
        }
        return res.next;
    }
}