/*
	Authorship: ALL credit for the code in this file goes to the authors of the
	book "Elements of Programming Interviews" by Adnan Aziz, Amit Prakash, and
	Tsung-Hsien Lee.

	I have just added explanatory comments, reformatted the code, & changed
	variable names for understanding.

	The video to explain this code is here: [a link will live here someday]
*/

public static String intToString(int inputInt) {

	/*
		Take note if the integer is negative before
		we operate on it and take it to 0 during
		our truncation
	*/
	boolean isNegative = false;

	if (inputInt < 0) {
		isNegative = true;
	}

	/*
		"Eat" away the string character by character:

		1.) Convert the ones place to a character
		2.) Append it to the target string
		3.) "Eat" away the ones place from the integer
	*/
	StringBuilder sb = new StringBuilder();
	do {

		int onesPlace = inputInt % 10;
		char onesPlaceAsCharacter = (char) '0' + Math.abs(onesPlace);

		sb.append(digitAsCharacter);
		inputInt /= 10;

	} while (inputInt != 0);

	if (isNegative) {
		sb.append('-'); // Adds the negative sign to the end
	}

	/*
		By this point if the input was -123 then we will
		have "321-"

		Reversed we will get what we want, "-123"
	*/
	sb.reverse();

	/*
		Finally, convert the StringBuilder object to a string
	*/
	return sb.toString();
}


public static int stringToInt(String sb) {

	int result = 0;

	/*
		Determine where we want to start. If the first character is a '-' sign
		we begin converting at index 1, if we do not have a '-' then we can
		start decoding from index 0.
	*/
	for(int i = sb.charAt(0) == '-' ? 1 : 0; i < sb.length(); i++){
		final int digit = sb.charAt(i) - '0'; // Grab the character and convert to an int
		result *= 10; // Make room in the ones place
		result = result + digit; // Add it to the result
	}

	return sb.charAt(0) == '-' ? -result : result;
}
