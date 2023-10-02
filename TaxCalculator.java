import java.util.Scanner;

public class TaxCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter your income: ");
            double income = scanner.nextDouble();

            System.out.println("Enter deduction (For old regime only): ");
            double standardDeduction = scanner.nextDouble();

            double oldTax = computeOldRegimeTax(income, standardDeduction);
            double newTax = computeNewRegimeTax(income);

            System.out.println("Tax under old regime: " + oldTax);
            System.out.println("Tax under new regime: " + newTax);

            double pro_newTax = oldTax-newTax;
            double pro_oldTax = newTax-oldTax;
            
            if(oldTax > newTax) {
                System.out.println(String.format("You should opt for New Regime as it will provide you benefit of Rs. %.2f over Old Regime", pro_newTax));
            } else {
                System.out.println(String.format("You should opt for Old Regime as it will provide you benefit of Rs. %.2f over New Regime", pro_oldTax));
            }

            System.out.println("Do you want to compute for another income? (yes/no)");
            String response = scanner.next();
            
            if (response.equalsIgnoreCase("no")) {
                break;  // This will exit the loop
            }
        }
    }

    public static double computeOldRegimeTax(double income, double standardDeduction) {
        double tax = 0;
        double deductibleIncome = income - standardDeduction;

        if (deductibleIncome <= 250000) {
            tax = 0;
        } else if (deductibleIncome <= 500000) {
            tax = (deductibleIncome - 250000) * 0.10;
        } else if (deductibleIncome <= 1000000) {
            tax = (500000 - 250000) * 0.10 + (deductibleIncome - 500000) * 0.20;
        } else {
            tax = (500000 - 250000) * 0.10 + (1000000 - 500000) * 0.20 + (deductibleIncome - 1000000) * 0.30;
        }

        return tax;
    }

    public static double computeNewRegimeTax(double income) {
        double tax = 0;

        if (income <= 500000) {
            tax = income * 0.05;
        } else if (income <= 1000000) {
            tax = 500000 * 0.05 + (income - 500000) * 0.10;
        } else {
            tax = 500000 * 0.05 + (1000000 - 500000) * 0.10 + (income - 1000000) * 0.15;
        }

        return tax;
    }
}
