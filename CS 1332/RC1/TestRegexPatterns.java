import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * These are the test cases we will use to grade your regex assignment.
 * 
 * Each test is worth 0.2pts for a total of 10pts. We will 
 * round down when entering grading into T-Square.
 * 
 * Yes... if you do nothing and turn it in, you will get 4pts.
 * 
 * @author Naomi Robert
 *
 */
public class TestRegexPatterns {

	/*
	 * TEST NAME
	 */
	
	@Test
	public void testName0() {
		assertTrue("Failed name case 1",RegexPatterns.testName("Peter Griffin"));
	}

	@Test
	public void testName1() {
		assertTrue("Failed name case 2",RegexPatterns.testName("Anna-Kate Smith"));
	}

	@Test
	public void testName2() {
		assertTrue("Failed name case 3",RegexPatterns.testName("Mark ONeill"));
	}

	@Test
	public void testName3() {
		assertFalse("Failed name case 4",RegexPatterns.testName("Mark O'Neill"));
	}

	@Test
	public void testName4() {
		assertFalse("Failed name case 5",RegexPatterns.testName("homer Simpson"));
	}

	@Test
	public void testName5() {
		assertFalse("Failed name case 6",RegexPatterns.testName("homer simpson"));
	}

	@Test
	public void testName6() {
		assertFalse("Failed name case 7",RegexPatterns.testName("Homer simpson"));
	}

	/*
	 * TEST PHONE NUMBER
	 */
	
	@Test
	public void testPNumber0() {
		assertFalse("Failed phone number case 1",RegexPatterns.testPhoneNumber("4044444444"));
	}

	@Test
	public void testPNumber1() {
		assertFalse("Failed phone number case 2",RegexPatterns.testPhoneNumber("404-444-4444"));
	}

	@Test
	public void testPNumber2() {
		assertTrue("Failed phone number case 3",RegexPatterns.testPhoneNumber("(404)444-4444"));
	}

	@Test
	public void testPNumber3() {
		assertTrue("Failed phone number case 4",RegexPatterns.testPhoneNumber("(678)444-4444"));
	}

	@Test
	public void testPNumber4() {
		assertTrue("Failed phone number case 5",RegexPatterns.testPhoneNumber("(770)444-4444"));
	}

	@Test
	public void testPNumber5() {
		assertFalse("Failed phone number case 6",RegexPatterns.testPhoneNumber("(202)444-4444"));
	}

	@Test
	public void testPNumber6() {
		assertFalse("Failed phone number case 7",RegexPatterns.testPhoneNumber("(404) 444-4444"));
	}

	/*
	 * TEST EMAIL
	 */

	@Test
	public void testEmail0() {
		assertTrue("Failed email case 1",RegexPatterns.testEmail("email@email.com"));
	}

	@Test
	public void testEmail1() {
		assertTrue("Failed email case 2",RegexPatterns.testEmail("email.email@email.com"));
	}

	@Test
	public void testEmail2() {
		assertTrue("Failed email case 3",RegexPatterns
				.testEmail("email-email_email@email.com"));
	}

	@Test
	public void testEmail3() {
		assertFalse("Failed email case 4",RegexPatterns.testEmail("email@email@email.com"));
	}

	@Test
	public void testEmail4() {
		assertFalse("Failed email case 5",RegexPatterns.testEmail("email&email@email.com"));
	}

	@Test
	public void testEmail5() {
		assertTrue("Failed email case 6",RegexPatterns
				.testEmail("e______________________mail@email.com"));
	}

	@Test
	public void testEmail6() {
		assertTrue("Failed email case 7",RegexPatterns.testEmail("email@email.org"));
	}

	@Test
	public void testEmail7() {
		assertTrue("Failed email case 8",RegexPatterns.testEmail("email@email.net"));
	}

	@Test
	public void testEmail8() {
		assertTrue("Failed email case 9",RegexPatterns.testEmail("email@ema____il.net"));
	}

	@Test
	public void testEmail9() {
		assertTrue("Failed email case 10",RegexPatterns.testEmail("ema1234il@email.net"));
	}

	@Test
	public void testEmail10() {
		assertTrue("Failed email case 11",RegexPatterns.testEmail("email@ema1234il.net"));
	}

	@Test
	public void testEmail11() {
		assertFalse("Failed email case 12",RegexPatterns.testEmail("email@email.it"));
	}

	@Test
	public void testEmail12() {
		assertFalse("Failed email case 13",RegexPatterns.testEmail("4email@email.com"));
	}

