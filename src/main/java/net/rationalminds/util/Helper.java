/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rationalminds.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.spec.OAEPParameterSpec;

/**
 *
 * @author Vaibhav Singh
 */
public class Helper {

	public static int isDateLieral(String token) {
		/*
		 * the bigest date element is 9 character long like 'wednesday and
		 * 'spetember''
		 */
		String s = token.toLowerCase();
		if (token.length() > 9) {
			return Dictionary.NOT_DATE_LITERAL;
		}
		// check possibility of numeric sequence of two charcters
		if (s.length() <= 2 && isDigit(s)) {
			return Dictionary.DIGIT_LITERAL;
		}
		// check possibility of numeric sequence of two charcters
		if (s.length() <= 2 && isDigit(s)) {
			return Dictionary.DIGIT_LITERAL;
		}
		// check possibility of numeric sequence of three charcters for
		// 'milliseconds'
		if (s.length() == 3 && isDigit(s)) {
			return Dictionary.THREE_DIGIT_LITERAL;
		}
		// check possibility of numeric sequence of four charcters for 'year'
		if (s.length() == 4 && isDigit(s)) {
			return Dictionary.FOUR_DIGIT_LITERAL;
		}
		if (s.length() <= 2) {
			return Dictionary.NOT_DATE_LITERAL;
		}
		if (s.contains("k") || s.contains("q") || s.contains("x") || s.contains("z")) {
			return Dictionary.NOT_DATE_LITERAL;
		}
		if (isWeekDayLiteral(s)) {
			return Dictionary.WEEKDAY_LITERAL;
		}
		if (isMonthLiteral(s)) {
			return Dictionary.MONTH_LITERAL;
		}
		return Dictionary.NOT_DATE_LITERAL;
	}

	/**
	 *
	 * @param s
	 * @return
	 */
	private static boolean isMonthLiteral(String s) {
		// no month contains letter 'w'
		if (s.contains("w")) {
			return false;
		}
		// short month represenation do not have 'h' & 'i'
		if (s.length() == 3 && (s.contains("h") || s.contains("i"))) {
			return false;
		}
		for (String c : Dictionary.MONTH_SHORT) {
			if (s.equals(c)) {
				return true;
			}
		}
		for (String c : Dictionary.MONTH_FULL) {
			if (s.equals(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param s
	 * @return
	 */
	private static boolean isWeekDayLiteral(String s) {
		if (s.contains("b") || s.contains("c") || s.contains("g") || s.contains("j") || s.contains("l") || s.contains("p") || s.contains("v")) {
			return false;
		}
		for (String c : Dictionary.WEEKDAY_SHORT) {
			if (s.equals(c)) {
				return true;
			}
		}
		for (String c : Dictionary.WEEKDAY_FULL) {
			if (s.equals(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * fastest way to check whether a given string is numeric
	 *
	 * @param s
	 * @return
	 */
	public static boolean isDigit(String s) {
		int i = 0;
		int length = s.length();
		for (; i < length; i++) {
			char c = s.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * @param s
	 * @return
	 */
	public static boolean isDigit(char c) {
		if (c <= '/' || c >= ':') {
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param c
	 * @return
	 */
	public static boolean isDelimeter(char c) {
		if (c == '\\') {
			return true;
		}
		if (c == '/') {
			return true;
		}
		if (c == '-') {
			return true;
		}
		if (c == ' ') {
			return true;
		}
		if (c == '.') {
			return true;
		}
		if (c == ',') {
			return true;
		}
		if (c == ':') {
			return true;
		}
		return false;
	}

	public static List<String> testDataForDateFormats() {
		List<String> dPList = new ArrayList<String>();
		for (String str : Dictionary.PATTERN) {
			String testData = "";
			for (int i = 0; i < str.length(); i++) {
				testData = testData + getTestDataForChar(str.charAt(i));
			}
			dPList.add(testData);
		}
		List<String> tPlist = new ArrayList<String>();
		for (String str : Dictionary.TIME_PATTERN) {
			String testData = "";
			for (int i = 0; i < str.length(); i++) {
				testData = testData + getTestDataForChar(str.charAt(i));
			}
			tPlist.add(testData);
		}
		List<String> dTPlist = new ArrayList<String>();
		for (String dateFrgmt : dPList) {
			for (String timeFgmnt : tPlist) {
				dTPlist.add(dateFrgmt + " " + timeFgmnt);
			}
		}
		dPList.addAll(dTPlist);
		return dPList;
	}

	private static String getTestDataForChar(char c) {
		if (c == 'D') {
			int i = ThreadLocalRandom.current().nextInt(0, 10);
			return String.valueOf(i);
		}
		if (c == '*') {
			int i = ThreadLocalRandom.current().nextInt(1, 6);
			return String.valueOf(getTestCaseDelimeter(i));

		}
		if (c == 'M') {
			int i = ThreadLocalRandom.current().nextInt(1, 25);
			return getTestMonth(i);
		}
		return String.valueOf(c);
	}

	private static char getTestCaseDelimeter(int i) {
		switch (i) {
		case 1:
			return '\\';
		case 2:
			return '/';
		case 3:
			return '-';
		case 4:
			return ' ';
		case 5:
			return '.';
		case 6:
			return ',';
		}
		return '~';
	}

	private static String getTestMonth(int i) {
		switch (i) {
		case 1:
			return "january";
		case 2:
			return "february";
		case 3:
			return "march";
		case 4:
			return "april";
		case 5:
			return "may";
		case 6:
			return "june";
		case 7:
			return "july";
		case 8:
			return "august";
		case 9:
			return "september";
		case 10:
			return "october";
		case 11:
			return "november";
		case 12:
			return "december";
		case 13:
			return "jan";
		case 14:
			return "feb";
		case 15:
			return "mar";
		case 16:
			return "apr";
		case 17:
			return "may";
		case 18:
			return "jun";
		case 19:
			return "jul";
		case 20:
			return "aug";
		case 21:
			return "sep";
		case 22:
			return "oct";
		case 23:
			return "nov";
		case 24:
			return "dec";
		}
		return "";
	}
}
