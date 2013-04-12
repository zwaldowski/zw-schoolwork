/*******************************************************
* CSCI 140L-1
* Lab 8
* Zachary Waldowski
* Date: 2/20/12
*
* Deciding on an account type using different forms of
* logic.
*
********************************************************/

#include <stdio.h>

// Function declarations
void description(void);
int get_logic_type(void);
int get_account_type(void);

float get_minimum_deposit(int logic_type, int account_type);
int get_interest_rate(int logic_type, int account_type);
void print_account(int logic_type, int account_type);

double get_initial_deposit(int logic_type, int account_type);
double get_interest(int logic_type, int account_type, double initial_deposit);
void print_interest(double interest);

int main(void) {
    // Declare variables
    int logic_type = 0,
        account_type = 0;
    double initial_deposit = 0.0,
           one_year_interest = 0.0;
    
    // Print the description
    description();
    
    // Ask for and store the preferred logic type.
    logic_type = get_logic_type();
    
    // Ask for and store their account type.
    account_type = get_account_type();
    
    // Print the account type information using
    // the preferred logic type.
    print_account(logic_type, account_type);
        
    // Bonus.
    // Ask for and store an initial deposit.
    initial_deposit = get_initial_deposit(logic_type, account_type);
    
    // Calculate interest after one year.
    one_year_interest = get_interest(logic_type, account_type, initial_deposit);
    
    // Print out that information.
    print_interest(one_year_interest);
    
	return 0;
}

// Prints a description of the program.
void description(void) {
    printf("//////////////////////////////////////////////////////////\n");
    printf(" This program allow you to choose an account type.        \n");
    printf("//////////////////////////////////////////////////////////\n\n");
}

// Asks for and returns "1" or "2".
int get_logic_type(void) {
    int selection = 0;
    do {
        printf("Select a logic type:\n");
        printf("\t1 - if\n");
        printf("\t2 - switch\n");
        printf(" > ");
        scanf("%d", &selection);
        
        if (selection == 1 || selection == 2)
            break;
    } while (1);
    printf("\n");
    return selection;
}

int get_account_type(void) {
    int selection = 0;
    do {
        printf("Select an account type:\n");
        printf("\t - 1\n");
        printf("\t - 2\n");
        printf("\t - 3\n");
        printf("\t - 4\n");
        printf(" > ");
        scanf("%d", &selection);
        
        if (selection && selection <= 4)
            break;
    } while (1);
    printf("\n");
    return selection;
}

float get_minimum_deposit(int logic_type, int account_type) {
    double deposit;
    if (logic_type == 1) {
        if (account_type == 1) {
            deposit = 1.00;
        } else if (account_type == 2) {
            deposit = 100.00;
        } else if (account_type == 3) {
            deposit = 1000.00;
        } else if (account_type == 4) {
            deposit = 10000.00;
        } else {
            deposit = 0.0;
        }
    } else {
        switch (account_type) {
            case 1:
                deposit = 1.00;
                break;
            case 2:
                deposit = 100.00;
                break;
            case 3:
                deposit = 1000.00;
                break;
            case 4:
                deposit = 10000.00;
                break;
            default:
                deposit = 0.0;
                break;
        }
    }
    return deposit;
}

int get_interest_rate(int logic_type, int account_type) {
    int rate;
    if (logic_type == 1) {
        if (account_type == 1) {
            rate = 3;
        } else if (account_type == 2) {
            rate = 4;
        } else if (account_type == 3) {
            rate = 5;
        } else if (account_type == 4) {
            rate = 6;
        } else {
            rate = 0;
        }
    } else {
        switch (account_type) {
            case 1:
                rate = 3;
                break;
            case 2:
                rate = 4;
                break;
            case 3:
                rate = 5;
                break;
            case 4:
                rate = 6;
                break;
            default:
                rate = 0;
                break;
        }
    }
    return rate;
}

void print_account(int logic_type, int account_type) {
    int rate = get_interest_rate(logic_type, account_type);
    float deposit = get_minimum_deposit(logic_type, account_type);
    printf("You will earn %d%% a year with an initial deposit of $%.2f.\n\n", rate, deposit);
}

double get_initial_deposit(int logic_type, int account_type) {
    double minimum = get_minimum_deposit(logic_type, account_type);
    double initial = 0;
    do {
        printf("Enter an initial deposit. > ");
        scanf("%lf", &initial);
        
        if (initial && initial >= minimum)
            break;
            
        printf("Initial deposit too low for account type.\n");
    } while (1);
    printf("\n");
    return initial;
}

double get_interest(int logic_type, int account_type, double initial_deposit) {
    int rate = get_interest_rate(logic_type, account_type);
    return initial_deposit * rate / 100;
}

void print_interest(double interest) {
    printf("In one year, you will earn %.2f in interest.\n", interest);
}