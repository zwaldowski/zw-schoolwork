//
//  Utilities (header)
//  Jacobi Algorithm Project
//  Project 2
//
//  Zachary Waldowski - gtID 902897777
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#ifndef JacobiAlgorithm_util_h
#define JacobiAlgorithm_util_h

#ifdef __cplusplus
typedef bool BOOL;
#define YES true
#define NO false
#elif __STDC_VERSION__ >= 199901L
typedef _Bool BOOL;
#define YES 1
#define NO 0
#else
typedef signed char BOOL;
#define YES ((BOOL)1)
#define NO ((BOOL)0)
#endif

extern const char *BOX_TOP_LEFT;
extern const char *BOX_TOP_RIGHT;
extern const char *BOX_BOTTOM_LEFT;
extern const char *BOX_BOTTOM_RIGHT;
extern const char *BOX_VERTICAL;

extern void *Copy1DArray(void *array, unsigned int typeSize, unsigned int count);
extern void *Create1DArray(unsigned int typeSize, unsigned int count);
extern void **Copy2DArray(void **array, unsigned int typeSize, unsigned int width, unsigned int height);
extern void **Copy2DArrayTranspose(void **array, unsigned int typeSize, unsigned int width, unsigned int height);
extern void **Create2DArray(unsigned int typeSize, unsigned int width, unsigned int height);
extern void Destroy2DArray(void **array, unsigned int width);

extern double drandom();

#endif
