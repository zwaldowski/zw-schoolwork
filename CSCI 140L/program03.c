/*******************************************************
 * CSCI 140-01
 * Program 3
 * Zachary Waldowski
 * Date: 4/5/12
 *
 * A character stack.
 *
 ********************************************************/

#include <stdio.h>

// Function declarations
void get_menu_response(int *response);
void print_stack(char stack[], int size);

void push(char stack[], int maxSize, int *currentSize, char value);
char pop(char stack[], int maxSize, int *currentSize);

char get_character(void);
void push_character(char stack[], int maxSize, int *currentSize);

// Macro definitions
#define STACK_SIZE 25

int main(void) {
    // Variable declarations
    char stack[STACK_SIZE], poppedValue;
    int i = 0, response = -2, top = 0;
    
    // configure initial values
    for (i = 0; i < STACK_SIZE; i++) {
        stack[i] = '_';
    }
    
    // control loop, bails on -1
    while (response != -1) {
        get_menu_response(&response);
        
        // If 1, push.
        // If 2, pop.
        // If 3 or -1, dump the stack.
        switch (response) {
            case 1:
                push_character(stack, STACK_SIZE, &top);
                break;
            case 2:
                poppedValue = pop(stack, STACK_SIZE, &top);
                if (poppedValue == '_') {
                    printf("\nCouldn't pop anything.\n");
                } else {
                    printf("\nPopped the character \"%c\".\n", poppedValue);
                }
                break;
            case -1:
            case 3:
                print_stack(stack, top);
                break;
            default: break;
        }
    }
    
    return 0;
}

// Prints and returns a picked menu value.
void get_menu_response(int *response) {
    int ret;
    
    printf("Enter:\n");
    printf("  1 to push a value.\n");
    printf("  2 to pop a value.\n");
    printf("  3 to print all values.\n");
    printf(" -1 to exit.\n");
    printf(" > ");
    scanf("%d", &ret);
    
    if (response)
        *response = ret;
}

// Prints the entire stack in order of add.
void print_stack(char stack[], int size) {
    int i;
    
    printf("\nThe contents of the stack:\n");
    for (i = size - 1; i >= 0; i--) {
        printf("\t%c\n", stack[i]);
    }
    printf("\n");
}

// Pushes a value onto the stack and increases the stack size.
// If it would cause us to go over the maximum, bail out.
void push(char stack[], int maxSize, int *currentSize, char value) {
    if (*currentSize == maxSize - 1)
        return;
    
    stack[*currentSize] = value;
    
    (*currentSize)++;
}

// Pops a value from the stack, replacing it with '_'.
// Returns the popped value.
// If there's nothing in the stack, returns '_'.
char pop(char stack[], int maxSize, int *currentSize) {
    if (*currentSize == 0)
        return '_';
        
    int pos;
    char oldValue;
    
    pos = *currentSize - 1;
    oldValue = stack[pos];
    stack[pos] = '_';
    *currentSize = pos;
    
    return oldValue;
}

// Gets an inputted character.
char get_character(void) {
    char ret;
    printf("\nEnter a character. > ");
    scanf(" %c", &ret);
    return ret;
}

// Convenience function. Gets a character, pushes it.
void push_character(char stack[], int maxSize, int *currentSize) {
    char value = get_character();
    push(stack, maxSize, currentSize, value);
}