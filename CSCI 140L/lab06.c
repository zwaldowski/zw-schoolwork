/*******************************************************
* CSCI 140L-1
* Lab 6
* Zachary Waldowski
* Date: 2/13/12
*
* Takes in a users' weight and height, calculates BMI.
*
********************************************************/

#include <stdio.h>
#include <math.h>

// BMI type
typedef enum {
    Underweight,
    Normal,
    Overweight,
    Obese
} WeightStatus;

// Function forward declarations
void description(void);
double get_weight(void);
double get_height(void);
int validate_weight(double weight);
int validate_height(double height);
double get_bmi(double weight, double height);
void print_bmi(double bmi);
WeightStatus get_weight_status(double bmi);
void print_status(WeightStatus status);

int main(void) {
    // Declare variables
    double weight = 0.0,
           height = 0.0,
           bmi = 0.0;
    WeightStatus status = 0;
    
    // Ask for and save the value for weight.
    weight = get_weight();
    
    // Ask for and save the value for height.
    height = get_height();
    
    // Bail out if we have an invalid weight.
    if (validate_weight(weight) == 0) {
        printf("\n!!! Invalid weight. Exiting...\n");
        return 0;
    }
    
    // Bail out if we have an invalid height.
    if (validate_height(height) == 0) {
        printf("\n!!! Invalid height. Exiting...\n");
        return 0;
    }
    
    // Calculate and print the BMI.
    bmi = get_bmi(weight, height);
    print_bmi(bmi);
    
    // Decide and print the weight status.
    status = get_weight_status(bmi);
    print_status(status);
	
	return 0;
}

// Prints a description of the program.
void description(void) {
    printf("//////////////////////////////////////////////////////////\n");
	printf(" This program will ask for a height and weight, then      \n");
	printf(" returns the body mass index (BMI) for those values.      \n");
    printf("//////////////////////////////////////////////////////////\n");
}

// Prints a prompt, records, and returns inputted weight.
double get_weight(void) {
    double weight = 0.0;
    printf("Enter a weight in pounds.\t> ");
    scanf("%lf", &weight);
    return weight;
}

// Prints a prompt, records, and returns inputted height.
double get_height(void) {
    double height = 0.0;
    printf("Enter a height in inches.\t> ");
    scanf("%lf", &height);
    return height;
}

// Validates a weight. Returns 1 if valid, 0 if not.
int validate_weight(double weight) {
    if (weight < 400)
        return 1;
    else
        return 0;
}

// Validates a height. Returns 1 if valid, 0 if not.
int validate_height(double height) {
    if (height < 84.0)
        return 1;
    else
        return 0;
}

// Calculate the BMI:  (703 * wt_lb) / (ht_in ^ 2)
double get_bmi(double weight, double height) {
    double numerator = 703 * weight;
    double denominator = pow(height, 2);
    return numerator / denominator;
}

// Print out a message regarding the BMI.
void print_bmi(double bmi) {
    printf("\nThe body mass index for that height and weight is: %.2f", bmi);
}

// Decide on a weight status.
WeightStatus get_weight_status(double bmi) {
    if (bmi < 18.5)
        return Underweight;
    else if (bmi < 25)
        return Normal;
    else if (bmi < 30)
        return Overweight;
    else
        return Obese;
}

// Print out a message regarding the weight status.
void print_status(WeightStatus status) {
    printf("\nThe Centers for Disease Control would rate this BMI: \n\t- ");
    if (status == Underweight) {
        printf("Underweight");
    } else if (status == Normal) {
        printf("Normal");
    } else if (status == Overweight) {
        printf("Overweight");
    } else {
        printf("Obese");
    }
    printf("\n");
}