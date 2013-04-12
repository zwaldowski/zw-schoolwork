//
//  Geometry
//  Pinball Project
//  Project 1
//
//  Zachary Waldowski - gtID 902897777.
//  MATH 2605 - Spring 2013 - Georgia Tech
//

#include "geom.h"
#include <stdio.h>
#include <math.h>

// Constants

Point2D const Point2DZero = { .x = 0, .y = 0 };
Vector2D const Vector2DZero = { .magnitude = 0, .direction = { 0, 0 } };

// Point operations

inline Point2D Point2DMake(double x, double y) {
	Point2D pt = { .x = x, .y = y };
	return pt;
}

inline double Point2DGetX(Point2D p) {
	return p.x;
}

inline double Point2DGetY(Point2D p) {
	return p.y;
}

Point2D Point2DAdd(Point2D a, Point2D b) {
	double x = Point2DGetX(a) + Point2DGetX(b);
	double y = Point2DGetY(a) + Point2DGetY(b);
	return Point2DMake(x, y);
}

Point2D Point2DSubtract(Point2D a, Point2D b) {
	double x = Point2DGetX(a) - Point2DGetX(b);
	double y = Point2DGetY(a) - Point2DGetY(b);
	return Point2DMake(x, y);
}

Point2D Point2DMultiplyScalar(Point2D a, double b) {
	return Point2DMake(Point2DGetX(a) * b, Point2DGetY(a) * b);
}

Point2D Point2DDivideScalar(Point2D a, double b) {
	return Point2DMake(Point2DGetX(a) / b, Point2DGetY(a) / b);
}

double Point2DDotProduct(Point2D p1, Point2D p2) {
	double a = Point2DGetX(p1) * Point2DGetX(p2);
	double b = Point2DGetY(p1) * Point2DGetY(p2);
	return a + b;
}

// Vector operations

inline Vector2D Vector2DMake(double x, double y) {
	double magnitude = sqrt(pow(x, 2) + pow(y, 2));
	Point2D direction = Point2DMake(x / magnitude, y / magnitude);
	Vector2D vc = { .magnitude = magnitude, .direction = direction };
	return vc;
}

Vector2D Vector2DMakeWithAngle(double radians, double magnitude) {
	Point2D direction = Point2DMake(cos(radians), sin(radians));
	Vector2D vc = { .magnitude = magnitude, .direction = direction };
	return vc;
}

inline double Vector2DGetMagnitude(Vector2D v) {
	return v.magnitude;
}

inline Point2D Vector2DGetDirection(Vector2D v) {
	return v.direction;
}

inline double Vector2DGetX(Vector2D v) {
	return Point2DGetX(Vector2DGetDirection(v)) * Vector2DGetMagnitude(v);
}

inline double Vector2DGetY(Vector2D v) {
	return Point2DGetY(Vector2DGetDirection(v)) * Vector2DGetMagnitude(v);
}

Vector2D Vector2DGetUnitVector(Vector2D v) {
	if (Vector2DGetMagnitude(v) == 1) return v;
	Vector2D vc = { .magnitude = 1, .direction = Vector2DGetDirection(v) };
	return vc;
}

double Vector2DDotProduct(Vector2D v1, Vector2D v2) {
	double a = Vector2DGetX(v1) * Vector2DGetX(v2);
	double b = Vector2DGetY(v1) * Vector2DGetY(v2);
	return a + b;
}

double Vector2DDotProductPoint(Vector2D v1, Point2D p1) {
	double a = Vector2DGetX(v1) * Point2DGetX(p1);
	double b = Vector2DGetY(v1) * Point2DGetY(p1);
	return a + b;
}

Vector2D Vector2DAdd(Vector2D a, Vector2D b) {
	double x = Vector2DGetX(a) + Vector2DGetX(b);
	double y = Vector2DGetY(a) + Vector2DGetY(b);
	return Vector2DMake(x, y);
}

Vector2D Vector2DSubtract(Vector2D a, Vector2D b) {
	double x = Vector2DGetX(a) - Vector2DGetX(b);
	double y = Vector2DGetY(a) - Vector2DGetY(b);
	return Vector2DMake(x, y);
}

Vector2D Vector2DAddPoint(Vector2D a, Point2D b) {
	double x = Vector2DGetX(a) + Point2DGetX(b);
	double y = Vector2DGetY(a) + Point2DGetY(b);
	return Vector2DMake(x, y);
}

Vector2D Vector2DSubtractPoint(Vector2D a, Point2D b) {
	double x = Vector2DGetX(a) - Point2DGetX(b);
	double y = Vector2DGetY(a) - Point2DGetY(b);
	return Vector2DMake(x, y);
}

Point2D Vector2DMultiplyScalar(Vector2D a, double b) {
	return Point2DMake(Vector2DGetX(a) * b, Vector2DGetY(a) * b);
}

Point2D Vector2DDivideScalar(Vector2D a, double b) {
	return Point2DMake(Vector2DGetX(a) / b, Vector2DGetY(a) / b);
}

// Circle operations

inline Circle CircleMake(Point2D center, double radius) {
	Circle cr = { .center = center, .radius = radius };
	return cr;
}

extern inline Point2D CircleGetCenter(Circle c) {
	return c.center;
}

extern inline double CircleGetRadius(Circle c) {
	return c.radius;
}

// Particle operations

inline Particle ParticleMake(Point2D location, Vector2D velocity) {
	Particle p = {
		.position = location,
		.velocity = velocity,
		.currentCircle = NULL
	};
	return p;
}

Point2D ParticleGetPosition(Particle p) {
	return p.position;
}

Vector2D ParticleGetVelocity(Particle p) {
	return p.velocity;
}

void ParticleSetCurrentCircle(ParticleRef p, CircleRef circle) {
	p->currentCircle = circle;
}

void ParticleSetPosition(ParticleRef p, Point2D position) {
	p->position = position;
}

void ParticleSetVelocity(ParticleRef p, Vector2D velocity) {
	p->velocity = velocity;
}

CircleRef ParticleGetCurrentCircle(Particle p) {
	return p.currentCircle;
}

BOOL ParticleCurrentCircleEquals(Particle p, CircleRef c1) {
	return (p.currentCircle == c1);
}
