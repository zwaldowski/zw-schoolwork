//
//  Main
//  Jacobi Algorithm Project
//  Project 2
//
//  Zachary Waldowski - gtID 902897777
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#include <stdio.h>
#include <stdlib.h>
#include "matrix.h"
#include "jacobi.h"

#define printSeparator() printf("//////////////////////////////////////////////////////////\n")
#define printLine() printf("\n")

BOOL promptShouldUseSorting(void) {
	BOOL out;
	do {
		printf("Sort to decide submatrices? (Y/N) > ");
		char select;
		if (!scanf(" %c", &select)) continue;
        
		if (select == 'Y' || select == 'y') {
			out = YES;
			break;
		} else if (select == 'N' || select == 'n') {
			out = NO;
			break;
		}
	} while (1);
	return out;
}

BOOL promptShouldUseDemo(void) {
	BOOL out;
	do {
		printf("Demo mode? (Single iteration w/ matrices.) (Y/N) > ");
		char select;
		if (!scanf(" %c", &select)) continue;
        
		if (select == 'Y' || select == 'y') {
			out = YES;
			break;
		} else if (select == 'N' || select == 'n') {
			out = NO;
			break;
		}
	} while (1);
	return out;
}

int promptIterations(void) {
	int d;
	do {
        printf("Enter a number of iterations. > ");
        if (!scanf(" %d", &d)) continue;
        
        if (d <= 0) {
            printf("Number of iterations must greater than zero.\n");
            continue;
        } else {
            break;
        }
    } while (1);
	return d;
}

int main(int argc, const char * argv[])
{
    static const double endingCondition = 0.000000001;
    
    printSeparator();
    printf(" This program will successively run iterations of the     \n");
    printf(" Jacobi Algorithm for diagonalization until some form of  \n");
    printf(" convergence is reacheed.\n");
    printSeparator();
    BOOL shouldSort = promptShouldUseSorting();
    BOOL demoMode = promptShouldUseDemo();
    int iterations = 1;
    if (!demoMode) {
        iterations = promptIterations();
    }
    
    for (int i = 0; i < iterations; i++) {
        printSeparator();
        printLine();
        
        MatrixRef A = MatrixCreateRandomSymmetric(5);
        double offA = JacobiSquareSumOffDiagonal(A, NULL);
        
        if (demoMode) {
            printf("Starting 5x5 symmetric matrix: \n\n");
            MatrixPrint(A);
            
            printLine();
            printf("Starting off(A): %6.2f\n\n", offA);
            
            printSeparator();
            printLine();
        }
        
        MatrixRef B = Jacobi(A, shouldSort, endingCondition);
        
        if (demoMode) {
            printLine();
            printSeparator();
            printLine();
            printf("Finishing matrix: \n\n");
            MatrixPrint(B);
        }
        
        MatrixDestroy(B); B = NULL;
        MatrixDestroy(A); A = NULL;
    }
    
#if defined(__MINGW32__) || defined(_WIN32)
    system("PAUSE");
#endif
    
    return 0;
}
