package org.example.hw19;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {
    private final String BOOK_CATEGORY = "Book";
    private final String TOY_CATEGORY = "Toy";

    private List<Product> productList;

    @BeforeEach
    void setUp() {
        productList = new ArrayList<>();
    }

    @Test
    void getExpensiveBooks() {
        final double priceThreshold = 250;
        productList.add(new Product(BOOK_CATEGORY, 100, false));
        productList.add(new Product(TOY_CATEGORY, 200, false));
        productList.add(new Product(BOOK_CATEGORY, 300, false));
        productList.add(new Product(BOOK_CATEGORY, 400, false));
        productList.add(new Product(BOOK_CATEGORY, 500, false));

        List<Product> expensiveBooks = Product.getExpensiveBooks(productList, priceThreshold);

        assertEquals(3, expensiveBooks.size());
        assertTrue(expensiveBooks.stream().allMatch(product -> product.getType().equals(BOOK_CATEGORY)));
        assertTrue(expensiveBooks.stream().allMatch(product -> product.getPrice() > priceThreshold));
    }

    @Test
    void getDiscountedBooks() {
        productList.add(new Product(BOOK_CATEGORY, 100, true));
        productList.add(new Product(TOY_CATEGORY, 200, true));
        productList.add(new Product(BOOK_CATEGORY, 300, false));
        productList.add(new Product(BOOK_CATEGORY, 400, true));
        productList.add(new Product(BOOK_CATEGORY, 500, true));

        List<Product> discountedBooks = Product.getDiscountedBooks(productList);

        assertEquals(3, discountedBooks.size());
    }

    @Test
    void getCheapestBook() throws Exception {
        productList.add(new Product(TOY_CATEGORY, 100, false));
        productList.add(new Product(BOOK_CATEGORY, 50, false));
        productList.add(new Product(BOOK_CATEGORY, 200, false));
        productList.add(new Product(BOOK_CATEGORY, 300, false));

        Product cheapestBook = Product.getCheapestBook(productList);

        assertEquals(BOOK_CATEGORY, cheapestBook.getType());
        assertEquals(50, cheapestBook.getPrice());
    }

    @Test
    void getLastThreeAddedProducts() {
        final LocalDate date = LocalDate.of(2020, 1, 1);
        productList.add(new Product(BOOK_CATEGORY, 100, false, date));
        productList.add(new Product(BOOK_CATEGORY, 200, false, date.plusDays(1)));
        productList.add(new Product(BOOK_CATEGORY, 300, false, date.plusDays(2)));
        productList.add(new Product(BOOK_CATEGORY, 400, false, date.plusDays(3)));
        productList.add(new Product(BOOK_CATEGORY, 500, false, date.plusDays(4)));

        final int lastAddedProductsCount = 3;
        List<Product> lastThreeAddedProducts = Product.getLastThreeAddedProducts(productList, lastAddedProductsCount);

        assertEquals(3, lastThreeAddedProducts.size());
    }

    @Test
    void getTotalCostOfNewCheapBooks() {
        productList.add(new Product(BOOK_CATEGORY, 10, false, LocalDate.now()));
        productList.add(new Product(BOOK_CATEGORY, 20, false, LocalDate.now()));
        productList.add(new Product(BOOK_CATEGORY, 30, false, LocalDate.now()));
        productList.add(new Product(TOY_CATEGORY, 40, false, LocalDate.now()));
        productList.add(new Product(BOOK_CATEGORY, 100, false, LocalDate.now()));

        final LocalDate dateThreshold = LocalDate.now();
        final double priceThreshold = 75;
        double totalCostOfNewCheapBooks = Product.getTotalCostOfNewCheapBooks(productList, dateThreshold, priceThreshold);

        assertEquals(60, totalCostOfNewCheapBooks);
    }

    @Test
    void groupProductsByType() {
        productList.add(new Product(BOOK_CATEGORY, 10, false, LocalDate.now()));
        productList.add(new Product(BOOK_CATEGORY, 20, false, LocalDate.now()));
        productList.add(new Product(BOOK_CATEGORY, 30, false, LocalDate.now()));
        productList.add(new Product(TOY_CATEGORY, 40, false, LocalDate.now()));
        productList.add(new Product(BOOK_CATEGORY, 100, false, LocalDate.now()));

        Map<String, List<Product>> groupedProducts = Product.groupProductsByType(productList);

        assertEquals(2, groupedProducts.size());
        assertEquals(4, groupedProducts.get(BOOK_CATEGORY).size());
        assertEquals(1, groupedProducts.get(TOY_CATEGORY).size());
    }
}