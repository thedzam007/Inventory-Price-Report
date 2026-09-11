import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InventoryPriceReport {

    public static void main(String[] args) {

        String fileName = "inventory.txt";

        BufferedReader reader = null;

        // Variables used to build the report
        int validCount = 0;
        int invalidCount = 0;
        double totalPrice = 0.0;

        String highestProduct = null;
        double highestPrice = Double.NEGATIVE_INFINITY;

        String lowestProduct = null;
        double lowestPrice = Double.POSITIVE_INFINITY;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNumber = 0;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // Skip completely blank lines silently
                if (line.trim().isEmpty()) {
                    continue;
                }

                // A valid line must split into exactly two parts on the comma
                String[] parts = line.split(",");

                if (parts.length != 2) {
                    System.out.println("Error on line " + lineNumber + ": \"" + line
                            + "\" - missing data or extra comma. Skipping.");
                    invalidCount++;
                    continue;
                }

                String productName = parts[0].trim();
                String priceText = parts[1].trim();

                if (productName.isEmpty() || priceText.isEmpty()) {
                    System.out.println("Error on line " + lineNumber + ": \"" + line
                            + "\" - missing product name or price. Skipping.");
                    invalidCount++;
                    continue;
                }

                try {
                    double price = Double.parseDouble(priceText);

                    if (price < 0) {
                        System.out.println("Error on line " + lineNumber + ": \"" + line
                                + "\" - price cannot be negative. Skipping.");
                        invalidCount++;
                        continue;
                    }

                    // Line is valid - update running totals
                    validCount++;
                    totalPrice += price;

                    if (price > highestPrice) {
                        highestPrice = price;
                        highestProduct = productName;
                    }

                    if (price < lowestPrice) {
                        lowestPrice = price;
                        lowestProduct = productName;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error on line " + lineNumber + ": \"" + line
                            + "\" - price \"" + priceText + "\" is not a valid number. Skipping.");
                    invalidCount++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error: Could not read file \"" + fileName + "\". "
                    + e.getMessage());
        } finally {
            // Always attempt to close the reader, even if an exception occurred
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error: Could not close the file properly.");
                }
            }
            System.out.println("File reading complete.\n");
        }

        // ----- Print the final report -----
        System.out.println("========== INVENTORY PRICE REPORT ==========");
        System.out.println("Valid products processed : " + validCount);
        System.out.println("Invalid lines skipped     : " + invalidCount);

        if (validCount > 0) {
            double average = totalPrice / validCount;
            System.out.printf("Total inventory price     : $%.2f%n", totalPrice);
            System.out.printf("Average price              : $%.2f%n", average);
            System.out.println("Highest priced item        : " + highestProduct
                    + String.format(" ($%.2f)", highestPrice));
            System.out.println("Lowest priced item         : " + lowestProduct
                    + String.format(" ($%.2f)", lowestPrice));
        } else {
            System.out.println("No valid product data found in the file.");
        }
        System.out.println("=============================================");
    }
}