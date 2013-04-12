//
//  Geometry (Header)
//  Pinball Project
//  Project 1
//
//  Zachary Waldowski - gtID 902897777.
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#include "defs.h"

#ifndef PinballProject_Geom
#define PinballProject_Geom

// Point operations

extern Point2D Point2DMake(double x, double y);

extern double Point2DGetX(Point2D p);
extern double Point2DGetY(Point2D p);

extern Point2D Point2DAdd(Point2D a, Point2D b);
extern Point2D Point2DSubtract(Point2D a, Point2D b);
extern Point2D Point2DMultiplyScalar(Point2D a, double b);
extern Point2D Point2DDivideScalar(Point2D a, double b);

extern double Point2DDotProduct(Point2D v1, Point2D v2);

// Vector operations

extern Vector2D Vector2DMake(double x, double y);
extern Vector2D Vector2DMakeWithAngle(double radians, double magnitude);

extern double Vector2DGetMagnitude(Vector2D v);
extern Point2D Vector2DGetDirection(Vector2D v);

extern double Vector2DGetX(Vector2D v);
extern double Vector2DGetY(Vector2D v);
extern Vector2D Vector2DGetUnitVector(Vector2D v);

extern double Vector2DDotProduct(Vector2D v1, Vector2D v2);
extern double Vector2DDotProductPoint(Vector2D v1, Point2D p1);

extern Vector2D Vector2DAdd(Vector2D a, Vector2D b);
extern Vector2D Vector2DSubtract(Vector2D a, Vector2D b);

extern Vector2D Vector2DAddPoint(Vector2D a, Point2D b);
extern Vector2D Vector2DSubtractPoint(Vector2D a, Point2D b);

extern Point2D Vector2DMultiplyScalar(Vector2D a, double b);
extern Point2D Vector2DDivideScalar(Vector2D a, double b);

// Circle operations

extern Circle CircleMake(Point2D center, double radius);

extern Point2D CircleGetCenter(Circle c);
extern double CircleGetRadius(Circle c);

// Particle operations

extern Particle ParticleMake(Point2D location, Vector2D velocity);
extern Point2D ParticleGetPosition(Particle p);
extern Vector2D ParticleGetVelocity(Particle p);
extern CircleRef ParticleGetCurrentCircle(Particle p);

extern void ParticleSetPosition(ParticleRef p, Point2D position);
extern void ParticleSetVelocity(ParticleRef p, Vector2D velocity);
extern void ParticleSetCurrentCircle(ParticleRef p, CircleRef circle);

extern BOOL ParticleCurrentCircleEquals(Particle p, CircleRef circle);

#endif
