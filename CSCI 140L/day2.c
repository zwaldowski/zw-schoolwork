//
// Converts distance in miles to kilometers.
//
 #include <stdio.h>             // printf, scanf definitions
 #define KMS_PER_MILE 1.609     // conversion constant

 int main(void) {
     double miles = 0,  // input - distance in miles.
            kms = 0;    // output - distance in kilometers.

    // Get the distance in miles.
    printf("Enter the distance in miles. > ");
    scanf("%lf", &miles);

    // Convert the distance to kilometers.
    kms = KMS_PER_MILE * miles;

    // Display the distance in kilometers.
    printf("That equals %f kilometers.\n", kms);

    return 0;
 }
