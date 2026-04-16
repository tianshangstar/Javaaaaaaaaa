package com.evan.javaaaaaaaaa.algorithm.other;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 埃氏筛 - 求素数
 */
public class Sieve {

    public static List<Integer> sieve(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n < 2) {
            return primes;
        }
        boolean[] composite = new boolean[n + 1]; // composite[i] = true表示合数
        for (int i = 2; i <= n; i++) {
            if (!composite[i]) {
                for (int j = i * i; j <= n; j += i) {
                    // 为什么这里要从i * i开始呢？
                    // 因为在之前的循环中，已经将小于i的质数的倍数标记为合数了，所以从i * i开始标记即可，避免重复标记。
                    composite[j] = true;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!composite[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        System.err.println(gson.toJson(sieve(10)));
        System.err.println(gson.toJson(sieve(20)));
        System.err.println(gson.toJson(sieve(50)));
        System.err.println(gson.toJson(sieve(100)));
        System.err.println(gson.toJson(sieve(1000)));
    }

}
