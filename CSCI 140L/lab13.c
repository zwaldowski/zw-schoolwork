/*******************************************************
 * CSCI 140L-1
 * Lab 13
 * Zachary Waldowski
 * Date: 3/19/12
 *
 * An ATM that dispenses money.
 *
 ********************************************************/

#include <stdio.h>

// Function declarations
void description(void);
void getBills(long int wantedMoney, unsigned int *fifties, unsigned int *twenties, unsigned int *tens);
void printBills(unsigned int fifties, unsigned int twenties, unsigned int tens);

int main(void) {
    // Variable declarations
    long int wantedMoney = 0.0;
    unsigned int fifties = 0,
                 twenties = 0,
                 tens = 0;
    
    // Print a description of the program
    description();
    
    // Loop until the enter a negative number
    while (1) {
        // Get input from the user
        printf("How much money do you want?\n(Enter a negative number to quit.)\t> ");
        scanf("%ld", &wantedMoney);
        printf("\n");
        
        // Bail out if they enter a negative value
        if (wantedMoney < 0) {
            printf("Bye.\n\n");
            break;
        }
        
        // Get the minimum number of bills
        getBills(wantedMoney, &fifties, &twenties, &tens);
        
        // And print a description of those values
        printBills(fifties, twenties, tens);
    }
        
    return 0;
}

// Print a description of the program
void description(void) {
    printf("//////////////////////////////////////////////////////////\n");
    printf(" This is a program for an ATM that dispenses money.       \n");
    printf("//////////////////////////////////////////////////////////\n\n");
}

// Calculates a minimum number of bills for a given amount of money
void getBills(long int wantedMoney, unsigned int *fifties, unsigned int *twenties, unsigned int *tens) {
    unsigned int retFifties = 0,
                 retTwenties = 0,
                 retTens = 0,
                 leftover;
    
    retFifties = wantedMoney / 50;
    leftover = wantedMoney % 50;
    
    retTwenties = leftover / 20;
    leftover = leftover % 20;
    
    retTens = leftover / 10;
    
    *fifties = retFifties;
    *twenties = retTwenties;
    *tens = retTens;
}

// Prints a description for the number of bills we'd return
void printBills(unsigned int fifties, unsigned int twenties, unsigned int tens) {
    printf("Okay, that's:\n");
    
    if (fifties) {
        if (fifties > 1)
            printf("\t- %u 50 dollar bills\n", fifties);
        else
            printf("\t- A 50 dollar bill.\n");
    }
    
    if (twenties) {
        if (twenties > 1)
            printf("\t- %u 20 dollar bills\n", twenties);
        else
            printf("\t- A 20 dollar bill.\n", twenties);
    }
        
    if (tens) {
        if (tens > 1)
            printf("\t- %u 10 dollar bills\n", tens);
        else
            printf("\t- A 10 dollar bill.\n", tens);
    }
        
    if (!fifties && !twenties && !tens)
        printf("\tNothing.\n");
        
    printf("\n");
}