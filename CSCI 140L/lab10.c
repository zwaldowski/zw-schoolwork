/*******************************************************
* CSCI 140L-1
* Lab 10
* Zachary Waldowski
* Date: 2/27/12
*
* Demonstrates the problem of representational error.
*
********************************************************/

#include <stdio.h>

// Function declarations
void description(void);
int get_count(void);
void print_summary(int numberSame, int numberLess, int numberMore);

int main(void) {
    // Declare variables
    double fraction, result;
    int count, numberSame = 0, numberLess = 0, numberMore = 0;
    
    // Print the info
    description();
    
    // Get the user's count.
    count = get_count();
    
    for (int outer = 2; outer <= count; outer++) {
        fraction = 1.0 / (double)outer;
        result = 0.0;
        
        for (int inner = 0; inner < outer; inner++) {
            result += fraction;
        }
                
        if (result < 1.0) {
            printf("Adding %d 1/%d’s gives a result of less than 1.", outer, outer);
            numberLess++;
        } else if (result > 1.0) {
            printf("Adding %d 1/%d’s gives a result of greater than 1.", outer, outer);
            numberMore++;
        } else {
            printf("Adding %d 1/%d’s gives a result of 1.", outer, outer);
            numberSame++;
        }
        printf("\n");
    }
    
    // Print our post-execution summary.
    print_summary(numberSame, numberLess, numberMore);
    
    return 0;
}

// Prints information about the program.
void description(void) {
    printf("//////////////////////////////////////////////////////////\n");
    printf(" This program demonstrates the problem of                 \n");
    printf(" representational error.                                  \n");
    printf("//////////////////////////////////////////////////////////\n\n");
}

// Ask for and store the count.
int get_count(void) {
    int value = 0;
    while (1) {
        printf("Enter a number to count up to. > ");
        scanf("%d", &value);
        printf("\n");
        
        if (value > 2)
            break;
    }
    return value;
}

// Bonus: print a summary of the representational errors.
void print_summary(int numberSame, int numberLess, int numberMore) {
    printf("\n");
    printf("//////////////////////////////////////////////////////////\n");
    printf("We got a result equal to 1, %d times.\n", numberSame);
    printf("We got a result less than 1, %d times.\n", numberLess);
    printf("We got a result greater than 1, %d times.\n", numberMore);
    printf("//////////////////////////////////////////////////////////\n");
    
}