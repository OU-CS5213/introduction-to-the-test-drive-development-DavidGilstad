import java.util.Arrays;

/**
 * Example class to practice using test-driven development (TDD).
 * 
 * @author David Gilstad
 * @version 1.0
 */
public class AWS {
	/**
	 * Value used in <code>fillAndExpand()</code> to set end of array to.
	 */
	private static final int FILLER_VALUE = Integer.MIN_VALUE;

	/**
	 * Array of values.
	 */
	private int[] values;

	/**
	 * Get a copy of the array <code>this.values</code>.
	 * 
	 * @return the copied array.
	 */
	public int[] getValues() {
		return Arrays.copyOf(values, values.length);
	}

	/**
	 * Sets <code>this.values</code> to the given array.
	 * 
	 * @param values - array of ints to set <code>this.values</code> to.
	 */
	public void setValues(int[] values) {
		this.values = Arrays.copyOf(values, values.length);
	}

	/**
	 * Override of the default <code>toString()</code> method.
	 * 
	 * @return String in the form "AWS [values=<code>this.values</code>]".
	 */
	@Override
	public String toString() {
		return "AWS [values=" + Arrays.toString(values) + "]";
	}

	/**
	 * Constructor that initializes <code>this.values</code> with the given values.
	 * 
	 * @param values - array of ints to set <code>this.values</code> to.
	 */
	public AWS(int[] values) {
		super();
		setValues(values);
	}

	/**
	 * Removes the element at index i in <code>this.values</code>, shifts any
	 * elements after it down, and sets the last element to
	 * <code>FILLER_VALUE</code>.
	 * 
	 * @param i - index of element to remove. If not valid index nothing is done to
	 *          <code>this.values</code>.
	 * @return the removed value, or <code>FILLER_VALUE</code> if <code>i</code> is
	 *         invalid.
	 */
	public int remove(int i) {
		if (i < 0 || i >= values.length) return FILLER_VALUE;
		
		int value = values[i];
		for (int index = i; index < values.length - 1; ++index)
			values[index] = values[index + 1];
		values[values.length - 1] = FILLER_VALUE;
		
		return value;
	}

	/**
	 * Copy the value at index <code>position</code> and insert <code>nt</code>
	 * replicas after it into <code>this.values</code>. If <code>nt</code> is
	 * negative, make all the copies and the original value the negative of what is
	 * was.
	 * 
	 * e.g. if <code>this.values</code> is {1, 2, 4} fillAndExpand(1, 3) would
	 * change it to {1, 2, 2, 2, 2, 4} fillAndExpand(2, -2) would change it to {1,
	 * 2, -4, -4, -4}
	 * 
	 * @param position - index of values to copy and expand, must be valid index.
	 * @param nt       - number of times to copy and insert value at
	 *                 <code>position</code>.
	 * @throws indexOutOfBoundsException if <code>position</code> is invalid.
	 */
	public void fillAndExpand(int position, int nt) {
		int numberOfTimes = Math.abs(nt);
		int[] newArray = new int[values.length + numberOfTimes];

		for (int i = 0; i <= position; ++i)
			newArray[i] = values[i];

		int fillValue = newArray[position] * (nt < 0 ? -1 : 1);
		for (int i = position; i <= numberOfTimes + position; ++i)
			newArray[i] = fillValue;

		for (int i = position + 1; i < values.length; ++i)
			newArray[i + numberOfTimes] = values[i];

		values = newArray;
	}

	/**
	 * Remove any values in <code>this.values</code> that are identical to the value
	 * immediately before it.
	 * 
	 * e.g. {1, 2, 2, 1, 3, 3, 3} goes to {1, 2, 1, 3}
	 */
	public void removeConsecutiveRepeats() {
		for (int i = 1; i < values.length;)
			if (values[i] == FILLER_VALUE)
				break;
			else if (values[i] == values[i - 1])
				this.remove(i);
			else
				++i;

		for (int i = 0; i < values.length; ++i)
			if (values[i] == FILLER_VALUE) {
				values = Arrays.copyOf(values, i);
				break;
			}
	}

}
