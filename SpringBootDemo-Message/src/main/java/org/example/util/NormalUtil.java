package org.example.util;

/**
 * @author IN
 * @version 1.0
 * @date 2020-08-18
 */
public class NormalUtil {

	/**
	 * 判断账户类型
	 * @param accountName
	 * @return
	 */
	public static String accountTypeFlag(String accountName) {
		if (accountName.length() >= 7) {
			return "对公";
		}
		return "对私";
	}
}