	@Test
	public void testEmail13() {
		assertFalse("Failed email case 14",RegexPatterns.testEmail("email@4email.com"));
	}

	/*
	 * TEST ADDRESS
	 */

	@Test
	public void testAddress0() {
		assertTrue("Failed address case 1",RegexPatterns
				.testAddress("1 Atlantic Ave.\nAtlanta, GA 30313"));
	}

	@Test
	public void testAddress1() {
		assertTrue("Failed address case 2",RegexPatterns
				.testAddress("12 Atlantic Ave.\nAtlanta, GA 30313"));
	}

	@Test
	public void testAddress2() {
		assertTrue("Failed address case 3",RegexPatterns
				.testAddress("123 Atlantic Ave.\nAtlanta, GA 30313"));
	}

	@Test
	public void testAddress3() {
		assertTrue("Failed address case 4",RegexPatterns
				.testAddress("1234 Atlantic Ave.\nAtlanta, GA 30313"));
	}

	@Test
	public void testAddress4() {
		assertTrue("Failed address case 5",RegexPatterns
				.testAddress("12345 Atlantic Ave.\nAtlanta, GA 30313"));
	}

	@Test
	public void testAddress5() {
		assertFalse("Failed address case 6",RegexPatterns
				.testAddress("123456 Atlantic Ave.\nAtlanta, GA 30313"));//6 leading digits
	}

	@Test
	public void testAddress6() {
		assertTrue("Failed address case 7",RegexPatterns
				.testAddress("123 A\nAtlanta, GA 30313"));
	}

	@Test
	public void testAddress7() {
		assertTrue("Failed address case 8",RegexPatterns
				.testAddress("123 lowercase\nAtlanta, GA 30313"));
	}

	@Test
	public void testAddress8() {
		assertFalse("Failed address case 9",RegexPatterns.testAddress("123\nAtlanta, GA 30313"));//no street
	}

	@Test
	public void testAddress9() {
		assertFalse("Failed address case 10",RegexPatterns
				.testAddress("123 Atlantic Ave.\natlanta, GA 30313"));
	}

	@Test
	public void testAddress10() {
		assertTrue("Failed address case 11",RegexPatterns
				.testAddress("123 Atlantic Ave.\nAtlanta GA 30313"));
	}

	@Test
	public void testAddress11() {
		assertFalse("Failed address case 12",RegexPatterns
				.testAddress("123 Atlantic Ave.\nAtlanta, Ga 30313"));//state should be caps
	}

	@Test
	public void testAddress12() {
		assertFalse("Failed address case 13",RegexPatterns
				.testAddress("123 Atlantic Ave.\nAtlanta, ga 30313"));//state should be caps
	}

	@Test
	public void testAddress13() {
		assertFalse("Failed address case 14",RegexPatterns
				.testAddress("123 Atlantic Ave.\nAtlanta, GA 30"));//invalid zip code
	}

	@Test
	public void testAddress14() {
		assertFalse("Failed address case 15",RegexPatterns
				.testAddress("123 Atlantic Ave.\nAtlanta, GA 30313-5124"));//invalid zip code
	}

	@Test
	public void testAddress15() {
		assertTrue("Failed address case 16",RegexPatterns
				.testAddress("100 The Moon\nCity, NA 00000"));
	}

	@Test
	public void testAddress16() {
		assertTrue("Failed address case 17",RegexPatterns
				.testAddress("100 First-Street Cir.\nSt. Lewis, NA 00000"));
	}

	@Test
	public void testAddress17() {
		assertTrue("Failed address case 18",RegexPatterns
				.testAddress("100 First-Street Cir.\nNorth-Ridge, NA 00000"));
	}

	@Test
	public void testAddress18() {
		assertFalse("Failed address case 19",RegexPatterns
				.testAddress("First-Street Cir.\nNorth-Ridge, NA 00000"));//no leading digits
	}

	/*
	 * TEST JAVA EXECUTABLE
	 */

	@Test
	public void testJavaExec0() {
		assertTrue("Failed Java method case 1",RegexPatterns
				.testJavaExecutable("public static void main(String[] args)"));
	}

	@Test
	public void testJavaExec1() {
		assertTrue("Failed Java method case 2",RegexPatterns
				.testJavaExecutable("\n //  ///*\"public static void main(String[] args)\"*/ \n "));
	}

	@Test
	public void testJavaExec2() {
		assertFalse("Failed Java method case 3",RegexPatterns
				.testJavaExecutable("\n  public void main(String[] args)  \n"));
	}
}
