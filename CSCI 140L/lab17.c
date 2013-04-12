/*******************************************************
 * CSCI 140L-1
 * Lab 17
 * Zachary Waldowski
 * Date: 4/16/12
 *
 * Plural nouns FTW.
 *
 ********************************************************/

#include <stdio.h>   // For printf/scanf
#include <stdlib.h>  // For malloc/free
#include <string.h>  // memcpy

// Function declarations
char *askForNoun(int *outSize);
int hasSuffix(const char *string, const char *search);

int main(void) {
    // Variable declarations
    int nounSize, pluralNounSize;
    char *noun, *pluralNoun = NULL;
    
    // Ask for and get a character string from the user.
    noun = askForNoun(&nounSize);
    
    if (hasSuffix(noun, "y")) {
        pluralNounSize = nounSize + 3;
        pluralNoun = calloc(sizeof(char), pluralNounSize);
        memcpy(pluralNoun, noun, nounSize - 1);
        strcat(pluralNoun, "ies");
    } else if (hasSuffix(noun, "s") || hasSuffix(noun, "ch") || hasSuffix(noun, "sh")) {
        pluralNounSize = nounSize + 2;
        pluralNoun = calloc(sizeof(char), pluralNounSize);
        memcpy(pluralNoun, noun, nounSize);
        strcat(pluralNoun, "es");
    } else {
        pluralNounSize = nounSize + 1;
        pluralNoun = calloc(sizeof(char), pluralNounSize);
        memcpy(pluralNoun, noun, nounSize);
        strcat(pluralNoun, "s");
    }
    
    // Print the results
    printf("\nYou entered the word:\t%s", noun);
    printf("\nThe plural version is:\t%s", pluralNoun);
    
    // Free allocated memory
    if (noun)
        free(noun);
    
    if (pluralNoun)
        free(pluralNoun);
    
    // Hasta la vista, baby
    return 0;
}

// Gets an allocated character array
char *askForNoun(int *outSize) {
    char *ret = malloc(sizeof(char) * 32);
    while (!strlen(ret)) {
        printf("Enter a noun. > ");
        scanf("%31s", ret);
    }
    if (outSize)
        *outSize = strlen(ret);
    return ret;
}

// Compares the end of a string for a substring
int hasSuffix(const char *string, const char *suffix) {
    int string_len = strlen(string);
    int suffix_len = strlen(suffix);
    
    if (string_len <= suffix_len)
        return 0;
    
    if (strcmp(suffix, string + string_len - suffix_len) == 0)
        return string_len - suffix_len;
    
    return 0;
}