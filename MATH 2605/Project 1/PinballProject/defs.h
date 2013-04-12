//
//  Definitions
//  Pinball Project
//  Project 1
//
//  Zachary Waldowski - gtID 902897777.
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#ifndef PinballProject_Defs
#define PinballProject_Defs

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

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

typedef struct _Point2D {
	double x;
	double y;
} Point2D;

extern Point2D const Point2DZero;

typedef struct _Vector2D {
	Point2D direction; // always unit
	double magnitude;
} Vector2D;

extern Vector2D const Vector2DZero;

typedef struct _Circle2D {
	Point2D center;
	double radius;
} Circle;
typedef Circle *CircleRef;

typedef struct _Particle2D {
	Point2D position;
	Vector2D velocity;
	CircleRef currentCircle;
} Particle;
typedef Particle *ParticleRef;

#endif
