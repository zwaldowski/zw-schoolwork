/*******************************************************
 * CSCI 140L-1
 * Lab 16
 * Zachary Waldowski
 * Date: 4/9/12
 *
 * Fun with two-dimensional arrays.
 *
 ********************************************************/

#include <stdio.h>   // For printf/scanf
#include <stdlib.h>  // For malloc/free
#include <time.h>    // For randomization
#include <string.h>  // Memcpy

// Macro declarations
#define RANDOM_MINIMUM 0

// Function declarations
void description(void);
unsigned int ask_for(char *message);

int random_number(int minimum, int maximum);
void print_grid(int **grid, int numberOfRows, int numberOfColumns);

int compareAscending(const void *aPtr, const void *bPtr);
int find_smallest(int *row, int size);

int main(void) {
    // Variable declarations
    int **grid,
        *smallestCells,
        numberOfRows,
        numberOfColumns,
        maximum,
        i, j;
    
    // Show a description
    description();
    
    // Get all of our parameters
    numberOfRows = ask_for("a number of rows");
    numberOfColumns = ask_for("a number of columns");
    maximum = ask_for("a maximum random number");
    
    // Allocate our arrays
    grid = malloc(sizeof(int *) * numberOfRows);
    smallestCells = malloc(sizeof(int *) * numberOfRows);
    for (i = 0; i < numberOfRows; i++) {
        grid[i] = malloc(sizeof(int) * numberOfColumns); 
    }
    
    // Fill our grid up
    for (i = 0; i < numberOfRows; i++) {
        for (j = 0; j < numberOfColumns; j++) {
            grid[i][j] = random_number(RANDOM_MINIMUM, maximum + 1);
        }
    }
    
    printf("\n");
    
    // Print the whole she-bang.
    print_grid(grid, numberOfRows, numberOfColumns);
    
    // Find and print the smallest number in each row.
    printf("Smallest number in each row:\n");
    for (i = 0; i < numberOfRows; i++) {
        smallestCells[i] = find_smallest(grid[i], numberOfColumns);
        printf("\tRow %d:\t%d\n", i + 1, smallestCells[i]);
    }
    
    // Display the smallest number in the whole array
    printf("\nSmallest number in whole grid: %d", find_smallest(smallestCells, numberOfRows));
    
    // Free our arrays
    for (i = 0; i < numberOfRows; i++) {
        free(grid[i]);
        grid[i] = NULL;
    }
    free(smallestCells); smallestCells = NULL;
    free(grid); grid = NULL;
    
    return 0;
}

// Prints a program description
void description(void) {
        printf("//////////////////////////////////////////////////////////\n");
        printf(" This program will generate a grid of random numbers based\n");
        printf(" on information you input.                                \n\n");
        printf(" You may need to resize your terminal to see every column.\n");
        printf("//////////////////////////////////////////////////////////\n\n");

}

// Asks for and returns a positive integer with given message.
unsigned int ask_for(char *message) {
    int ret = 0;
    while (ret <= 0) {
        printf("Enter %s. > ", message);
        scanf("%d", &ret);
    }
    return ret;
}

// Gets a random number between two values.
// Seed the randum number generator once and only once if necessary.
int random_number(int minimum, int maximum) {
    // Seed the generator if it hasn't been.
    static int hasSeeded = 0;
    if (hasSeeded = 0) {
        srand(time(NULL));
        hasSeeded = 1;
    }
    
    return ((rand() % (maximum - minimum)) + minimum);
}

// Prints an entire 2D array with the given metrics.
void print_grid(int **grid, int numberOfRows, int numberOfColumns) {
    int i, j;
    
    printf("The whole grid:\n\n");
    for (i = 0; i < numberOfRows; i++) {
            // Pretty print a list of the columns, like in a spreadsheet.
            if (i == 0) {
                printf("           ");
                for (j = 0; j < numberOfColumns; j++) {
                    printf("%10d ", j+1);
                }
                printf("\n");
                for (j = 0; j <= numberOfColumns; j++) {
                    printf("----------+");
                }
                printf("\n");
            }
            
            // At the beginning of each row, print the row number.
            printf("%9d |", i+1);
            
            // Print each cell.
            for (j = 0; j < numberOfColumns; j++) {
                printf(" %8d |", grid[i][j]);
            }
        printf("\n");
    }
    printf("\n");
}

// int-based ascending comparator for qsort
int compareAscending(const void *aPtr, const void *bPtr) {
    const int *a = (int *)aPtr;
    const int *b = (int *)bPtr;
    return (int)(*a - *b);
}

int find_smallest(int *row, int size) {
    int *sorted = NULL, lowest;

    // Create a sorted array
    sorted = calloc(size, sizeof(*row));
    memcpy(sorted, row, size * sizeof(*row));
    qsort(sorted, size, sizeof(*row), compareAscending);
    
    // Get the lowest one
    lowest = sorted[0];
    
    // Free our sorted array
    free(sorted); sorted = NULL;
    
    return lowest;
}