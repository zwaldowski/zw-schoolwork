/*******************************************************
* CSCI 140L-1
* Lab 11
* Zachary Waldowski
* Date: 2/29/12
*
* Conversion between given values.
*
********************************************************/

#include <stdio.h>

// Function declarations
void ASCII_Operation(void);
void Integer_Operation(void);

int main(void) {
    // Declare variables
    char menu;
    
    // Main control loop
    while (1) {
        // display a menu
        printf("Select an operation:");
        printf("\n\tA - ASCII");
        printf("\n\tI - Integer");
        printf("\n\tQ - Quit");
        printf("\n > ");
        
        scanf(" %c", &menu);
        
        printf("\n");
        
        switch (menu) {
            case 'A':
            case 'a':
                ASCII_Operation();
            break;
            
            case 'I':
            case 'i':
                Integer_Operation();
            break;
            
            case 'Q':
            case 'q':
                return 0;
            break;
        }   
    }
}

void ASCII_Operation(void) {
    // Variable declarations
    char letter, shiftConverted;
    int integer, letterConverted, shift;
    
    // Accept input.
    printf("Enter a letter.    > ");
    scanf(" %c", &letter);
    
    printf("Enter an integer . > ");
    scanf("%d", &integer);
    
    printf("\n");
    
    // Store the casted bits into variables as demonstration.
    letterConverted = (int)letter;
    shift = letterConverted + integer;
    shiftConverted = (char)shift;
    
    // Print values
    printf("You entered \"%c\". It has a value of %d.\n", letter, letterConverted);
    printf("The shifted value is %d. That's \"%c\".\n\n", shift, shiftConverted);
}

void Integer_Operation(void) {
    // Variable declarations
    char integerConverted, shiftConverted;
    int integer = -1, shift;
    
    // Accept input.
    while (integer < 0 || integer > 255) {
        printf("Enter an integer, 0-255. > ");
        scanf("%d", &integer);
    }
    
    printf("Enter an integer.        > ");
    scanf("%d", &shift);
    printf("\n");
    
    // Store the casted bits into variables as demonstration.
    integerConverted = (char)integer;
    shift += integer;
    shiftConverted = (char)shift;
    
    // Print values
    printf("You entered %d. That's \"%c\".\n", integer, integerConverted);
    printf("The shifted value is %d. That's \"%c\".\n\n", shift, shiftConverted);
}