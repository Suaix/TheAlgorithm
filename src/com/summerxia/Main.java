package com.summerxia;

import com.summerxia.tree.TreeExecutor;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
	    AlgorithmsExecutor executor = new LeetCode100Executor();
	    executor.execAlgorithmCase();
//        test(25,9);
    }

    private static void test(int m, int n){
        int[] d = new int[Math.max(m, n)];
        for (int i = 0; i < d.length; i++) {
            int temp = i;
            while (temp > 0){
                d[i] = temp % 10;
                temp /= 10;
            }
        }

        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = d[i] + d[j];
                System.out.print("{"+sum[i][j]+"}");
            }
            System.out.println();
        }
    }
}
