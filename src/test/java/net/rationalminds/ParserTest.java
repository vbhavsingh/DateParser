package net.rationalminds;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ParserTest {

	private Parser p = new Parser();

	@Test
	public void testValidPatternDDDDxDDxDD() {
		String pattern = "identify date from text : 2016 06 30";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : 2016 30 06";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testInValidPatternDDDDxDDxDD() {
		String pattern = "identify date from text : 2016 13 30";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	@Test
	public void testValidPatternDDDDxDxDD() {
		String pattern = "identify date from text : 2016 6 30";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testInValidPatternDDDDxDxDD() {
		String pattern = "identify date from text : 2016 1 32";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	@Test
	public void testValidPatternDDDDxDDxD() {
		String pattern = "identify date from text : 2016 06 5";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : 2016 30 5";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testInValidPatternDDDDxDDxD() {
		String pattern = "identify date from text : 2016 99 8";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	@Test
	public void testValidPatternDDDDxDxD() {
		String pattern = "identify date from text : 2016 6 5";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// YYYY MM DD
	@Test
	public void testInValidPatternDDDDxDxD() {
		String pattern = "identify date from text : 2016 0 8";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	// YY MM DD where YY > 31
	@Test
	public void testValidPatternDDxDDxDD() {
		String pattern = "identify date from text : 99 12 17";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// YY MM DD where YY > 31
	@Test
	public void testInValidPatternDDxDDxDD() {
		String pattern = "identify date from text : 30 12 17";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	// YY MM DD where YY > 31
	@Test
	public void testValidPatternDDxDxDD() {
		String pattern = "identify date from text : 99 1 17";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// YY MM DD where YY > 31
	@Test
	public void testInValidPatternDDxDxDD() {
		String pattern = "identify date from text : 30 9 17";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	// YY MM DD where YY > 31
	@Test
	public void testValidPatternDDxDDxD() {
		String pattern = "identify date from text : 99 11 9";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// YY MM DD where YY > 31
	@Test
	public void testInValidPatternDDxDDxD() {
		String pattern = "identify date from text : 99 11 35";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	// YY MM DD where YY > 31
	@Test
	public void testValidPatternDDxDxD() {
		String pattern = "identify date from text : 99 5 9";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// YY MM DD where YY > 31
	@Test
	public void testInValidPatternDDxDxD() {
		String pattern = "identify date from text : 08 9 9";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	// DD MM YYYY & MM DD YYYY
	@Test
	public void testValidPatternDDxDDxDDDD() {
		String pattern = "identify date from text : 01 31 2017";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : 31 01 2017";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// DD MM YYYY
	@Test
	public void testInValidPatternDDxDDxDDDD() {
		String pattern = "identify date from text : 32 01 2017";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	// DD MM YYYY
	@Test
	public void testValidPatternDxDDxDDDD() {
		String pattern = "identify date from text : 5 31 2017";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : 9 13 2017";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// DD MM YYYY
	@Test
	public void testInValidPatternDxDDxDDDD() {
		String pattern = "identify date from text : 5 32 2017";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	// DD MM YYYY
	@Test
	public void testValidPatternDDxDxDDDD() {
		String pattern = "identify date from text : 31 1 2017";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : 13 1 2017";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// DD MM YYYY
	@Test
	public void testInValidPatternDDxDxDDDD() {
		String pattern = "identify date from text : 9 32 2017";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	// DD MM YYYY
	@Test
	public void testValidPatternDxDxDDDD() {
		String pattern = "identify date from text : 9 9 2017";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : 8 9 2017";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	// DD MM YYYY
	@Test
	public void testInValidPatternDxDxDDDD() {
		String pattern = "identify date from text : 9 32 2017";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	@Test
	public void testValidPatternDxDDxDD() {
		String pattern = "identify date from text : 9 11 2017";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : 9 30 2017";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testInValidPatternDxDDxDD() {
		String pattern = "identify date from text : 9 00 2017";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}

	@Test
	public void testValidPatternDxDxDD() {
		String pattern = "identify date from text : 9 9 2017";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text :1 9 2017";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testInValidPatternDxDxDD() {
		String pattern = "identify date from text : 0 1 2017";
		List<LocalDateModel> date = p.parse(pattern);
		Assert.assertTrue(date.size() == 0);
	}


	@Test
	public void testValidPatternDDDDxMxDDxJan() {
		String pattern = "identify date from text : 2017 january 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text :2017 jan 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testValidPatternDDDDxMxDDxFeb() {

		String pattern = "identify date from text : 2017 february 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text :2017 feb 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

	}
	
	@Test
	public void testValidPatternDDDDxMxDDxMarch() {
		String pattern = "identify date from text : 2017 march 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 mar 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxApril() {
		String pattern = "identify date from text : 2017 april 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 apr 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxMay() {
		String pattern = "identify date from text : 2017 may 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxJune() {
		String pattern = "identify date from text : 2017 june 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 jun 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxJuly() {
		String pattern = "identify date from text : 2017 july 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 jul 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxAugust() {
		String pattern = "identify date from text : 2017 august 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 aug 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxSep() {
		String pattern = "identify date from text : 2017 september 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 sep 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxOct() {
		String pattern = "identify date from text : 2017 october 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 oct 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxNov() {
		String pattern = "identify date from text : 2017 november 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 nov 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDDxDec() {
		String pattern = "identify date from text : 2017 december 20";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 dec 20";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testValidPatternDDDDxMxDxJan() {
		String pattern = "identify date from text : 2017 january 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text :2017 jan 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testValidPatternDDDDxMxDxFeb() {

		String pattern = "identify date from text : 2017 february 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text :2017 feb 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

	}
	
	@Test
	public void testValidPatternDDDDxMxDxMarch() {
		String pattern = "identify date from text : 2017 march 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 mar 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxApril() {
		String pattern = "identify date from text : 2017 april 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 apr 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxMay() {
		String pattern = "identify date from text : 2017 may 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxJune() {
		String pattern = "identify date from text : 2017 june 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 jun 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxJuly() {
		String pattern = "identify date from text : 2017 july 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 jul 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxAugust() {
		String pattern = "identify date from text : 2017 august 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 aug 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxSep() {
		String pattern = "identify date from text : 2017 september 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 sep 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxOct() {
		String pattern = "identify date from text : 2017 october 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 oct 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxNov() {
		String pattern = "identify date from text : 2017 november 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 nov 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDDDxMxDxDec() {
		String pattern = "identify date from text : 2017 december 2";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :2017 dec 2";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testValidPatternDDxMxDDxJan() {
		String pattern = "identify date from text : 22 january 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text :22 jan 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testValidPatternDDxMxDDxFeb() {

		String pattern = "identify date from text : 22 february 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text :22 feb 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

	}
	
	@Test
	public void testValidPatternDDxMxDDxMarch() {
		String pattern = "identify date from text : 22 march 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 mar 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxApril() {
		String pattern = "identify date from text : 22 april 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 apr 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxMay() {
		String pattern = "identify date from text : 22 may 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxJune() {
		String pattern = "identify date from text : 22 june 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 jun 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxJuly() {
		String pattern = "identify date from text : 22 july 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 jul 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxAugust() {
		String pattern = "identify date from text : 22 august 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 aug 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxSep() {
		String pattern = "identify date from text : 22 september 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 sep 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxOct() {
		String pattern = "identify date from text : 22 october 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 oct 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxNov() {
		String pattern = "identify date from text : 22 november 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 nov 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternDDxMxDDxDec() {
		String pattern = "identify date from text : 22 december 98";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text :22 dec 98";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testValidPatternMxDDxDDDDxJan() {
		String pattern = "identify date from text : january 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : jan 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}

	@Test
	public void testValidPatternMxDDxDDDDxFeb() {

		String pattern = "identify date from text : february 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

		pattern = "identify date from text : feb 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));

	}
	
	@Test
	public void testValidPatternMxDDxDDDDxMarch() {
		String pattern = "identify date from text : march 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : mar 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxApril() {
		String pattern = "identify date from text : april 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : apr 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxMay() {
		String pattern = "identify date from text : may 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxJune() {
		String pattern = "identify date from text : june 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : jun 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxJuly() {
		String pattern = "identify date from text : july 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : jul 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxAugust() {
		String pattern = "identify date from text : august 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : aug 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxSep() {
		String pattern = "identify date from text : september 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : sep 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxOct() {
		String pattern = "identify date from text : october 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : oct 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxNov() {
		String pattern = "identify date from text : november 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : nov 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidPatternMxDDxDDDDxDec() {
		String pattern = "identify date from text : december 19 1998";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
		
		pattern = "identify date from text : dec 19 1998";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(pattern.contains(date.getOriginalText()));
	}
	
	@Test
	public void testValidTimePatternWxMxDDxDDDD() {
		String pattern = "december 19 1998 10:10:58.123";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(date.getDateTimeString().contains("10:10:58.123"));
		
		pattern = "december 19 1998 10:10:58.123   ";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(date.getDateTimeString().contains("10:10:58.123"));
		Assert.assertEquals(date.getIdentifiedDateFormat(), "MMMMM DD YYYY HH:mm:ss.SSS");
		
		pattern = "Identified date : 27 Jan,2017 16:14:10, converted";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(date.getDateTimeString().contains("16:14:10"));
		Assert.assertEquals(date.getIdentifiedDateFormat(), "DD MMM,YYYY HH:mm:ss");
	}

	@Test
	public void testValidTimePatternDateXTime() {
		String pattern = "2017-Jul-10_14:25:21";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(date.getDateTimeString().contains("14:25:21"));
		
		pattern = "  2017-January-10_14:25:21       ";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(date.getDateTimeString().contains("14:25:21"));
		Assert.assertEquals(date.getIdentifiedDateFormat(), "YYYY-MMMMM-DD_HH:mm:ss");
		
	}
	
	@Test
	public void testValidTimePatternDateTTime() {
		String pattern = "2017-Jul-10T14:25:21.877Z";
		LocalDateModel date = p.parse(pattern).get(0);
		Assert.assertTrue(date.getDateTimeString().contains("14:25:21"));
		
		pattern = "  2017-January-10T14:25:21.877Z       ";
		date = p.parse(pattern).get(0);
		Assert.assertTrue(date.getDateTimeString().contains("14:25:21"));
		Assert.assertEquals(date.getIdentifiedDateFormat(), "YYYY-MMMMM-DD'T'HH:mm:ss.SSS");
		
	} 
}
