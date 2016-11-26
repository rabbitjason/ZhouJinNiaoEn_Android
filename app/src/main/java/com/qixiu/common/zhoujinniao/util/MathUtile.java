package com.qixiu.common.zhoujinniao.util;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * 
 * 作者:蔡名雨 功能:数据的工具类 创建时间:2016-5-6 下午3:47:31 参与者:
 */

public class MathUtile {
	/**
	 * 随机某一区间的整数值
	 * 
	 * @param min
	 *            最小数
	 * @param max
	 *            最大数
	 * @return 【min,max】
	 */
	public static int getRandomNumber(int min, int max) {
		return Math.abs(new Random().nextInt() % (max - min + 1)) + min;
	}

	public static int[] createRandomArray(int min, int max, int size) {
		int[] list = new int[size];
		int count = 0; // 计数
		int num = 0; // 随机数
		int i;
		// 初始化数组
		for (i = 0; i < list.length; i++) {
			list[i] = -1;
		}
		// 填充数组元素
		do {
			num = (int) MathUtile.getRandomNumber(min, max);
			// 判断元素是否存在数组中
			for (i = 0; i < list.length; i++) {
				if (list[i] == num) {
					break;
				}
			}
			// 不存在则装入
			if (i >= list.length) {
				list[count] = num;

				count++;
			}
		} while (count < size);

		return list;
	}

	public static int[] sorting(int min, int max, int size) {
		int[] list = createRandomArray(min, max, size);
		int temp;
		// 第一层循环:表明比较的次数, 比如 length 个元素,比较次数为 length-1 次（肯定不需和自己比）
		for (int i = 0; i < list.length - 1; i++) {
			for (int j = list.length - 1; j > i; j--) {
				if (list[j] < list[j - 1]) {
					temp = list[j];
					list[j] = list[j - 1];
					list[j - 1] = temp;
				}
			}
		}

		return list;
	}
}
