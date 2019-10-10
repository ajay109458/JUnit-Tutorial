package com.ajay.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {

	private String str;
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Initializing the database connection");
	}

	/**
	 * Runs only once
	 */
	@AfterAll
	static void afterAll() {
		System.out.println("Close connection to the database");
	}

	/**
	 * Runs before each test
	 * 
	 * @param info
	 */
	@BeforeEach
	void beforeEach(TestInfo info) {
		System.out.println("Initializing the test data for : " + info.getDisplayName());
	}

	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("cleanup test data for : " + info.getDisplayName());
	}

	@Test
	@Disabled
	void length_basic() {

		int actualLength = "ABCD".length();
		int expectedLength = 4;

		assertEquals(expectedLength, actualLength);

	}

	/**
	 * Step 09 : Test an exception Step 10 : @DisplayName
	 */
	@Test
	@DisplayName("When string is null, throw an exception")
	void length_exception() {

		String str = null;

		assertThrows(NullPointerException.class, () -> {
			str.length();
		});

	}

	/**
	 * Step 11 : Run parameterized test
	 * @param str
	 */
	@ParameterizedTest
	@ValueSource(strings = { "ABCD", "ABC", "A", "ABCDE" })
	void lengthGreaterThanZeroParameterizedTest(String str) {

		assertTrue(str.length() > 0);
	}
	
	@ParameterizedTest
	@CsvSource(value  = { "abc, ABC", "abcd, ABCD"  })
	void upperCaseCSVParameterizedTests(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}
	
	@Test
	@RepeatedTest(4)
	void minValueTest() {

		int a = 10;
		int b = 20;

		int actualValue = Math.min(a, b);
		int expectedValue = a;

		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(5), 
					() -> {
						for (int i = 0; i < 10; i++) {
							System.out.println(i);
						}
					}
				);
	}
	
	@Nested
	class EmptyStringTests {
		
		@BeforeEach
		void setToEmpty() {
			str = "";
		}
		
		@Test
		void lengthIsZero() {
			assertEquals(0, str.length());
		}
		
		@Test
		void upperCaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}
		
	}

}
