/*******************************************************
* CSCI 140-01
* Program 1
* Zachary Waldowski
* Date: 1/26/12
*
* Takes input and does very basic math.
*
********************************************************/

#include <stdio.h>
#include <math.h>

int main(void) {
    int first = 0,
        firstResult = 0,
        second = 0,
        secondResult = 0;
    double third = 0.0,
           thirdResult = 0.0;
    char fourth;

    // Asks the user to input an integer
    printf("Enter an integer. \t\t> ");
    scanf("%d", &first);

    firstResult = first * 34;
    printf("%i times 34 is %i.\n\n", first, firstResult);

    // Asks the user to input another integer
    printf("Enter another integer. \t\t> ");
    scanf("%d", &second);

    secondResult = second % 14;
    printf("%i mod 14 is %i.\n\n", second, secondResult);

    // Asks the user to input a double
    printf("Enter a decimal. \t\t> ");
    scanf("%lf", &third);

    thirdResult = pow(third, (double)second);
    printf("%f raised to the %i power is %f.\n\n", third, second, thirdResult);

	// Work around the enter key from the previous scanf getting passed to the next one.
	// Functionally identical to scanf("%c", &pointer);
	getchar();

    // Asks the user in input a character.
    printf("Enter a single character. \t> ");
	fourth = getchar();

    // Print the character.
    printf("%c is a great character!\n\n", fourth);

    // Print #4# #2# #1# #3# in order
    printf("%c %i %i %f\n\n", fourth, second, first, third);

    // Print #1# #2# #3# each on their own, all lined up.
    printf("%i\n", first, 15);
    printf("%i\n", second, 15);
    printf("%f\n", third, 15);

    return 0;
}
