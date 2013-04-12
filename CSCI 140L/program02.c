/*******************************************************
* CSCI 140-01
* Program 2
* Zachary Waldowski
* Date: 2/14/12
*
* Prints out the color associated with the
* first letter of a word.
*
********************************************************/

#include <stdio.h>

// Function declarations
void print_description(void);
char get_first_letter(void);

int main(void) {
    // Variable declarations
    char first_letter;
    
    // Print out program header
    print_description();
    
    // Requests a character from the user.
    first_letter = get_first_letter();
    
    printf("\nYou picked ");
    
    // Decide which color to print out.
    switch (first_letter) {
        case 'R':
        case 'r':
            printf("red");
            break;
        case 'O':
        case 'o':
            printf("orange");
            break;
        case 'Y':
        case 'y':
            printf("yellow");
            break;
        case 'G':
        case 'g':
            printf("green");
            break;
        case 'B':
        case 'b':
            printf("blue");
            break;
        case 'I':
        case 'i':
            printf("indigo");
            break;
        case 'V':
        case 'v':
            printf("violet");
            break;
        default:
            printf("unknown");
            break;
    }
    
    printf("!\n");
    
    return 0;
}

void print_description(void) {
    printf("//////////////////////////////////////////////////////////\n");
    printf(" This program will give the name of a color based on your \n input.\n");
    printf("//////////////////////////////////////////////////////////\n\n");
}

char get_first_letter(void) {
    char letter;
    printf("Enter the first letter of a color. > ");
    letter = getchar();
    return letter;
}
