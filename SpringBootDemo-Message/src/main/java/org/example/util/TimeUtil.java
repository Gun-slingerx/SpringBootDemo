package org.example.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 *
 * @author yongSen.wang
 * @date 2019/10/30 09:57
 */
public class TimeUtil {

	public final static String TIME_FORMAT_YMDHMS_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public final static String TIME_FORMAT_YMD_DEFAULT = "yyyy-MM-dd";
	public final static String TIME_FORMAT_MD_DEFAULT = "MM-dd";
	public final static String TIME_FORMAT_MD_SPRIT = "MM/dd";
	public final static String TIME_FORMAT_YMD_SPRIT = "yyyy/MM/dd";
	public final static String TIME_FORMAT_YMDHMS = "yyyyMMddHHmmss";
	public final static String TIME_FORMAT_YMD = "yyyyMMdd";
	public final static String TIME_FORMAT_YM = "yyyyMM";
	public final static String TIME_FORMAT_YMD_SPOT = "yyyy.MM.dd";

	public final static int ONE_DAY_SECONDS = 86400;

	/**
	 * 获取当前时间戳，单位：秒
	 *
	 * @return
	 */
	public static int getCurrTimestamp() {
		Long time = System.currentTimeMillis() / 1000;
		return time.intValue();
	}

	/**
	 * 转化时间为字符串
	 *
	 * @param time
	 * @param format 日期显示格式
	 * @return
	 */
	public static String convertTimeToStr(int time, String format) {
		try {
			long t = ((long) time * 1000);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(new Date(t));
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 获取前一天零点时的时间戳
	 *
	 * @return
	 */
	public static int getTimestampByDay(int count) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_YMD);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int day = calendar.get(Calendar.DATE);
			calendar.set(Calendar.DATE, day + count);
			Long time = sdf.parse(sdf.format(calendar.getTime())).getTime() / 1000;
			return time.intValue();
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 获取当前日期的零点时的时间戳
	 *
	 * @return
	 */
	public static int getCurrZeroTimestamp() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_YMD);
			Long time = sdf.parse(sdf.format(new Date())).getTime() / 1000;
			return time.intValue();
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 获取日期的零点时的时间戳
	 *
	 * @return
	 */
	public static int getZeroTimestamp(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_YMD);
			Long time = sdf.parse(sdf.format(date)).getTime() / 1000;
			return time.intValue();
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 获取前一天零点时的时间戳
	 *
	 * @return
	 */
	public static int getYesterdayZeroTimestamp() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_YMD);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int day = calendar.get(Calendar.DATE);
			calendar.set(Calendar.DATE, day - 1);
			Long time = sdf.parse(sdf.format(calendar.getTime())).getTime() / 1000;
			return time.intValue();
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * @param origDate
	 * @return long
	 * @throws @Description: Date 转化为 秒
	 * @author yubo
	 * @date 2018年4月10日下午5:12:06
	 */
	public static long convertDateToTimeSecond(Date origDate) {
		return origDate.getTime() / 1000;
	}

	/**
	 * @param origDate date
	 * @return int
	 * @throws @Description: Date 转化为 秒 Int类型
	 * @author muyonghui
	 * @date 2018年4月10日下午5:12:06
	 */
	public static int convertDateToTimeSecondInt(Date origDate) {
		if (origDate == null) {
			return 0;
		}
		Long time = origDate.getTime() / 1000;
		return time.intValue();
	}

	/**
	 * @param origTimeSecond
	 * @param pattern
	 * @return String
	 * @throws @Description: 秒转化为指定pattern的Date String
	 * @author yubo
	 * @date 2018年4月10日下午5:12:38
	 */
	public static String convertTimeSecondToDateStr(long origTimeSecond, String pattern) {
		return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(origTimeSecond * 1000));
	}

	/**
	 * 根据格式得到格式化后的日期
	 *
	 * @param currDate 要格式化的日期
	 * @param format   日期格式，如yyyy-MM-dd
	 * @return Date 返回格式化后的日期，格式由参数<code>format</code>定义，如yyyy-MM-dd，如2006-02-15
	 * @see SimpleDateFormat#parse(String)
	 */
	public static Date getFormatDate(String currDate, String format) {
		SimpleDateFormat dtFormatdB = null;
		try {
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.parse(currDate);
		} catch (Exception e) {
			dtFormatdB = new SimpleDateFormat(TIME_FORMAT_YMD_DEFAULT);
			try {
				return dtFormatdB.parse(currDate);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	/**
	 * 根据格式得到格式化后的时间
	 *
	 * @param currDate 要格式化的时间
	 * @param format   时间格式，如yyyy-MM-dd HH:mm:ss
	 * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
	 * @see SimpleDateFormat#parse(String)
	 */
	public static Date getFormatDateTime(String currDate, String format) {
		SimpleDateFormat dtFormatdB = null;
		try {
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.parse(currDate);
		} catch (Exception e) {
			dtFormatdB = new SimpleDateFormat(TIME_FORMAT_YMDHMS_DEFAULT);
			try {
				return dtFormatdB.parse(currDate);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	/**
	 * int 秒转为 java.util.Date
	 *
	 * @return
	 */
	public static Date getDateParseIntTimeSecond(int sourceTimeSecond) {
		String tempTimeSecond = convertTimeSecondToDateStr(sourceTimeSecond, TIME_FORMAT_YMDHMS_DEFAULT);
		Date targetTime = getFormatDateTime(tempTimeSecond, TIME_FORMAT_YMDHMS_DEFAULT);
		return targetTime;
	}

	/**
	 * 获取2个日期的零点时间间隔list
	 *
	 * @return
	 */
	public static List<Integer> getZeroTimeList(int beginDayZeroTime, int endDayZeroTime) {
		List<Integer> timeList = new ArrayList<>();
		// 计算有几天
		int num = endDayZeroTime - beginDayZeroTime / ONE_DAY_SECONDS;
		while (true) {
			timeList.add(beginDayZeroTime);
			if (beginDayZeroTime == endDayZeroTime) {
				break;
			}
			beginDayZeroTime += ONE_DAY_SECONDS;
		}
		return timeList;
	}

	/**
	 * int 秒转为 java.util.Date
	 *
	 * @return
	 */
	public static String getDateStrParseIntTimeSecond(int sourceTimeSecond, String format) {
		return convertTimeSecondToDateStr(sourceTimeSecond, format);
	}
}
