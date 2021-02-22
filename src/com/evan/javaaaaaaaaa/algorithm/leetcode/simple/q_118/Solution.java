package com.evan.javaaaaaaaaa.algorithm.leetcode.simple.q_118;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();

        List<Integer> first = generateLine(null, 0);
        results.add(first);
        for (int i = 1; i < numRows; i++) {
            results.add(generateLine(results.get(i - 1), i));
        }

        return results;

    }

    private List<Integer> generateLine(List<Integer> last, int lineNum) {
        List<Integer> line = new ArrayList<>();

        for (int i = 0; i <= lineNum; i++) {
            if (i == lineNum || i == 0) {
                line.add(1);
            } else {
                line.add(last.get(i - 1) + last.get(i));
            }

        }

        return line;
    }


    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }
}
