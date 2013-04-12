Project 2 README
----------------

### Zachary Waldowski, gtID #902897777
### MATH 2605, Spring 2013, Georgia Tech

#### Basic info

This project was written in C. It is compatible with most major C toolchains and has been tested with LLVM-GCC 4.2, Clang 4.2, and MinGW GCC 4.7. It has been compiled and tested on Windows 8 x86_64 and Mac OS X 10.8.

The following precompiled versions are included:

 * **PinballProject-i386.exe** for use on Microsoft Windows 8 and 7, possibly XP
 * **PinballProject-osx** for use on any Intel Mac (i386 or x86_64) running 10.6 or better
 
#### Compiling
 
The program can generally be compiled using the following template, as run from the directory containing this file:

    cc -std=c99 -Os -Wall -IJacobiAlgorithm -o PinballProject JacobiAlgorithm/main.c JacobiAlgorithm/matrix.c JacobiAlgorithm/jacobi.c JacobiAlgorithm/util.c -lm
    
You can see platform-specific syntaxes reflected in the minimal makefiles in this directory, `Makefile.win`, `Makefile.lnx`, and `Makefile.osx`.

#### Running

The program can be invoked from any command prompt without any arguments. The program will prompt for any input as needed.

    //////////////////////////////////////////////////////////
     This program will successively run iterations of the     
     Jacobi Algorithm for diagonalization until some form of  
     convergence is reached.
    //////////////////////////////////////////////////////////
    Sort to decide submatrices? (Y/N) > Y
    Demo mode? (Single iteration w/ matrices.) (Y/N) > N
    Enter a number of iterations. > 10
    //////////////////////////////////////////////////////////    
    
The first prompt decides whether to use the original version of the Jacobi Algorithm (`Y`) or a sortless variant (`N`). The following prompt decides whether to perform a Jacobi diagonalization on just one matrix (`Y`), including showing the input and output matrix, or on many without debugging output (`N`). Finally, if you picked (`N`) to do many iterations, you are prompted for the number of iterations to complete.

#### Output

Iterations of a Jacobi algorithm are output as follows:

    k, off(B), ln(off(B)), y
    0, 5.455400e+04, 1.090695e+01, 1.090695e+01
    1, 4.207200e+04, 1.064714e+01, 1.080159e+01
    …
    28, 3.589649e-10, -2.174780e+01, 7.956852e+00
    
The column `k` is the number of iterations that have been done on the matrix. `off(B)` is the value formed by completing the off-diagonal square sum of all items in the matrix. `ln(off(B))` is the natural log derived from the previous value. Finally, the final value `y` is the theoretical bound of the given Jacobi algorithm for this iteration `x`, expressed by the formula `x * ln(1-2/(n^2-2n)) + ln(Off(A))`.

The first line `k = 0`, reflects the off-diagonal-square-sum and its natural log for the initial matrix. 

Do note that the entire output can be copied-and-pasted into a `.CSV` and analyzed using your favorite spreadsheet software.

#### Notes
  
 Platform-wise, on Linux `cc` can in-theory be any C99-compatible compiler, though you probably want to use `gcc`. On OS X, using `clang` results in a much better-performing binary, though you can likewise use `gcc` or `llvm-gcc`. The Windows version was compiled and tested using MinGW, `cc` for a typical install. The MSVC toolchain may work but was not tested. 