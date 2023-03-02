package org.example.hw19;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Product {
    private static final String BOOK_CATEGORY = "Book";
    private static final double DISCOUNTED_PRICE_FACTOR = 0.9;

    private final String type;
    private final double price;
    private final boolean discount;
    private final LocalDate addedDate;

    public Product(String type, double price, boolean discount) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.addedDate = LocalDate.now();
    }

    public Product(String type, double price, boolean discount, LocalDate addedDate) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.addedDate = addedDate;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean getDiscount() {
        return discount;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public static List<Product> getExpensiveBooks(final List<Product> productList, final double priceThreshold) {
        return productList.stream()
                .filter(product -> product.getType().equals(BOOK_CATEGORY))
                .filter(product -> product.getPrice() > priceThreshold)
                .collect(Collectors.toList());
    }

    public static List<Product> getDiscountedBooks(final List<Product> productList) {
        return productList.stream()
                .filter(product -> product.getType().equals(BOOK_CATEGORY))
                .filter(Product::getDiscount)
                .map(product -> new Product(product.getType(), product.getPrice() * DISCOUNTED_PRICE_FACTOR, false))
                .collect(Collectors.toList());
    }

    public static Product getCheapestBook(final List<Product> productList) throws Exception {
        Optional<Product> cheapestBook = productList.stream()
                .filter(product -> product.getType().equals(BOOK_CATEGORY))
                .min(Comparator.comparing(Product::getPrice));

        if (cheapestBook.isPresent()) {
            return cheapestBook.get();
        } else {
            throw new Exception("Product [category: " + BOOK_CATEGORY + "] not found");
        }
    }

    public static List<Product> getLastThreeAddedProducts(final List<Product> productList, final int lastAddedProductsCount) {
        return productList.stream()
                .sorted(Comparator.comparing(Product::getAddedDate).reversed())
                .limit(lastAddedProductsCount)
                .collect(Collectors.toList());
    }

    public static double getTotalCostOfNewCheapBooks(final List<Product> productList, final LocalDate date, final double maxPrice) {
        return productList.stream()
                .filter(product -> product.getType().equals(BOOK_CATEGORY))
                .filter(product -> product.getPrice() <= maxPrice)
                .filter(product -> product.getAddedDate().getYear() == date.getYear())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public static Map<String, List<Product>> groupProductsByType(final List<Product> productList) {
        return productList.stream()
                .collect(Collectors.groupingBy(Product::getType));
    }
}
