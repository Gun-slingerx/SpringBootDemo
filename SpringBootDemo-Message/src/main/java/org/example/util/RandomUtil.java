package org.example.util;

import java.util.Random;

/**
 * 生成随机字符串
 *
 * @author dingjian
 * @date 2019/03/28 17:03:06
 */
public class RandomUtil {
	private static Random random = new Random();

	/**
	 * 生成随机的字符串
	 *
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		if (length < 0) {
			length = 6;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			boolean isChar = random.nextInt(2) % 2 == 0;
			if (isChar) {
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				builder.append((char) (choice + random.nextInt(26)));
			} else {
				builder.append(Integer.toString(random.nextInt(10)));
			}
		}
		return builder.toString();
	}

	/**
	 * 生成随机数字
	 *
	 * @param length
	 * @return
	 */
	public static String getRandomInteger(int length) {
		if (length < 0) {
			length = 6;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			builder.append(Integer.toString(random.nextInt(10)));
		}
		return builder.toString();
	}
}
