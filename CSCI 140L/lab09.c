/*******************************************************
* CSCI 140L-1
* Lab 9
* Zachary Waldowski
* Date: 2/22/12
*
* Predicts the billions of dollars of fast food charges.
*
********************************************************/

#include <stdio.h>

// Function declarations
void information(void);
double fast_food_billions(int year);

int main(void) {
    // Declare variables
    int year = 0;
    double billions = 0.0;
    
    // Print the info
    information();
    
    // Enter the loop; automatically bail out 
    do {
        // Get an inputted year.
        printf("Enter a year after 2005.\n\t(Enter 2005 to quit.)\t> ");
        scanf("%d", &year);
        printf("\n");
        
        // Check if it's under, loop through again if it is..
        if (year < 2005) {
            printf("Year is not after 2005.\n\n");
            continue;
        } else if (year == 2005) {  // Bail out if they enter the exit value.
            break;
        }
        
        // Calculate the prediction.
        billions = fast_food_billions(year);
        
        // Print the prediction.
        printf("Consumers will charge $%.2f billion in fast food in %d.\n\n", billions, year);
    } while (1);
    
	return 0;
}

// Prints information about the program.
void information(void) {
    printf("//////////////////////////////////////////////////////////\n");
    printf(" United States consumers put $51 billion in fast food     \n");
    printf(" charges on the their credit cards in 2006, up from 33.2  \n");
    printf(" billion in 2005.                                         \n");
    printf("//////////////////////////////////////////////////////////\n\n");
}

// Calculate and return a prediction:  33.2 + 16.8 * years since 2005
double fast_food_billions(int year) {
    return 33.2 + (16.8 * (year - 2005));
}