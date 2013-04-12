/*******************************************************
* CSCI 140L-1
* Lab 1
* Zachary Waldowski
* Date: 1/23/12
*
* Computes the tip and fair share of a restaurant bill
* for one or more people.
*
********************************************************/

#include <stdio.h>

int main(void) {
    double price = 0.0,
           tipPercent = 0.0,
           tipAmount = 0.0,
           totalPrice = 0.0,
           fairShare = 0.0;
    int numberOfPeople = 0, tipRate = 0;

    // Format specifiers (from the book):
    //  - %lf is for a "double *" (scanf)
    //  - %d is for an "int *" (scanf)
    //  - %f is for a float and/or double (printf)
    //  - %% is for just a % character.

    // Get the price of the meal.
    printf("Enter the price of the meal. \t> $");
    scanf("%lf", &price);

    // Get the rate of the tip.
    printf("Enter the tip as a percent.\n(Enter a 15 for a 15%% tip.) \t> ");
    scanf("%d", &tipRate);

    // Convert the tip value (15) to a decimal (0.15).
    tipPercent = (double)tipRate / 100;

    // Get the number of people.
    printf("Enter the number of people. \t> ");
    scanf("%d", &numberOfPeople);

    // Calculate the tip amount.
    tipAmount = tipPercent * price;
    printf("\nYour tip is: \t\t\t$%.2f\n", tipAmount);

    // Calculate the total price.
    totalPrice = tipAmount + price;
    printf("Your total bill is: \t\t$%.2f\n", totalPrice);

    // Safety check for numberOfPeople so we don't divide by zero.
    if (numberOfPeople == 0) {
        numberOfPeople = 1;
    }

    // Calculate the fair share.
    fairShare = totalPrice / numberOfPeople;
    printf("Each person should pay: \t$%.2f\n", fairShare);

    return 0;
}
