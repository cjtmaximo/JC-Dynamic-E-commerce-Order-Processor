package org.example;

import java.util.Scanner;

public class InteractiveOrderProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Interactive Order Processor!\n");

        // Get Order Data from User
        System.out.println("--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Is customer a member (true/false)?: ");
        String isMember = scanner.nextLine();

        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        String customerTier = scanner.nextLine();

        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        String shippingZone = scanner.nextLine();

        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
        String discountCode = scanner.nextLine();
        boolean isFreeShipApplied = false;

        System.out.println("\n--- Order Details ---");
        System.out.printf("Unit Price: $%.2f\n", unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);
        System.out.println();

        System.out.println("--- Calculation Details ---");
        // Subtotal Calculation
        double subTotal = unitPrice * quantity;
        System.out.printf("Initial Subtotal: $%.2f\n", subTotal);

        // Tier-Based Discount
        String tierDiscount = "Regular - 0%";
        if (customerTier.equals("Gold")) {
            subTotal = subTotal * 0.85; // 15% discount
            tierDiscount = "Gold - 15%";
        } else if (customerTier.equals("Silver")) {
            subTotal = subTotal * 0.90; // 10% discount
            tierDiscount = "Silver - 10%";
        } else {
            subTotal = subTotal * 1; // No discount
        }
        System.out.printf("After Tier Discount (%s): $%.2f\n", tierDiscount, subTotal);

        // Quantity Discount
        if (quantity >= 5) {
            subTotal = subTotal * 0.95; // 5% discount
        }
        System.out.printf("After Quantity Discount (5%% for >= 5 items): $%.2f\n", subTotal);

        // Promotional Code Application
        if (discountCode.equals("SAVE10") && subTotal > 75.00) { // Case-sensitive
            subTotal = subTotal - 10.00;
            System.out.printf("After Promotional Code (SAVE10 for >$75): $%.2f\n", subTotal);
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            isFreeShipApplied = true; // Shipping cost is 0.00
        }

        // Small Order Surcharge
        double surcharge = subTotal < 25.00 ? 3.00: 0.00;
        double totalBeforeShipping = subTotal + surcharge;
        System.out.printf("After Small Order Surcharge (if applicable): $%.2f %s\n", totalBeforeShipping, surcharge > 0 ? "" : "(No surcharge)");

        // Shipping Cost Calculation
        double shippingCost = 0.0;
        if (!isFreeShipApplied) {
            switch (shippingZone) {
                case "ZoneA":
                    shippingCost = 5.00;
                    break;
                case "ZoneB":
                    shippingCost = 12.50;
                    break;
                case "ZoneC":
                    shippingCost = 20.00;
                    break;
                default:
                    shippingCost = 25.00;
                    break;
            }
        }
        System.out.println();
        System.out.printf("Shipping cost: $%.2f (%s)\n", shippingCost, shippingZone);

        // Final Total
        double finalOrderTotal = totalBeforeShipping + shippingCost;
        System.out.println();
        System.out.printf("Final Order Total: $%.2f\n", finalOrderTotal);
        System.out.println();

        // Interactive String Equality
        // Get Strings from User
        System.out.println("--- String Equality Demo ---");
        System.out.print("Enter first string for comparison: ");
        String firstString = scanner.nextLine();

        System.out.print("Enter second string for comparison: ");
        String secondString = scanner.nextLine();

        System.out.println();
        System.out.println("String 1: \"" + firstString + "\"" );
        System.out.println("String 2: \"" + secondString + "\"" );
        System.out.println();

        // String Equality Demonstrations
        boolean useEqualsOperator = (firstString == secondString);
        boolean useEqualsMethod = (firstString.equals(secondString));
        boolean useEqualsIgnoreCaseMethod = (firstString.equalsIgnoreCase(secondString));

        System.out.println("String 1 == String 2: " + useEqualsOperator);
        System.out.println("String 1 .equals() String 2: " + useEqualsMethod);
        System.out.println("String 1 .equalsIgnoreCase() String 2: " + useEqualsIgnoreCaseMethod);

        scanner.close();
    }
}
