package com.verde.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	public static String getFolderFormattedDate() {
		Date date = new Date();
		return new SimpleDateFormat("yyyyMMdd").format(date);

	}
}
