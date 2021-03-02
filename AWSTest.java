import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AWSTest {

	private static final int FILLER_VALUE = Integer.MIN_VALUE;
	private int[] original = { 1, 2, 3 };
	AWS originalAWS;

	@BeforeEach
	void setUp() throws Exception {
		originalAWS = new AWS(this.original);
	}

	@Test
	void testGetValues() {
		// make sure new array doesn't point to same reference
		int[] newArray = originalAWS.getValues();
		assertNotEquals(newArray, originalAWS.getValues());
		
		// just to be sure, make sure updates don't change original
		assertNotEquals(++newArray[0], originalAWS.getValues()[0]);
		assertEquals(--newArray[0], originalAWS.getValues()[0]);
		
	}

	@Test
	void testSetValues() {
		int[] expected = {2, 3, 4};
		int[] setArray = {2, 3, 4};
		originalAWS.setValues(setArray);
		
		// change set array but make sure aws values don't change
		setArray[1] = 37;
		assertEquals(expected[1], originalAWS.getValues()[1]);
		assertEquals(expected.length, originalAWS.getValues().length);
	}

	@Test
	void testToString() {
		String expected = "AWS [values=" + Arrays.toString(original) + "]";
		assertEquals(expected, originalAWS.toString());
	}

	@Test
	void testAWS() {
		int[] expected = { 1, 2, 3 };
		int[] x = { 1, 2, 3 };
		AWS aws = new AWS(x);
		x[1] = 100;

		int[] actual = aws.getValues();
		assertEquals(actual[0], expected[0]);
		assertEquals(actual[1], expected[1]);
	}

	@Test
	void testRemove() {
		int[] x = { 1, 2, 3 };
		AWS aws = new AWS(x);

		int value = aws.remove(-1);
		int expected = FILLER_VALUE;
		assertEquals(expected, value);

		value = aws.remove(x.length + 10);
		expected = FILLER_VALUE;
		assertEquals(expected, value);

		value = aws.remove(0);
		assertEquals(x[0], value);

		int[] r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);

		value = aws.remove(2);
		assertEquals(r[2], value);

		r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);

	}

	@Test
	void testFillAndExpand() {
		int position = 1;
		int numberOfTimes = 2;
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
		int first = org[0];

		int expected = originalAWS.getValues().length + numberOfTimes;
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);

		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		assertEquals(first, result[0]);

	}

	@Test
	void testFillAndExpandWithNegative() {
		int position = 1;
		int numberOfTimes = -2;
		
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
		int first = org[0];
		int expected = originalAWS.getValues().length + Math.abs(numberOfTimes);
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);

		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(-expectedValue, b);
		assertEquals(-expectedValue, c);

		assertEquals(first, result[0]);

	}

}
