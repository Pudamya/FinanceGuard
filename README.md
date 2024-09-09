# FinanceGuard

FinanceGuard is a personal finance management application designed to help users manage their financial transactions, track expenses, and analyze their spending patterns. Built using Java Spring Boot, Thymeleaf, and MySQL, the application provides a secure and user-friendly platform for managing personal finances.

### Features

- **User Registration and Authentication**: Secure user registration and login functionality using Spring Security.
- **Transaction Management**: Add, edit, and delete transactions to keep track of expenses and income.
- **Category Management**: Organize transactions by categories for better financial insights.
- **Dashboard Overview**: Visualize expenses and income using interactive charts and graphs.
- **Filtering and Sorting**: Filter and sort transactions by date, category, and amount.
- **Currency Support**: Supports currency in rupees (₹).

### Tech Stack

- **Backend**: Java, Spring Boot, Spring MVC, Spring Data JPA, Spring Security
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript
- **Database**: MySQL
- **Tools & Libraries**: Maven, Lombok, Chart.js

### Installation

To run this project locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Pudamya/FinanceGuard.git
   ```
   
2. **Navigate to the project directory:**
   ```bash
   cd FinanceGuard
   ```

3. **Set up the MySQL database:**
   - Create a new MySQL database named `financeguard`.
   - Update the `src/main/resources/application.properties` file with your MySQL credentials.

4. **Build the project:**
   ```bash
   mvn clean install
   ```

5. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

6. **Access the application:**
   Open your web browser and go to `http://localhost:8080`.

### Usage

- **Register** a new account or **log in** if you already have an account.
- **Add** your income and expense transactions to track your finances.
- **View** your financial overview on the dashboard.
- **Filter** transactions to analyze spending in different categories.

### Contributing

Contributions are welcome! 

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Contact

For any inquiries or support, please reach out to Pudamya Rathnayake at [pudamya.rathnayake.contact@gmail.com](mailto:pudamya.rathnayake.contact@gmail.com).
