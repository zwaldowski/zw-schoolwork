//
//  Main Program
//  Pinball Project
//  Project 1
//
//  Zachary Waldowski - gtID 902897777.
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <math.h>
#include <time.h>
#include <float.h>
#include <string.h>
#include "defs.h"
#include "geom.h"

// Actual Calculus

//
// Intersection formula, basically the Quadratic Formula
// t = v dot (c - x) +- sqrt(D), where D = (v dot (c-x))^2 - (c-x)dot(c-x) + r^2
// Modified for efficiency
//
double intersect(Circle circle, Particle particle, BOOL *hasSolution) {
	Point2D location = ParticleGetPosition(particle);
	Vector2D velocity = Vector2DGetUnitVector(ParticleGetVelocity(particle));
	Point2D cMinusX = Point2DSubtract(CircleGetCenter(circle), location);
	double vDotCMinusX = Vector2DDotProductPoint(velocity, cMinusX);

	if (vDotCMinusX <= 0) {
		if (hasSolution) *hasSolution = NO;
		return 0;
	}

	double vDotCMinusXSquare = pow(vDotCMinusX, 2);
	double cMinusXDotP = Point2DDotProduct(cMinusX, cMinusX);
	double rSquare = pow(CircleGetRadius(circle), 2);
	double sheWantsThe = vDotCMinusXSquare - cMinusXDotP + rSquare;

	if (sheWantsThe <= 0) {
		if (hasSolution) *hasSolution = NO;
		return 0;
	}

	if (hasSolution) *hasSolution = YES;
	return vDotCMinusX - sqrt(sheWantsThe);
}

//
// Vector reflection formula
// w = v - ((2v dot (c-x))/(c-x)dot(c-x))) * (c-x)
//
Vector2D reflect(Circle circle, Point2D newLocation, Vector2D oldVelocity) {
	Vector2D oldUVelocity = Vector2DGetUnitVector(oldVelocity);
	Point2D cMinusX = Point2DSubtract(CircleGetCenter(circle), newLocation);

	Point2D twoV = Vector2DMultiplyScalar(oldUVelocity, 2);
	double twoVDotCMinusX = Point2DDotProduct(twoV, cMinusX);
	double cMinusXDotP = Point2DDotProduct(cMinusX, cMinusX);
	double fraction = twoVDotCMinusX / cMinusXDotP;
	Point2D subtractor = Point2DMultiplyScalar(cMinusX, fraction);
	return Vector2DSubtractPoint(oldUVelocity, subtractor);
}

// Helpers

//
// Use the c rand function to generate a random radian between 0 and 2*pi.
//
double randomAngle(void) {
    // Seed the generator if it hasn't been.
	// This isn't thread-safe, but neither is this function, so...
    static BOOL hasSeeded = NO;
    if (!hasSeeded) {
	srand((unsigned)time(NULL));
	hasSeeded = YES;
    }

	static double const radMin = 0, radMax = 2 * M_PI;
    double f = (double)rand() / RAND_MAX;
    return radMin + f * (radMax - radMin);
}

//
// Helper function to get the minimum of three doubles
//
double min3(double a, double b, double c) {
	#define MIN(X,Y) ((X) < (Y) ? (X) : (Y))
	return MIN(a, MIN(b, c));
}

//
// Convert radians back to degrees.
//
double rad2deg(double radians) {
	return radians * (180.f / M_PI);
}

// User input

//
// Prints a description of the program.
//
void showDescription(void) {
    printf("////////////////////////////////////////////////////////////////////////////////\n");
    printf(" Imagine an equilateral triangle with side length 's'. There are three discs    \n");
    printf(" with radius 'r', placed with their centers at the edges of the triangle. A ball\n");
    printf(" travels in a random direction. This program measures, the number of times the  \n");
    printf(" ball hits a circle before 'escaping' the triangle. The number of iterations of \n");
    printf(" the program decides how many random angles are used. It is also up to you      \n");
    printf(" whether the angles chosen are random or systematically based on iterations.    \n");
    printf("////////////////////////////////////////////////////////////////////////////////\n");
}

//
// User input; gets the length and radius of the triangle and circles
//
void promptLengthAndRadius(double *sOut, double *rOut) {
	double s, r;
    do {
	printf("Enter a length of the triangle (s). > ");
	if (!scanf("%lf", &s)) continue;

	printf("Enter the radius of the circles (r). > ");
	if (!scanf("%lf", &r)) continue;

	if (s < 2) {
		printf("Length of the triangle must be at least 2.\n");
		continue;
	} else if (r < 1) {
		printf("Radius of the circles must be at least 1.\n");
		continue;
	} else if (r >= s / 2) {
		printf("Radius of circles must be less than half of triangle length.\n");
		continue;
	} else {
		break;
	}
    } while (1);
	if (sOut) *sOut = s;
	if (rOut) *rOut = r;
}

//
// More user input; gets the iterations we should run our little random thing.
//
int promptIterations(void) {
	int d;
	do {
	printf("Enter a number of iterations. > ");
	if (!scanf("%d", &d)) continue;

	if (d <= 0) {
		printf("Number of iterations must greater than zero.\n");
		continue;
	} else {
		break;
	}
    } while (1);
	return d;
}

