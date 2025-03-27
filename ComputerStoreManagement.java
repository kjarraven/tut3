import java.util.*;

// Product Class
class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    public Product(int id, String name, String category, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void displayInfo() {
        System.out.println("Product ID: " + id + ", Name: " + name + ", Category: " + category + ", Price: " + price + ", Stock: " + stockQuantity);
    }

    public int getId() { return id; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public void reduceStock(int quantity) { stockQuantity -= quantity; }
    public String getCategory() { return category; }
    public String getName() { return name; }
}

// Customer Class
class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isLoyalCustomer;

    public Customer(int id, String firstName, String lastName, String email, boolean isLoyalCustomer) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isLoyalCustomer = isLoyalCustomer;
    }

    public void displayInfo() {
        System.out.println("Customer ID: " + id + ", Name: " + firstName + " " + lastName + ", Email: " + email + ", Loyal: " + isLoyalCustomer);
    }
    public boolean isLoyalCustomer() { return isLoyalCustomer; }
    public int getId() { return id; }
    public String getFullName() { return firstName + " " + lastName; }
}

// Order Class
class Order {
    private int id;
    private Customer customer;
    private List<Product> products;
    private List<Integer> quantities;
    private Date orderDate;
    private String status;

    public Order(int id, Customer customer, List<Product> products, List<Integer> quantities) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.quantities = quantities;
        this.orderDate = new Date();
        this.status = "New";
    }

    public double calculateTotalValue() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getPrice() * quantities.get(i);
        }
        if (customer.isLoyalCustomer()) {
            total *= 0.9; // 10% discount
        }
        return total;
    }

    public void displayDetails() {
        System.out.println("Order ID: " + id + "\nCustomer: " + customer.getFullName() + "\nStatus: " + status + "\nTotal: " + calculateTotalValue());
        for (int i = 0; i < products.size(); i++) {
            System.out.println("Product: " + products.get(i).getName() + ", Quantity: " + quantities.get(i));
        }
    }
}

// ComputerStore Class
class ComputerStore {
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public void addProduct(Product product) { products.add(product); }
    public void addCustomer(Customer customer) { customers.add(customer); }
    public void createOrder(Customer customer, List<Product> products, List<Integer> quantities) {
        Order order = new Order(orders.size() + 1, customer, products, quantities);
        orders.add(order);
        order.displayDetails();
    }
    public List<Product> getProducts() { return products; }
}

// Main class to test the system
public class ComputerStoreManagement {
    public static void main(String[] args) {
        ComputerStore store = new ComputerStore();

        // Adding Products
        store.addProduct(new Product(1, "Laptop", "Electronics", 1000.0, 10));
        store.addProduct(new Product(2, "Mouse", "Accessories", 20.0, 50));
        store.addProduct(new Product(3, "Monitor", "Electronics", 300.0, 15));

        // Adding Customers
        Customer nikita = new Customer(1, "Nikita", "Ivanov", "nikita@example.com", true);
        Customer vlad = new Customer(2, "Vlad", "Petrov", "vlad@example.com", false);
        store.addCustomer(nikita);
        store.addCustomer(vlad);

        // Creating Orders
        List<Product> order1Products = Arrays.asList(store.getProducts().get(0), store.getProducts().get(1));
        List<Integer> order1Quantities = Arrays.asList(1, 1);
        store.createOrder(nikita, order1Products, order1Quantities);

        List<Product> order2Products = Arrays.asList(store.getProducts().get(2));
        List<Integer> order2Quantities = Arrays.asList(1);
        store.createOrder(vlad, order2Products, order2Quantities);
    }
}
