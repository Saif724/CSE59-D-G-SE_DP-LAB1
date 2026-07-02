
# SOLID Principles:

SOLID is an acronym that represents five core design principles of object-oriented programming (OOP). Introduced by Robert C. Martin (often called "Uncle Bob"), these principles serve as a guideline for designing software that is easy to maintain, understand, and scale. When codebases grow, poorly designed classes become rigid and fragile. The SOLID principles help developers avoid technical debt by keeping code modular and decoupled. Java, being a strictly object-oriented language, is an excellent medium for demonstrating these concepts.

---

### What Each Letter Means

* **S - Single Responsibility Principle (SRP):** A class should have one, and only one, reason to change. This means a class should only have one job or responsibility.
* **O - Open/Closed Principle (OCP):** Software entities (classes, modules, functions) should be open for extension but closed for modification. You should be able to add new functionality without rewriting existing code.
* **L - Liskov Substitution Principle (LSP):** Objects of a superclass should be replaceable with objects of its subclasses without breaking the application. A subclass must behave in a way that the parent class is expected to behave.
* **I - Interface Segregation Principle (ISP):** A client should never be forced to implement an interface that it doesn't use. It is better to have many small, specific interfaces rather than one large, general-purpose one.
* **D - Dependency Inversion Principle (DIP):** High-level modules should not depend on low-level modules. Both should depend on abstractions (e.g., interfaces). Furthermore, abstractions should not depend on details; details should depend on abstractions.

---

### Deep Dive: The Single Responsibility Principle (S)

The core idea of SRP is **cohesion**. If a class handles data logic, presentation logic, and database routing all at once, a change in how the database works could accidentally break the presentation logic.

#### ❌ When S is Violated

In this example, the `Report` class is doing too much. It holds the state, formats the data, and interacts with the file system. It has *three* distinct reasons to change.

```java
import java.io.FileWriter;
import java.io.IOException;

public class Report {
    private String title;
    private String content;

    public Report(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Responsibility 1: Data formatting
    public String formatToJson() {
        return String.format("{\"title\": \"%s\", \"content\": \"%s\"}", this.title, this.content);
    }

    // Responsibility 2: Data persistence (File I/O)
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(this.formatToJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

#### ✅ When S is Followed

To fix this, we break the responsibilities into distinct classes. Now, if we want to change how reports are saved (e.g., to a SQL database instead of a text file), we don't have to touch the `Report` data class or the `ReportFormatter`.

```java
import java.io.FileWriter;
import java.io.IOException;

// Responsibility 1: Holding the data (POJO)
public class Report {
    private String title;
    private String content;

    public Report(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
}

// Responsibility 2: Formatting the data
public class ReportFormatter {
    public static String formatToJson(Report report) {
        return String.format("{\"title\": \"%s\", \"content\": \"%s\"}", 
                             report.getTitle(), report.getContent());
    }
}

// Responsibility 3: Saving the data
public class ReportSaver {
    public static void saveToFile(String data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

---

### Student Task: Refactor for SRP

You can hand this snippet to your students. Explain that this `User` class is currently handling user properties, input validation, and database operations all in one place.

**The Goal for the Students:** Refactor this code into separate classes so that it strictly adheres to the Single Responsibility Principle.

**The Broken Code:**

```java
public class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public boolean isValidEmail() {
        // Checks if the email contains an '@' symbol
        if (this.email != null && this.email.contains("@")) {
            return true;
        }
        System.out.println("Invalid email format.");
        return false;
    }

    public void saveToDatabase() {
        if (this.isValidEmail()) {
            System.out.println("Connecting to database...");
            System.out.println("Saving user " + this.username + " to the users table.");
        }
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
}

```

*(Instructor Note: The ideal student solution should separate this into a `User` class for the state, an `EmailValidator` class containing a static method for the validation logic, and a `UserRepository` or `DatabaseManager` class for the mock database print statements.)*







# The Open/Closed Principle (O)

The Open/Closed Principle states that software entities should be **open for extension, but closed for modification**. This means you should be able to add new features to a system by writing *new* code, rather than altering *existing* code. This prevents the introduction of bugs into code that is already tested and working.

#### ❌ When O is Violated

Here, if we want to add a new customer type (like "Employee"), we have to open the `DiscountCalculator` class and modify the `calculateDiscount` method. This breaks the principle.

```java
public class DiscountCalculator {
    public double calculateDiscount(String customerType, double price) {
        if (customerType.equals("Regular")) {
            return price * 0.05; // 5% discount
        } else if (customerType.equals("VIP")) {
            return price * 0.20; // 20% discount
        }
        return 0; // No discount
    }
}

```

#### ✅ When O is Followed

By using an interface, the `DiscountCalculator` is now "closed" for modification. If we want to add an "Employee" discount, we simply create a new class `EmployeeDiscount` that implements `DiscountStrategy`. The existing calculator code never changes.

```java
// The Abstraction
public interface DiscountStrategy {
    double applyDiscount(double price);
}

// Extensions (New classes instead of modifying old ones)
public class RegularDiscount implements DiscountStrategy {
    public double applyDiscount(double price) { return price * 0.05; }
}

public class VIPDiscount implements DiscountStrategy {
    public double applyDiscount(double price) { return price * 0.20; }
}

// The closed calculator
public class DiscountCalculator {
    public double calculate(DiscountStrategy strategy, double price) {
        return strategy.applyDiscount(price);
    }
}

```

#### YouDO2: Refactor for OCP

**The Goal for the Students:** Refactor this `AreaCalculator` so that adding a new shape (like a `Triangle`) doesn't require modifying the `calculateTotalArea` method.

**The Broken Code:**

```java
class Rectangle {
    public double length;
    public double width;
}

class Circle {
    public double radius;
}

public class AreaCalculator {
    public double calculateTotalArea(Object[] shapes) {
        double area = 0;
        for (Object shape : shapes) {
            if (shape instanceof Rectangle) {
                Rectangle r = (Rectangle) shape;
                area += r.length * r.width;
            } else if (shape instanceof Circle) {
                Circle c = (Circle) shape;
                area += Math.PI * (c.radius * c.radius);
            }
        }
        return area;
    }
}

```

*(Instructor Note: The solution should involve creating a `Shape` interface with a `calculateArea()` method. `Rectangle` and `Circle` should implement this interface, allowing `AreaCalculator` to simply call `shape.calculateArea()` in the loop without checking the type.)*

---






