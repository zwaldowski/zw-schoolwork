/*******************************************************
 * CSCI 140L-1
 * Lab 15
 * Zachary Waldowski
 * Date: 4/2/12
 *
 * Employees and salaries, and all sorts of fun.
 *
 ********************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function declarations
int ask_number_of_employees(void);
int ask_employee_number(int forcePositive);
double ask_salary(void);
double ask_bonus(void);

void print_salaries_info(double *salaries, int size);
void print_bonuses_info(double *bonuses, int size);

int compareAscending(const void *aPtr, const void *bPtr);
int compareDescending(const void *bPtr, const void *aPtr);

void print_highest_salary(int *employees, double *salaries, int size);
void print_lowest_bonus(int *employees, double *bonuses, int size_t);

void print_all_salaries(double *salaries, int size, int isAscending);

int get_employee_index(int *employees, int size, int employeeNumber);

int main(void) { 
    // Variable declarations
    int numberOfEmployees = 0,
		i = 0,
	    employeeNumber = 0,
		idx;
    int *employees;
    double *salaries, *bonuses;
    
    // Retrieve number of employees
    numberOfEmployees = ask_number_of_employees();
    
    // Allocate arrays
    employees = malloc(numberOfEmployees * sizeof(int));
    salaries = malloc(numberOfEmployees * sizeof(double));
    bonuses = malloc(numberOfEmployees * sizeof(double));
    
    // Loop through employees to fill up arrays
    printf("\n");
    for (i = 0; i < numberOfEmployees; i++) {
        printf("Employee #%d:\n", i + 1);
        employees[i] = ask_employee_number(1);
        salaries[i] = ask_salary();
        bonuses[i] = ask_bonus();
        printf("\n");
    }
    
    // Print the total and average of the salaries on one line
    print_salaries_info(salaries, numberOfEmployees);
    
    // Print the total and average of the bonuses on one line
    print_bonuses_info(bonuses, numberOfEmployees);
    
    // Print the employee number and highest salary on one line.
    print_highest_salary(employees, salaries, numberOfEmployees);
    
    // Print the employee number and lowest bonus on one line.
    print_lowest_bonus(employees, bonuses, numberOfEmployees);
	
    // Print each employee number with their correct salary and bonus.
    printf("\n");
	for (i = 0; i < numberOfEmployees; i++) {
		printf("Employee #%d:\t\tSalary %9.2f | Bonus %9.2f\n", employees[i], salaries[i], bonuses[i]);
	}
	
    // Print the salaries in ascending order.
    print_all_salaries(salaries, numberOfEmployees, 1);
	
    // Print the salaries in descending order.
    print_all_salaries(salaries, numberOfEmployees, 0);
	    
    // Lookup loop - bail out if they enter a negative number
    while (1) {
        printf("\nEnter a negative number to exit.\n");
        employeeNumber = ask_employee_number(0);
        
        if (employeeNumber < 0)
            break;
		
        idx = get_employee_index(employees, numberOfEmployees, employeeNumber);
        
        if (idx < 0) {
            printf("\nNot Found\n");
            continue;
        }
        
        printf("\nEmployee #%d\n\tSalary: %9.2f\n\tBonus: %9.2f\n", employees[idx], salaries[idx], bonuses[idx]);
    }
    
    // Release arrays
    free(employees); employees = NULL;
    free(salaries); salaries = NULL;
    free(bonuses); bonuses = NULL;
	
    return 0;
}

// Asks for the number of employees.  Must be positive.
int ask_number_of_employees(void) {
    int ret = -1;
    while (ret < 0) {
        printf("Enter a number of employees. > ");
        scanf("%d", &ret);
    }
    return ret;
}

// Asks for an employee number
// If forcePositive, it has to be positive (data entry).
// If not, we take the first result (for the sentinel loop).
int ask_employee_number(int forcePositive) {
    int ret = -1;
    while (ret < 0) {
        printf("Enter an employee number. > ");
        scanf("%d", &ret);
		
		if (!forcePositive)
			break;
    }
    return ret;
}

// Asks for a salary. Must be positive.
double ask_salary(void) {
    double ret = -1;
    while (ret < 0) {
        printf("Enter their salary. > ");
        scanf("%lf", &ret);
    }
    return ret;
}

// Asks for a bonus. Must be positive.
double ask_bonus(void) {
    double ret = -1;
    while (ret < 0) {
        printf("Enter their bonus. > ");
        scanf("%lf", &ret);
    }
    return ret;
}

// Calculates total and average for salaries.
void print_salaries_info(double *salaries, int size) {
    int i;
    double total = 0.0, average = 0.0;
    for (i = 0; i < size; i++) {
        total += salaries[i];
    }
    average = total / (double)size;
    printf("$%.2f paid out in salaries for an average of $%.2f.\n", total, average);
}

// Calculates total and average for bonuses.
void print_bonuses_info(double *bonuses, int size) {
    int i;
    double total = 0.0, average = 0.0;
    for (i = 0; i < size; i++) {
        total += bonuses[i];
    }
    average = total / (double)size;
    printf("$%.2f paid out in bonuses for an average of $%.2f.\n", total, average);
}

// double-based descending comparator for qsort
int compareDescending(const void *aPtr, const void *bPtr) {
    const double *a = (double *)aPtr;
    const double *b = (double *)bPtr;
    return (int)(*b - *a);
}

// double-based ascending comparator for qsort
int compareAscending(const void *aPtr, const void *bPtr) {
    const double *a = (double *)aPtr;
    const double *b = (double *)bPtr;
    return (int)(*a - *b);
}

// Print out the highest salary, by sorting and picking the first.
void print_highest_salary(int *employees, double *salaries, int size) {
    double highestSalary = 0.0;
    int i = 0, index = 0;
	double *sorted = NULL;
	
	// Create a sorted array
	sorted = calloc(size, sizeof(double));
	memcpy(sorted, salaries, size * sizeof(double));
	qsort(sorted, size, sizeof(double), compareDescending);
    
    // get the highest one
    highestSalary = sorted[0];
    
    // free our sorted array
    free(sorted); sorted = NULL;
    
    // find it in the original
    for (i = 0; i < size; i++) {
        if (salaries[i] == highestSalary) {
            index = i;
            break;
        }
    }
    
    printf("Employee #%d has the highest salary of %.2f.\n", employees[index], salaries[index]);
}

// Print out the lowest salary, by sorting and picking the first.
void print_lowest_bonus(int *employees, double *bonuses, int size) {
    double lowestBonus = 0.0;
    int i = 0, index = 0;
    double *sorted = NULL;
	
	// Create a sorted array
	sorted = calloc(size, sizeof(double));
	memcpy(sorted, bonuses, size * sizeof(double));
	qsort(sorted, size, sizeof(double), compareAscending);
	
    // get the lowest one
    lowestBonus = sorted[0];
    
    // free our sorted array
    free(sorted); sorted = NULL;
    
    // find it in the original
    for (i = 0; i < size; i++) {
        if (bonuses[i] == lowestBonus) {
            index = i;
            break;
        }
    }
    
    printf("Employee #%d has the lowest bonus of %.2f.\n", employees[index], bonuses[index]);
}

// Print out all salaries in order using a specific sorting type
void print_all_salaries(double *salaries, int size, int isAscending) {
	int i;
	double *sorted = NULL;
	
	// Create a properly sorted array
	sorted = calloc(size, sizeof(double));
	memcpy(sorted, salaries, size * sizeof(double));
	
	// Print a header, and sort things
	printf("\nSalaries");
	if (isAscending == 1) {
		qsort(sorted, size, sizeof(double), compareAscending);
		printf(" High-to-Low");
	} else {
		qsort(sorted, size, sizeof(double), compareDescending);
		printf(" Low-to-High");
	}
	printf(":\n");
	
	// Print the whole list
	for (i = 0; i < size; i++) {
		printf("\t%9.2f\n", sorted[i]);
	}
}

// Gets the index for an entered employee ID
int get_employee_index(int *employees, int size, int employeeNumber) {
	int i, ret = -1;
	
	// Loop through the employees list until we find it
	for (i = 0; i < size; i++) {
		if (employees[i] == employeeNumber) {
			ret = i;
			break;
		}
	}
	
	return ret;
}