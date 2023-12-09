# Stockify

Stockify is an inventory management system built using Java, JavaFX, and MySQL for the database. It allows users to manage products, orders, suppliers, and categories.

## Installation

To use Stockify, you need to have Java and MySQL installed on your machine. You also need to have the JavaFX library added to your project.

1. Clone the repo: `git clone https://github.com/Mohamed-Rabie-Abdelhameed/Stockify.git`
2. Open the project in your preferred Java IDE.
3. Add the JavaFX library to your project.
4. Import the database schema located in the `DB` folder to your local MySQL server.
5. Update the database connection properties in the `DB.java` file to match your local MySQL server settings.
6- Install the application on your machine using the `setup.exe` file in the the `setup` folder.   
### use `admin` for the username and password

## Usage

The Stockify app consists of four pages:

1. *Inventory*: displays the products available and their quantity, allows the user to add, edit, or delete products.
2. *Orders*: keeps a record of all the orders, allows the user to add, edit, or delete orders.
3. *Suppliers*: keeps a record of all the Suppliers, allows the user to add, edit, or delete Suppliers.
4. *Categories*: keeps a record of all the Categories, allows the user to add, edit, or delete Categories, and displays the low in stock categories and a bar chart of each category and the number of products in this category.

To start using the app, run the `App.java` file in your Java IDE.

## Contributing

If you find any issues or have any suggestions for improving Stockify, feel free to submit a pull request or open an issue on the GitHub repository.
