package by.denis.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        // ВРЕМЕННЫЙ ТЕСТ ПОДКЛЮЧЕНИЯ
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/product_db",
                    "admin",
                    "admin123"
            );
            System.out.println("✅ Успешное подключение к product_db!");
            conn.close();
        } catch (Exception e) {
            System.err.println("❌ Ошибка подключения: " + e.getMessage());
            e.printStackTrace();
        }

        // ЗАПУСК ОСНОВНОГО ПРИЛОЖЕНИЯ
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
