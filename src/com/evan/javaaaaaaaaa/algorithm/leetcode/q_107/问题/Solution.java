package com.evan.javaaaaaaaaa.algorithm.leetcode.q_107.问题;

import com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_104.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    /**
     * 1 层序
     * 2 反序  最下的层先返回
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        // use as stack
        LinkedList<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        // 最下一层最闲发挥，符合stack的使用场景
//        Deque<List<TreeNode>> stack = new LinkedList<>();

        // 层序遍历，首先考虑使用不同的队列来保存每一层的遍历结果
        List<TreeNode> pre = new ArrayList<>();

        pre.add(root);
        result.push(Arrays.asList(root.val));

        List<TreeNode> cur;

        // 层序遍历考虑两件事
        // 1 当前层遍历完了没有
        // 2 如何保存不同层的结果
        while (!pre.isEmpty()) {
            cur = new ArrayList();
            // 遍历上一层，把对应上一层每个node的左右节点都放到cur
            for (TreeNode preNode : pre) {

                if (preNode.left != null)
                    cur.add(preNode.left);
                if (preNode.right != null)
                    cur.add(preNode.right);

            }

            if (!cur.isEmpty()) {
                result.push(
                        cur.stream().map(it -> it.val).collect(Collectors.toList())
                );
            }

            // 转向下一层
            pre = cur;
        }

        return result;
    }
}
