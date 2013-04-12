//
//  Jacobi Algorithm (header)
//  Jacobi Algorithm Project
//  Project 2
//
//  Zachary Waldowski - gtID 902897777
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#ifndef JacobiAlgorithm_jacobi_h
#define JacobiAlgorithm_jacobi_h

#include "util.h"
#include "matrix.h"

typedef struct _MatrixLocation {
	unsigned int i;
	unsigned int j;
} MatrixLocation;

extern MatrixRef Jacobi(const MatrixRef A, BOOL sorting, double endingCondition);

extern double JacobiSquareSumOffDiagonal(const MatrixRef mat, MatrixLocation *outLargestLocation);

extern MatrixRef JacobiCopyLargestSubmatrix(const MatrixRef mat, const MatrixLocation loc);

extern MatrixRef JacobiCreateGivensRotation(const MatrixRef U, unsigned int dim, const MatrixLocation loc);

#endif
