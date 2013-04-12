/*******************************************************
* CSCI 140L-1
* Lab 4
* Zachary Waldowski
* Date: 1/30/12
*
* Asks the user for information about an employee and
* then displays vital financial information on that user.
*
********************************************************/

#include <stdio.h>

// Function forward declarations
void print_description(void);
double get_yearly_salary(void);
int get_years_with_company(void);
double get_bonus(int yearsWithCompany, double yearlySalary);
double get_benefits_cost(double yearlySalary);
double get_total_cost(double yearlySalary, double bonus, double benefitsCost);
void print_results(double bonus, double benefitsCost, double totalCost);

int main(void) {
	// Declare variables
	double yearlySalary = 0.0,
	       bonus = 0.0,
	       benefitsCost = 0.0,
	       totalCost = 0.0;
	int yearsWithCompany = 0;
	
	// Print the description
	print_description();
	
	// Get values that need to be asked from the user.
	yearlySalary = get_yearly_salary();
	yearsWithCompany = get_years_with_company();
	
	// Calculate the relevant values we want to return.
	bonus = get_bonus(yearsWithCompany, yearlySalary);
	benefitsCost = get_benefits_cost(yearlySalary);
	totalCost = get_total_cost(yearlySalary, bonus, benefitsCost);
	
	// Print out results.
	print_results(bonus, benefitsCost, totalCost);
	
	return 0;
}

// Prints a description of the program.
void print_description(void) {
	printf("//////////////////////////////////////////////////////////\n");
	printf(" This program will ask basic information for an employee \n and prints out important financial information for them.\n");
	printf("//////////////////////////////////////////////////////////\n\n");
}

// Asks for and returns the yearly salary for an employee.
double get_yearly_salary(void) {
	double salary = 0.0;
	printf("Enter the yearly salary for an employee.  > $");
    scanf("%lf", &salary);
	return salary;
}

// Asks for and returns the years an employee has been with the company.
int get_years_with_company(void) {
	int yearsWithCompany = 0;
	printf("How long have they been employed?         > ");
    scanf("%d", &yearsWithCompany);
	return yearsWithCompany;
}

// Returns the bonus of the employee (years with the company * .5 / 100 * salary)
double get_bonus(int yearsWithCompany, double yearlySalary) {
	return yearsWithCompany * (0.5 / 100) * yearlySalary;
}

// Returns the cost of benefits for the employee (salary * 35%)
double get_benefits_cost(double yearlySalary) {
	return yearlySalary * 0.35;
}

// Returns the total cost of the employee by summing the given 3 numbers.
double get_total_cost(double yearlySalary, double bonus, double benefitsCost) {
	return yearlySalary + bonus + benefitsCost;
}

// Formats and prints the given values.
void print_results(double bonus, double benefitsCost, double totalCost) {
	printf("\n");
	printf("Their bonus for this year is:\t\t%12.2f\n", bonus);
	printf("The cost of their benefits is:\t\t%12.2f\n", benefitsCost);
	printf("The total cost of the employee is:\t%12.2f\n", totalCost);
}