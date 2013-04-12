/*******************************************************
* CSCI 140L-1
* Lab 5
* Zachary Waldowski
* Date: 2/1/12
*
* 
*
********************************************************/

#include <stdio.h>

// Function forward declarations
void description(void);
void display_break(void);
double sum(double x, double y);
double diff(double x, double y);

int main(void) {
	// Declare variables
	double firstNumber = 0.0,
		   secondNumber = 0.0,
		   sumNumber = 0.0,
		   firstDiff = 0.0,
		   secondDiff = 0.0;
	
	// Display the description.
	description();
	
	// Display a graphical break.
	display_break();
	
	// Ask the user to enter 2 numbers (ask for 1 at a time).
	printf("Enter a number:       > ");
	scanf("%lf", &firstNumber);
	printf("Enter another number: > ");
	scanf("%lf", &secondNumber);
	
	// Use the sum function to add the 2 numbers and display the results.
	sumNumber = sum(firstNumber, secondNumber);
	printf("\nThe sum of %.2f and %.2f is:\t\t\t\t%.2f.\n", firstNumber, secondNumber, sumNumber);
	
	// Display a graphical break.
	display_break();
		
	// Use the diff function to find the difference in number 1 and number 2 and display the results.
	firstDiff = diff(firstNumber, secondNumber);
	printf("The difference between %.2f and %.2f is:  %.2f.\n", firstNumber, secondNumber, firstDiff);
	
	// Display a graphical break.
	display_break();
		
	// Use diff function to find the difference in number 2 and number 1 and display the results.
	secondDiff = diff(secondNumber, firstNumber);
	printf("The difference between %.2f and %.2f is:  %.2f.\n", secondNumber, firstNumber, secondDiff);
	
	// Display a graphical break.
	display_break();
	
	return 0;
}

// Prints a description of the program.
void description(void) {
	printf("\n This program will ask for, then sum and find the diff-   \n");
	printf(" erence between two numbers.                              \n");
}

// Displays a graphical break.
void display_break(void) {
	printf("\n");
	printf("+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+\n");
	printf("|                               |\n");
	printf("+===============================+\n");
	printf("\n");
}

// Returns the sum of two numbers.
double sum(double x, double y) {
	double sum = x + y;
	return sum;
}

// Returns the difference between two numbers.
double diff(double x, double y) {
	return x - y;
}