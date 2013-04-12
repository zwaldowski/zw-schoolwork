//
//  Matrices
//  Jacobi Algorithm Project
//  Project 2
//
//  Zachary Waldowski - gtID 902897777
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#include "matrix.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "util.h"

typedef struct _Matrix {
    double **val; // row then col
    unsigned int row;
    unsigned int col;
} Matrix;

MatrixRef __MatrixCreateWithValuesNoCopy(double **values, unsigned int rows, unsigned int columns) {
    if (values == NULL) return NULL;
    
    MatrixRef out = malloc(sizeof(Matrix));
    
    out->val = values;
    out->row = rows;
    out->col = columns;
    
    return out;
}

MatrixRef MatrixCreate(unsigned int rows, unsigned int columns) {
    return __MatrixCreateWithValuesNoCopy((double **)Create2DArray(sizeof(double), rows, columns), rows, columns);
}

MatrixRef MatrixCreateWithValues(double **values, unsigned int rows, unsigned int columns) {
    return __MatrixCreateWithValuesNoCopy((double **)Copy2DArray((void **)values, sizeof(double), rows, columns), rows, columns);
}

extern MatrixRef MatrixCreateCopy(MatrixRef mat) {
    return MatrixCreateWithValues(mat->val, mat->row, mat->col);
}

MatrixRef MatrixCreateIdentity(unsigned int dim) {
    MatrixRef out = MatrixCreate(dim, dim);
    for (unsigned int i = 0; i < dim; i++) {
        out->val[i][i] = 1;
    }
    return out;
}

MatrixRef MatrixCreateTranspose(MatrixRef mat) {
    if (mat == NULL) return NULL;
    return __MatrixCreateWithValuesNoCopy((double **)Copy2DArrayTranspose((void **)mat->val, sizeof(double), mat->row, mat->col), mat->col, mat->row);
}

MatrixRef MatrixCreateRandomSymmetric(unsigned int dim) {
    MatrixRef left = MatrixCreate(dim, dim);
    for (unsigned int i = 0; i < dim; i++) {
        for (unsigned int j = 0; j < dim; j++) {
            left->val[i][j] = drandom();
        }
    }
    MatrixRef right = MatrixCreateTranspose(left);
    MatrixRef out = MatrixAdd(left, right);
    MatrixDestroy(left);
    MatrixDestroy(right);
    return out;
}

void MatrixDestroy(MatrixRef mat) {
    if (mat == NULL) return;
    Destroy2DArray((void **)mat->val, mat->row);
    free(mat);
}


unsigned int MatrixGetColumns(MatrixRef mat) {
    if (mat == NULL) return 0;
    return mat->col;
}

unsigned int MatrixGetRows(MatrixRef mat) {
    if (mat == NULL) return 0;
    return mat->row;
}

unsigned int MatrixGetSize(MatrixRef mat) {
    if (mat == NULL || !MatrixIsSquare(mat)) return 0;
    return mat->row;
}

extern BOOL MatrixIsSquare(MatrixRef mat) {
    if (mat == NULL) return NO;
    return (mat->col == mat->row);
}

void MatrixPrint(MatrixRef mat) {
    if (mat == NULL) return;
    for (unsigned int i = 0; i < mat->row; i++) {
        printf("%s", (i == 0 ? BOX_TOP_LEFT : (i == mat->row - 1 ? BOX_BOTTOM_LEFT : BOX_VERTICAL)));
        for (unsigned int j = 0; j < mat->col; j++) {
            printf("%10.6f ", mat->val[i][j]);
        }
        printf("%s\n", (i == 0 ? BOX_TOP_RIGHT : (i == mat->row - 1 ? BOX_BOTTOM_RIGHT : BOX_VERTICAL)));
    }
}



double MatrixGet(MatrixRef mat, unsigned int i, unsigned int j) {
    if (mat == NULL || i >= mat->row || j >= mat->col) {
        return NAN;
    }
    return mat->val[i][j];
}

void MatrixSet(MatrixRef mat, unsigned int i, unsigned int j, double value) {
    if (mat == NULL || i >= mat->row || j >= mat->col) {
        return;
    }
    
    mat->val[i][j] = value;
}



MatrixRef MatrixAdd(MatrixRef left, MatrixRef right) {
    if (left == NULL || right == NULL || left->row != right->row || left->col != right->col) {
        return NULL;
    }
    
    MatrixRef out = MatrixCreateCopy(left);
    for (int i = 0; i < left->row; i++) {
        for (int j = 0; j < left->col; j++) {
            out->val[i][j] += right->val[i][j];
        }
    }
    return out;
}

MatrixRef MatrixMultiply(MatrixRef left, MatrixRef right) {
    if (left == NULL || right == NULL) return NULL;
    
    unsigned int lRows = MatrixGetRows(left),
    rCols = MatrixGetColumns(right),
    lCols = MatrixGetColumns(left),
    rRows = MatrixGetRows(right);
    
    if (lCols != rRows) return NULL;
    
    MatrixRef out = MatrixCreate(lRows, rCols);

    for (unsigned int li = 0; li < lRows; li++) {
        for (unsigned int rj = 0; rj < rCols; rj++) {
            double temp = 0;
            for (int lj = 0, ri = 0; lj < lCols && ri < rRows; lj++, ri++) {
                temp += MatrixGet(left, li, lj) * MatrixGet(right, ri, rj);
            }
            MatrixSet(out, li, rj, temp);
        }
    }
    
    return out;
}

extern MatrixRef MatrixMultiplyTriple(MatrixRef left, MatrixRef middle, MatrixRef right) {
    if (left == NULL || middle == NULL || right == NULL) return NULL;
    
    MatrixRef leftTerm = MatrixMultiply(left, middle);
    MatrixRef out = MatrixMultiply(leftTerm, right);
    MatrixDestroy(leftTerm); leftTerm = NULL;
    return out;
}

MatrixRef MatrixMultiplyScalar(MatrixRef mat, double scalar) {
    if (mat == NULL) return NULL;
    
    MatrixRef out = MatrixCreateCopy(mat);
    for (unsigned int i = 0; i < out->row; i++) {
        for (unsigned int j = 0; j < out->col; j++) {
            out->val[i][j] *= scalar;
        }
    }
    return out;
}

void MatrixSwapRows(MatrixRef mat, unsigned int idx1, unsigned idx2) {
    if (mat == NULL || idx1 >= mat->row || idx2 >= mat->row) return;
    
    double *one = mat->val[idx1];
    mat->val[idx1] = mat->val[idx2];
    mat->val[idx2] = one;
}
