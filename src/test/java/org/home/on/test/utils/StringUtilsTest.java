package org.home.on.test.utils;

import java.text.ParseException;

import br.com.thing.utils.StringUtils;

public class StringUtilsTest {

	public static void main(String[] args) throws ParseException {

		
		String cpfFormat = StringUtils.returnOnlyNumber("118.552.556-42\\]]///");
		String lastDay = StringUtils.lastDay("2022-02");

	}

}
