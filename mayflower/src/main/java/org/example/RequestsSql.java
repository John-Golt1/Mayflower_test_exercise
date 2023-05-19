package org.example;

public class RequestsSql {
    public static final String ALL_FROM_CUSTOMERS = "SELECT * FROM Customers;";
    public static final String CUSTOMERS_FROM_LONDON = "SELECT * FROM Customers WHERE city='London';";
    public static final String NEW_DATA = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ('Anton', 'QA', 'Matice 75', 'Belgrade', '12345', 'Serbia');";
    public static final String CONTACT_NAME_QA = "SELECT * FROM Customers WHERE ContactName = 'QA';";
    public static final String CHANGE_DATA = "UPDATE Customers SET CustomerName = 'New Name', Address = 'New Address', City = 'New City', PostalCode = 'New Postal Code', Country = 'New Country' WHERE CustomerID = '%s';";
    public static final String CHECK_CHANGE_DATA = "SELECT * FROM Customers WHERE CustomerID = '%s';";
}
