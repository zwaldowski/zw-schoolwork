/*******************************************************
* CSCI 140L-1
* Lab 7
* Zachary Waldowski
* Date: 2/15/12
*
* Basic four-function math system.
*
********************************************************/

#include <stdio.h>

// Function declarations
void description(void);
double sum(double x, double y);
double difference(double x, double y);
double multiply(double x, double y);
double divide(double x, double y);

int main(void) {
    // Declare variables
    double firstNumber = 0.0,
           secondNumber = 0.0,
           result = 0.0;
    int selection = -1;
    
    // Print the description
    description();
    
    // Get two numbers
    printf("Enter a number.       > ");
    scanf("%lf", &firstNumber);
    
    printf("Enter another number. > ");
    scanf("%lf", &secondNumber);
    
    // Loop through the selection/perform operation.
    // runs infinitely until we hit "break"; i.e., when they enter 0.
    while (1) {
        // display a menu
        printf("Select an operation:");
        printf("\n\t1 - Sum");
        printf("\n\t2 - Difference");
        printf("\n\t3 - Multiply");
        printf("\n\t4 - Divide");
        printf("\n\tEnter 0 to cancel.");
        printf("\n > ");
        
        // get their selection
        scanf("%d", &selection);
        
        // perform the math operation
        if (selection == 1) {
            result = sum(firstNumber, secondNumber);
            printf("Sum");
        } else if (selection == 2) {
            result = difference(firstNumber, secondNumber);
            printf("Difference");
        } else if (selection == 3) {
            result = multiply(firstNumber, secondNumber);
            printf("Product");
        } else if (selection == 4) {
            result = divide(firstNumber, secondNumber);
            printf("Quotient");
        } else // if they enter zero or something invalid, bail out.
            break;
            
        // print the result
        printf(":  %.2f\n\n", result);
    }
	
	return 0;
}

// Prints a description of the program.
void description(void) {
    printf("//////////////////////////////////////////////////////////\n");
    printf(" This program will ask for two numbers and perform simple \n");
    printf(" mathematical operations according to your input.         \n");
    printf("//////////////////////////////////////////////////////////\n");
}

// Adds two numbers and returns the result.
double sum(double x, double y) {
    return x + y;
}

// Subtracts one number from the other and returns the result.
double difference(double x, double y) {
    return x - y;
}

// Multiplies two numbers together and returns the result.
double multiply(double x, double y) {
    return x * y;
}

// Divides one number by the other and returns the result.
// Includes handling to prevent divide by 0 crashes.
double divide(double x, double y) {
    if (y == 0.0)
        return 0;
    return x / y;
}
