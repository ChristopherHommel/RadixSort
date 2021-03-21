/**
 * An implementation of the radix sort algorithm
 * @author Christopher
 *
 */
public class RadixSort {

	public static void main(String[] args) {

	}
	
	/**
	 * Apply a count sort to each digit with a multiple of 10
	 * 1s, 10, 100s, 1000s,....n
	 * Continue until there is no more multiples on the largest number in the array
	 * @param array array to be sorted
	 */
	public void radixSort(int[] array) {
		int length = array.length;
		if (array == null || length <= 1) {
		      return;
		}
		int maximum = maximumValue(array, length);
		//Number of times to call apply a count sort on each value
		int numberOfDigits = calculateNumberOfDigits(maximum);
		//Start at 1s value then move to 10s, 100s and so on
		int placeholder = 1;
		
		while(numberOfDigits > 0) {
			//Apply a count sort on the array at the new placeholder position
			countSort(array, placeholder);
			placeholder *= 10;
			//Once all the digits have been visited stop loop
			numberOfDigits--;
		}
		      
		      
	}
	
	/**
	 * Find the total number of digits from the maximum value in the array
	 * @param value Largest number in the array
	 * @return Number of digits from value
	 */
	private int calculateNumberOfDigits(int value) {
		return (int)Math.log10(value) + 1;
	}
	
	/**
	 * Find the maximum value in the array
	 * @param array an array of unsorted values
	 * @param length the length of the array
	 * @return the maximum value
	 */
	public int maximumValue(int[] array, int length) {
		//Get the first position in the array
		int maximum = array[0];
		//Loop through the array
		for(int i = 0; i<length;i++) {
			//If a value in the array is greater than the current maximum value
			//Set the new maximum value to be that value
			if(array[i] > maximum) {
				maximum = array[i];
			}
		}
		//Return the value found
		return maximum;
	}
	
	/**
	 * Counting sort of the array to keep the values static
	 * @param array array to be count sorted
	 * @param exponent The value to access the next digit over
	 */
	public void countSort(int[] array, int exponent) {
		//Set range to 10 to be able to store the frequency of digits occuring the array
		int range = 10;
		//Get the length of the array
		int length = array.length;
			
		int[] frequency = new int[range];
		int[] sortedValues = new int[length];
		
		//Calculate the digit at each position in the string of numbers and increase the given position by 1
		for (int i = 0; i < length; i++) {
			int digit = (array[i] / exponent) % range;
			frequency[digit]++;
		}

		//Find the frequency of numbers occuring and remove one from each position
		for (int i = 1; i < range; i++) {
			frequency[i] += frequency[i - 1];
		}
		
		//Place the sorted value into a new list of sorted values
		for (int i = length - 1; i >= 0; i--) {
			int digit = (array[i] / exponent) % range;
			sortedValues[frequency[digit] - 1] = array[i];
			frequency[digit]--;
		}
		//Copy the sorted values into the passed in array and recheck that 
		//no values > 0 exist in the frequency array and that the list is sorted
		System.arraycopy(sortedValues, 0, array, 0, length);
	}

}






















