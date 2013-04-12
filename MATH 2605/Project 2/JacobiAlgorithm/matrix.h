//
//  Matrices (header)
//  Jacobi Algorithm Project
//  Project 2
//
//  Zachary Waldowski - gtID 902897777
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#ifndef JacobiAlgorithm_matrix_h
#define JacobiAlgorithm_matrix_h

#include "util.h"

struct _Matrix;
typedef struct _Matrix *MatrixRef;

extern MatrixRef MatrixCreate(unsigned int rows, unsigned int columns);
extern MatrixRef MatrixCreateWithValues(double **values, unsigned int rows, unsigned int columns);
extern MatrixRef MatrixCreateCopy(MatrixRef mat);

extern MatrixRef MatrixCreateIdentity(unsigned int dim);
extern MatrixRef MatrixCreateTranspose(MatrixRef matrix);
extern MatrixRef MatrixCreateRandomSymmetric(unsigned int dim);

extern void MatrixDestroy(MatrixRef matrix);

extern unsigned int MatrixGetColumns(MatrixRef mat);
extern unsigned int MatrixGetRows(MatrixRef mat);
extern unsigned int MatrixGetSize(MatrixRef mat);
extern BOOL MatrixIsSquare(MatrixRef mat);
extern void MatrixPrint(MatrixRef matrix);

extern double MatrixGet(MatrixRef matrix, unsigned int i, unsigned int j);
extern void MatrixSet(MatrixRef matrix, unsigned int i, unsigned int j, double value);

extern MatrixRef MatrixAdd(MatrixRef left, MatrixRef right);
extern MatrixRef MatrixMultiply(MatrixRef left, MatrixRef right);
extern MatrixRef MatrixMultiplyTriple(MatrixRef left, MatrixRef middle, MatrixRef right);
extern MatrixRef MatrixMultiplyScalar(MatrixRef mat, double scalar);
extern void MatrixSwapRows(MatrixRef mat, unsigned int i1, unsigned i2);

#endif
