# Inventory Price Report

A Java program that reads product data from a text file, validates each
entry, and generates a summary report — including totals, averages, and
the highest/lowest priced items.

## What It Does

The program reads `inventory.txt` line by line, where each line is expected
to follow the format `product name,price`. It separates valid entries from
invalid ones, reporting *why* each invalid line was skipped, then prints a
final report summarizing the valid data.

## Key Concepts Demonstrated

- **File I/O** — reads an external file using `BufferedReader` and
  `FileReader`.
- **Exception handling** — uses try/catch/finally to handle missing files,
  malformed lines, and invalid number formats without crashing.
- **Input validation** — checks for blank lines, incorrect formatting,
  missing fields, negative prices, and non-numeric values.
- **Data aggregation** — tracks running totals, averages, and the
  highest/lowest priced items while processing the file.

## Files

| File | Purpose |
|---|---|
| `InventoryPriceReport.java` | Main program — reads, validates, and reports on inventory data |
| `inventory.txt` | Sample data file, including intentionally invalid lines to demonstrate error handling |

## How to Run

```bash
javac InventoryPriceReport.java
java InventoryPriceReport
```

Make sure `inventory.txt` is in the same directory as the compiled program.

## Sample Output

```
Error on line 7: "Webcam,-15.00" - price cannot be negative. Skipping.
Error on line 8: "Desk Lamp,abc" - price "abc" is not a valid number. Skipping.
Error on line 9: "Headphones,59.99,extra" - missing data or extra comma. Skipping.
Error on line 10: ",29.99" - missing product name or price. Skipping.
File reading complete.

========== INVENTORY PRICE REPORT ==========
Valid products processed : 6
Invalid lines skipped     : 4
Total inventory price     : $1298.47
Average price              : $216.41
Highest priced item        : Laptop ($899.99)
Lowest priced item         : USB-C Cable ($9.99)
=============================================
```
