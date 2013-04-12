/*******************************************************
* CSCI 140-01
* Lab 2
* Zachary Waldowski
* Date: 1/23/12 (assigned)
*
* Takes input and does very basic math, redux.
*
********************************************************/

#include <stdio.h>
#include <math.h>

int main(void) {
	int firstInteger = 0,
		secondInteger = 0;
	double firstDouble = 0.0,
		   secondDouble = 0.0,
		   doublesDivided = 0.0,
		   integersPowered = 0;
	char firstCharacter,
		 secondCharacter;
		
	// Input integers
	printf("Enter an integer. \t\t\t> ");
	scanf("%d", &firstInteger);
	
	printf("Enter another integer. \t\t> ");
	scanf("%d", &secondInteger);
	
	// Input doubles
	printf("Enter a decimal. \t\t\t> ");
	scanf("%lf", &firstDouble);
	
	printf("Enter another decimal. \t\t> ");
	scanf("%lf", &secondDouble);
	
	// Crash prevention
	if (firstDouble == 0.0f)
		firstDouble = 1.0f;
	
	// Input characters
	printf("Enter a single character. \t> ");
	scanf(" %c", &firstCharacter);
	
	printf("Enter another character. \t> ");
	scanf(" %c", &secondCharacter);

	// Print each of the 6 variables on their own line
	printf("\nFirst integer:\t\t%i\nSecond decimal:\t\t%i\n", firstInteger, secondInteger);
	printf("First decimal:\t\t%f\nSecond decimal:\t\t%f\n", firstDouble, secondDouble);
	printf("First character:\t%c\nSecond character:\t%c\n", firstCharacter, secondCharacter);

	// Print the value of the 1st integer raised to the power of the 2nd integer.
	// `pow` returns a `double`, let's stay precise; if you power large numbers, `int`-based types wrap around negative
	// Instead, store the result as a double and truncate it down to a .0 because we're using whole numbers.
	integersPowered = pow((double)firstInteger, secondInteger);
	printf("\n%i to the power of %i is %.0f.\n", firstInteger, secondInteger, integersPowered);
	
	// Print the floor of the 2nd double divided by the 1st double.
	doublesDivided = floor(secondDouble/firstDouble);
	printf("%.2f divided by %.2f is %.2f.\n", secondDouble, firstDouble, doublesDivided);
	
	// Print the 1st char, 2nd char, 1st char, 2nd char (using 1 printf).
	printf("%c %c %c %c\n", firstCharacter, secondCharacter, firstCharacter, secondCharacter);

    return 0;
}
