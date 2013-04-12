Project 1 README
----------------

### Zachary Waldowski, gtID #902897777
### MATH 2605, Spring 2013, Georgia Tech

#### Basic info

This project was written in C. It is compatible with most major C toolchains and has been tested with GCC 4.2, GCC 4.7, LLVM-GCC 4.2, Clang 4.2, and MinGW GCC 4.7. It has been compiled and tested on Windows 7 x86_64, Mac OS X 10.8, and Ubuntu 12.10 x86_64.

The following precompiled versions are included:

 * **PinballProject-i386.exe** for use on Microsoft Windows 7, possibly XP
 * **PinballProject-x86_64-lnx** for use on any 64-bit Linux*
 * **PinballProject-osx** for use on Intel Mac running 10.6 or better
 
#### Compiling
 
The program can generally be compiled using the following template, as run from the directory containing this file:

    cc -std=c99 -Os -Wall -IPinballProject -o PinballProject PinballProject/geom.c PinballProject/main.c -lm
    
You can see platform-specific syntaxes reflected in the minimal makefiles in this directory, `Makefile.win`, `Makefile.lnx`, and `Makefile.osx`.

#### Running

The program can be invoked from any command prompt without any arguments. The program will prompt for any input as needed.

    Enter a length of the triangle (s). > 6
    Enter the radius of the circles (r). > 2
    Enter a number of iterations. > 10000000
    Should the angles chosen be random? (Y/N) > N
    Debug mode? (Starting angles and hit circles.) (Y/N) > N
    
The first two prompts are your typical s and r values. The following prompt is i, the number of testing iterations. It can be any number. My common test was with ten million iterations. The next prompt is a systematic/random switch, Y for random. The final option should probably be left at N for speed purposes.

#### Notes
 
 (*) Re: pre-compiled Linux; preferably with GLIBC 2.15 although it was compiled statically and should work on any 64-bit Linux
 
 Platform-wise, on Linux `cc` can in-theory be any C99-compatible compiler, though you probably want to use `gcc`. On OS X, using `clang` results in a much better-performing binary, though you can also use `gcc` or `llvm-gcc`. The Windows version was compiled and tested using MinGW, `cc` for a typical install. The MSVC toolchain may work but was not tested. 