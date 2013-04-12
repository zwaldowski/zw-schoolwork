/*******************************************************
* CSCI 140L-1
* Lab 3
* Zachary Waldowski
* Date: 1/25/12
*
* Asks for the name, graduation year, desired income,
* and retirement age and prints them, all using functions.
*
********************************************************/

#include <stdio.h>

int get_age(void);
int get_graduation_year(void);
double get_desired_income(void);
int get_retire_age(void);
void print_header(int age, int graduationYear, double desiredIncome, int retirementAge);

int main(void) {
	int age = 0,
		graduationYear = 0,
		retirementAge = 0;
	double desiredIncome = 0.0;
	
	// Get their age and store it to the variable.
	age = get_age();
	
	// Get their graduation year and store it.
	graduationYear = get_graduation_year();
	
	// Get their desired income and store it.
	desiredIncome = get_desired_income();
	
	// Get their retirement age and store it.
	retirementAge = get_retire_age();
	
	print_header(age, graduationYear, desiredIncome, retirementAge);

    return 0;
}

// Declare the return value, print a prompt, scan to it, and return (times four).
int get_age(void) {
	int age = 0;
	printf("Enter your age:\t\t\t\t\t\t> ");
	scanf("%d", &age);
	return age;
}

int get_graduation_year(void) {
	int year = 0;
	printf("Enter your graduation year:\t\t\t> ");
	scanf("%d", &year);
	return year;
}

double get_desired_income(void) {
	double income = 0;
	printf("Enter your desired yearly income:\t> ");
	scanf("%lf", &income);
	return income;
}

int get_retire_age(void) {
	int retireAge = 0;
	printf("Enter your desired retirement age:\t> ");
	scanf("%d", &retireAge);
	return retireAge;
}

// Print the given values.
void print_header(int age, int graduationYear, double desiredIncome, int retirementAge) {
	printf("\n\t---\n\n");
	printf("I am currently %d years old.\n", age);
	printf("I graduate in %d.\n", graduationYear);
	printf("I want to make at least $%.2f a year so I can retire when I am %d.", desiredIncome, retirementAge);
}

