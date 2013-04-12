/*******************************************************
 * CSCI 140L-1
 * Lab 12
 * Zachary Waldowski
 * Date: 3/5/12
 *
 * Input and sort characters.
 *
 ********************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function declarations
void getCharacters(const char *ptr, int count);
int compareCharacters(const void *a, const void *b);
void printCharacters(const char *chars);

// Macro declarations
#define FIRST_ARRAY_SIZE 3
#define SECOND_ARRAY_SIZE 7

int main(void) {
    char firstToCompare[FIRST_ARRAY_SIZE],
         secondToCompare[SECOND_ARRAY_SIZE];
    
    // Get 3 characters into a string; anything else is truncated.
    getCharacters(firstToCompare, FIRST_ARRAY_SIZE);
    
    // Call our comparator
    qsort(firstToCompare, FIRST_ARRAY_SIZE, sizeof(char), compareCharacters);
    
    // Print the sorted list
    printCharacters(firstToCompare);
    
    // Get 7 characters into a string; anything else is truncated.
    getCharacters(secondToCompare, SECOND_ARRAY_SIZE);
    
    // Call our comparator
    qsort(secondToCompare, SECOND_ARRAY_SIZE, sizeof(char), compareCharacters);
    
    // Print the sorted list
    printCharacters(secondToCompare);
        
    return 0;
}

// Get a specific number of characters and shove it
// into a (allocated, thanks to char[4] decl.) pointer.
void getCharacters(const char *ptr, int count) {
    char format[4];
    sprintf(format, "%%%ds", count);
    printf("Enter %d characters: > ", count);
    scanf(format, ptr);
}

// A sort function compatible with qsort.
// Values are passed as immutable pointers and dereferenced.
int compareCharacters(const void *aPtr, const void *bPtr) {
    const char *a = (char *)aPtr;
    const char *b = (char *)bPtr;
    return *a - *b;
}

// Print a sorted character array
void printCharacters(const char *chars) {
    int count = strlen(chars),
        i = 0;
        
    printf("\n");
        
    for (i; i < count; i++) {
        char *descriptor = NULL;
        
        if (i == 0)
            descriptor = "Smallest";
        else if (i == count - 1)
            descriptor = "Largest";
        else if (count > 4)
            if (i == 1)
                descriptor = "2nd smallest";
            else if (i == 2)
                descriptor = "3rd smallest";
            else if (i == count - 2)
                descriptor = "2nd largest";
            else if (i == count - 3)
                descriptor = "3rd largest";
            else
                descriptor = "Middle";
        else
            descriptor = "Middle";
        
        printf("%s character:\t\t%c\n", descriptor, chars[i]);
    }
    printf("\n");
}