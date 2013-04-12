/*******************************************************
 * CSCI 140L-1
 * Lab 14
 * Zachary Waldowski
 * Date: 3/26/12
 *
 * Fun with a random number generator.
 *
 ********************************************************/

// For printf/scanf
#include <stdio.h>

// For malloc/free
#include <stdlib.h>

// For randomization
#include <time.h>

// For memset
#include <string.h>

// Function declarations
int get_number(void);

int main(void) {
    // Seed the random number generator.
    srand(time(NULL));
    
    // Variable declarations
    int size = 0, i = 0;
    
    // Set the number the first time
    size = get_number();
    
    // Bail out when size is negative
    while (size > 0) {
        // Allocate our array
        int *counts = malloc(sizeof(int) * size);
        
        // Set all values to 0
        memset(counts, 0, sizeof(int) * size);
        
        // Generate 1000 random numbers; tally up the count
        for (i = 0; i < 1000; i++) {
            int r = rand() % size;
            counts[r]++;
        }
        
        printf("\n");
        
        // Print the tallies.
        for (i = 0; i < size; i++) {
            printf("\t%d was generated %d times.\n\n", i, counts[i]);
        }
        
        // Dealloc the array
        free(counts);
        
        // Set it again
        printf("\n");
        size = get_number();
    }
        
    return 0;
}


int get_number(void) {
    int ret;
    printf("Please enter a positive integer. (Negative to exit.) > ");
    scanf("%d", &ret);
    return ret;
}