//
// Even more user input. Asks whether angles should be random.
//
BOOL promptShouldUseRandom(void) {
	BOOL out;
	do {
	printf("Should the angles chosen be random? (Y/N) > ");
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

//
// Still more user input. Asks whether we should print out advanced values.
//
BOOL promptShouldUseDebug(void) {
	BOOL out;
	do {
		printf("Debug mode? (Starting angles and hit circles.) (Y/N) > ");
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

//
// User input -_-; gets the minimum bound for debugging.
//
int promptMinimumBound(void) {
	int d;
	do {
	printf("(DEBUG) Enter the minimum bound for recording debug values. > ");
	int out = scanf("%d", &d);

	if (d <= 1 || out != 1) {
		printf("Number of iterations must greater than one.\n");
		continue;
	} else {
		break;
	}
    } while (1);
	return d;
}

int appendToStringBuffer(char *target, size_t targetSize, const char * restrict format, ...)
{
	va_list args;
	char temp[targetSize];
	int result;

	va_start(args, format);
	result = vsnprintf(temp, targetSize, format, args);
	if (result != EOF)
	{
		if (strlen(temp) + strlen(target) > targetSize)
		{
			return 0;
		}
		strcat(target, temp);
	}
	va_end(args);
	return result;
}

// Main

//
// Main entry point of the program.
// This includes the bulk of our iterative method and the counter
//
int main(int argc, const char * argv[])
{
	// Hey, look, a description!
	showDescription();

	// Prompt for control values.
	double s, r;
	promptLengthAndRadius(&s, &r);
	const int iterations = promptIterations();
	BOOL shouldBeRandom = promptShouldUseRandom();
	BOOL shouldUseDebug = promptShouldUseDebug();
	int debugMin = 0;

	if (shouldUseDebug) {
		debugMin = promptMinimumBound();
	}

	double deltaTheta = 0.0f;
	if (!shouldBeRandom) {
		deltaTheta = (2 * M_PI) / iterations;
	}

	// Setup our circles
	Circle rightCircle = CircleMake(Point2DMake(s / 2, -s * (sqrt(3) / 6)), r);
	Circle leftCircle = CircleMake(Point2DMake(-s / 2, -s * (sqrt(3) / 6)), r);
	Circle topCircle = CircleMake(Point2DMake(0, s * (sqrt(3) / 3)), r);

	// Setup graph
	static const int maxValue = 1000;
	unsigned int *values = calloc(sizeof(unsigned int), maxValue);
	int largestValue = 0;

	int i;
	for (i = 0; i < iterations; i++) {
		// Create a particle pointing in a direction
		double angle = shouldBeRandom ? randomAngle() : deltaTheta * i;
		Particle p = ParticleMake(Point2DZero, Vector2DMakeWithAngle(angle, 1));

		int n = 0;
		#define kDebugStringMaxLen 1000
		char debugStringBuf[kDebugStringMaxLen] = {0};

		#define d_append(...) appendToStringBuffer(debugStringBuf, \
		kDebugStringMaxLen, __VA_ARGS__)

		if (shouldUseDebug) d_append("%f,", rad2deg(angle));

		while (1) {
			// The gist of this section: exclude a circle if it's currently
			// intersecting with the particle. Get the intersection for the
			// allowed circles.
			double t1, t2, t3;
			BOOL hasSolutions1 = NO, hasSolutions2 = NO, hasSolutions3 = NO;
			BOOL exclude1 = ParticleCurrentCircleEquals(p, &rightCircle);
			BOOL exclude2 = ParticleCurrentCircleEquals(p, &leftCircle);
			BOOL exclude3 = ParticleCurrentCircleEquals(p, &topCircle);

			if (!exclude1) t1 = intersect(rightCircle, p, &hasSolutions1);
			if (!exclude2) t2 = intersect(leftCircle, p, &hasSolutions2);
			if (!exclude3) t3 = intersect(topCircle, p, &hasSolutions3);

			// If the results from the circle can't be used for any reason,
			// make damn sure they're not the lowest
			if (exclude1 || !hasSolutions1) t1 = DBL_MAX;
			if (exclude2 || !hasSolutions2) t2 = DBL_MAX;
			if (exclude3 || !hasSolutions3) t3 = DBL_MAX;

			// If no results are found, we bail out - this iteration is done.
			if (!hasSolutions1 && !hasSolutions2 && !hasSolutions3)
				break;

			double t = min3(t1, t2, t3);

			// Back find the winner
			CircleRef winner;
			if (t == t1) {
				winner = &rightCircle;
				if (shouldUseDebug) d_append(" R");
			} else if (t == t2) {
				winner = &leftCircle;
				if (shouldUseDebug) d_append(" L");
			} else { // (t == t3)
				winner = &topCircle;
				if (shouldUseDebug) d_append(" T");
			}

			Point2D oldLocation = ParticleGetPosition(p);
			Vector2D oldVelocity = ParticleGetVelocity(p);
			Point2D deltaLocation = Vector2DMultiplyScalar(oldVelocity, t);

			// Advance to our new location and velocity
			Point2D newLocation = Point2DAdd(oldLocation, deltaLocation);
			Vector2D newVelocity = reflect(*winner, newLocation, oldVelocity);

			ParticleSetCurrentCircle(&p, winner);
			ParticleSetPosition(&p, newLocation);
			ParticleSetVelocity(&p, newVelocity);

			n++;
		}

		largestValue = n > largestValue ? n : largestValue;
		values[n]++;

		// If this iteration is eligible for debugging, record the angle,
		// number of hits, and sequence of circle hits.
		if (shouldUseDebug && n >= debugMin && n >= largestValue - 10) {
			printf("%d, ", n);
			printf("%s", debugStringBuf);
			printf("\n");
		}
	}

	// Print results and tear down graph
	printf("\nResults:\n");
	printf("////////////////////////////////////////////////////////////////////////////////\n");
	for (i = 0; i < largestValue + 1; i++) {
		if (values[i]) printf("%d, %u\n", i, values[i]);
	}

	free(values);

	return 0;
}

