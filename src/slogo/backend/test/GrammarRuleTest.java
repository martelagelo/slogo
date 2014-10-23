package slogo.backend.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import slogo.Constants;
import slogo.backend.impl.parsing.GrammarRule;

public class GrammarRuleTest {
	//@Test
	public void testInfiniteMatchRecurse(boolean expected, String[] pattern, String[] search){
		boolean result = GrammarRule.infiniteMatchRecurse(Arrays.asList(pattern),
				Arrays.asList(search));
		assertEquals(expected, result);
	}
	@Test
	public void testInfiniteMatch(){
		boolean sunnyResult = true;
		String[][] sunny = {
				{"hi", "there", Constants.INFINITE_MATCHING_LABEL, "foo" },
				{ "hi", "there", "there", "foo" }};
		testInfiniteMatchRecurse(sunnyResult, sunny[0], sunny[1]);
		boolean sunnyEndResult = true;
		String[][] sunnyEnd = {
				{"hi", "there", Constants.INFINITE_MATCHING_LABEL },
				{ "hi", "there", "there", "there", "there" }};
		testInfiniteMatchRecurse(sunnyEndResult, sunnyEnd[0], sunnyEnd[1]);
		boolean extraPatternResult = false;
		String[][] extraPattern = {
				{"hi", "there", Constants.INFINITE_MATCHING_LABEL, "blah" },
				{ "hi", "there", "there", "there" }
		};
		testInfiniteMatchRecurse(extraPatternResult, extraPattern[0], extraPattern[1]);
		boolean extraSearchResult = false;
		String[][] extraSearch = {
				{"hi", "there", Constants.INFINITE_MATCHING_LABEL },
				{"hi", "there", "there", "blah" }};
		testInfiniteMatchRecurse(extraSearchResult, extraSearch[0], extraSearch[1]);
		boolean justOneRepeatResult = true;
		String[][] justOneRepeat = {
				{"hi", "there", Constants.INFINITE_MATCHING_LABEL },
				{"hi", "there"}};
		testInfiniteMatchRecurse(justOneRepeatResult, justOneRepeat[0], justOneRepeat[1]);
	}
}
