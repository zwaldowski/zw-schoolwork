//
//  Utilities
//  Jacobi Algorithm Project
//  Project 2
//
//  Zachary Waldowski - gtID 902897777
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#include "util.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

const char *BOX_TOP_LEFT = "⎡";
const char *BOX_TOP_RIGHT = "⎤";
const char *BOX_BOTTOM_LEFT = "⎣";
const char *BOX_BOTTOM_RIGHT = "⎦";
const char *BOX_VERTICAL = "⎪";

void *Copy1DArray(void *src, unsigned int typeSize, unsigned int count) {
    if (src == NULL) return NULL;
    
    const unsigned int size = typeSize * count;
    void *out = malloc(size);
    memcpy(out, src, size);
    return out;
}

void *Create1DArray(unsigned int typeSize, unsigned int count) {
    return calloc(typeSize, count);
}

void **Copy2DArray(void **src, unsigned int typeSize, unsigned int width, unsigned int height) {
    if (src == NULL) return NULL;
    
    void **out = malloc(typeSize * width);
    for (unsigned int i = 0; i < width; i++) {
        out[i] = Copy1DArray(src[i], typeSize, height);
    }
    return out;
}

void **Copy2DArrayTranspose(void **src, unsigned int typeSize, unsigned int width, unsigned int height) {
    if (src == NULL || width == 0) return NULL;

    void **out = malloc(typeSize * height);
    for (unsigned int i = 0; i < height; i++) {
        out[i] = malloc(typeSize * width);
        for (unsigned int j = 0; j < width; j++) {
            memcpy(out[i] + (j * typeSize), src[j] + (i * typeSize), typeSize);
        }
    }
    return out;
}

void **Create2DArray(unsigned int typeSize, unsigned int width, unsigned int height) {
    void **values = calloc(width, sizeof(void *));
    for (unsigned int i = 0; i < width; i++) {
        values[i] = calloc(height, typeSize);
    }
    return values;
}

void Destroy2DArray(void **array, unsigned int width) {
    for (unsigned int i = 0; i < width; i++) {
        free(array[i]);
    }
    free(array);
}

double drandom() {
    // Seed the generator if it hasn't been.
    static BOOL hasSeeded = NO;
    if (!hasSeeded) {
        srand((unsigned int)time(NULL));
        hasSeeded = YES;
    }
    
    return (double)(rand() % 50);
}
