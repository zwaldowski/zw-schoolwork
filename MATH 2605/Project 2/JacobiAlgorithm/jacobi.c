//
//  Jacobi Algorithm
//  Jacobi Algorithm Project
//  Project 2
//
//  Zachary Waldowski - gtID 902897777
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#include "jacobi.h"
#include <stdio.h>
#include <math.h>
#include <float.h>
#include "util.h"

double JacobiSquareSumOffDiagonal(MatrixRef mat, MatrixLocation *outLargestLocation) {
    if (mat == NULL || !MatrixIsSquare(mat)) return NAN;
    MatrixLocation largestLocation;
    double temp = 0, largestOff = DBL_MIN, off = 0;
        
    if (outLargestLocation) *outLargestLocation = (MatrixLocation){0, 0};
    
    for (int i = 0; i < MatrixGetRows(mat); i++) {
        for (int j = 0; j < MatrixGetColumns(mat); j++) {
            if (i == j) continue;
            
            temp = MatrixGet(mat, i, j);
            
            if (outLargestLocation) {
                temp = fabs(temp);
                if (temp > largestOff) {
                    largestOff = temp;
                    largestLocation.i = i;
                    largestLocation.j = j;
                }
            }
            
            off += pow(temp, 2);
        }
    }
    
    if (outLargestLocation) *outLargestLocation = largestLocation;
    
    return off;
}

MatrixRef JacobiCopy2x2NormEigenspace(MatrixRef a) {
    // Copy matrix to local variables.
    double a00 = MatrixGet(a, 0, 0),
    a01 = MatrixGet(a, 0, 1),
    a11 = MatrixGet(a, 1, 1);
    
    // Initial eigenvectors.
    double v00 = 1.0, v01 = 0.0;
    double v10 = 0.0, v11 = 1.0;
    
    // If off-diagonal element is non-zero, zero it with a Jacobi rotation.
    if (a01!=0.0) {
        double tiny = 0.1*sqrt(DBL_EPSILON); // avoid overflow in r*r below
        double c,r,s,t,u,vpr,vqr;
        u = a11-a00;
        if (fabs(a01) < tiny * fabs(u)) {
            t = a01/u;
        } else {
            r = 0.5*u/a01;
            t = (r>=0.0)?1.0/(r+sqrt(1.0+r*r)):1.0/(r-sqrt(1.0+r*r));
        }
        c = 1.0/sqrt(1.0+t*t);
        s = t*c;
        u = s/(1.0+c);
        r = t*a01;
        a00 -= r;
        a11 += r;
        //a01 = 0.0;
        vpr = v00;
        vqr = v10;
        v00 = vpr-s*(vqr+vpr*u);
        v10 = vqr+s*(vpr-vqr*u);
        vpr = v01;
        vqr = v11;
        v01 = vpr-s*(vqr+vpr*u);
        v11 = vqr+s*(vpr-vqr*u);
    }
    
    // Copy eigenvectors
    MatrixRef v = MatrixCreate(2, 2);
    MatrixSet(v, 0, 0, v00);
    MatrixSet(v, 0, 1, v01);
    MatrixSet(v, 1, 0, v10);
    MatrixSet(v, 1, 1, v11);
    
    MatrixRef out = MatrixCreateTranspose(v);
    MatrixDestroy(v);
    return out;
}

MatrixRef JacobiCopyLargestSubmatrix(MatrixRef mat, MatrixLocation loc) {
    MatrixRef out = MatrixCreate(2, 2);
    MatrixSet(out, 0, 0, MatrixGet(mat, loc.i, loc.i));
    MatrixSet(out, 0, 1, MatrixGet(mat, loc.i, loc.j));
    MatrixSet(out, 1, 0, MatrixGet(mat, loc.j, loc.i));
    MatrixSet(out, 1, 1, MatrixGet(mat, loc.j, loc.j));
    return out;
}

MatrixRef JacobiCreateGivensRotation(MatrixRef U, unsigned int dim, MatrixLocation loc) {
    if (U == NULL || !MatrixIsSquare(U)) return NULL;
    MatrixRef G = MatrixCreateIdentity(dim);
    MatrixSet(G, loc.i, loc.i, MatrixGet(U, 0, 0));
    MatrixSet(G, loc.i, loc.j, MatrixGet(U, 0, 1));
    MatrixSet(G, loc.j, loc.i, MatrixGet(U, 1, 0));
    MatrixSet(G, loc.j, loc.j, MatrixGet(U, 1, 1));
    return G;
}

extern MatrixRef Jacobi(MatrixRef A, BOOL sorting, const double endingCondition) {
    if (A == NULL || !MatrixIsSquare(A)) return NULL;
    
    MatrixLocation loc = {.i = 0, .j = 1};
    
    const unsigned int dim = MatrixGetSize(A);
    
    const double offA = JacobiSquareSumOffDiagonal(A, sorting ? &loc : NULL);
    const double lnOffA = log(offA);
    const double lnNineTenths = log(0.9);
    double offB = offA;
    unsigned int k = 1;
    
    printf("k, off(B), ln(off(B)), y\n");
    printf("0, %e, %e, %e\n", offA, log(offB), lnOffA);
    
    MatrixRef B = MatrixCreateCopy(A);
    MatrixRef V = MatrixCreateIdentity(dim);
        
    while (offB > endingCondition) {
        
        // 1: Find the off-diagonal element of B with the largest absolute value.
        MatrixRef sub = JacobiCopyLargestSubmatrix(B, loc);
        
        // 2: For the 2x2 submatrix, let U be a rotation matrix so that U^t * sub * U is diagonal
        MatrixRef U = JacobiCopy2x2NormEigenspace(sub);
        
        // 3: Let theta be the angle of rotation of the matrix U found above.
        MatrixRef G = JacobiCreateGivensRotation(U, dim, loc);
        MatrixRef Gt = MatrixCreateTranspose(G);
        
        // 3a: Assign B = G(theta, i, j)^t * B * G(theta, i, j)
        MatrixRef newB = MatrixMultiplyTriple(Gt, B, G);
        MatrixDestroy(B); B = newB;
        
        // 3b: Assign V = V * G(theta, i, j)
        MatrixRef newV = MatrixMultiply(V, G);
        MatrixDestroy(V); V = newV;
        
        // 4: Find next off(B)
        offB = JacobiSquareSumOffDiagonal(B, sorting ? &loc : NULL);
        
        printf("%d, %e, %e, %e\n", k, offB, log(offB), (k * lnNineTenths) + lnOffA);
        
        MatrixDestroy(Gt); Gt = NULL;
        MatrixDestroy(G); G = NULL;
        MatrixDestroy(U); U = NULL;
        MatrixDestroy(sub); sub = NULL;
        
        k++;
        
        if (!sorting) { // manually manipulate loc
            // last column, increment row, reset column
            if (loc.j == dim - 1) {
                loc.i++;
                loc.j = loc.i+1;
                if (loc.i >= dim || loc.j >= dim) {
                    loc = (MatrixLocation){.i = 0, .j = 1};
                }
            } else {
                loc.j++;
            }
        }
    }
    
    MatrixDestroy(V); V = NULL;
    return B;
}
