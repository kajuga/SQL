USE ShopDB;



SELECT Products.ProdID, Description, SUM(Qty) AS Qty, SUM(TotalPrice) AS TotalSold
FROM Products
         INNER JOIN OrderDetails
                    ON Products.ProdID = OrderDetails.ProdID
GROUP BY Products.ProdID, Description;



SELECT Products.ProdID, Description, Qty, TotalPrice
FROM Products
         JOIN OrderDetails
              ON Products.ProdID = OrderDetails.ProdID;



SELECT FName, LName, MName, SUM(TotalPrice) AS TotalSold
FROM Employees -- [Total Sold]
         JOIN Orders
              ON Employees.EmployeeID = Orders.EmployeeID
         JOIN OrderDetails
              ON Orders.OrderID = OrderDetails.OrderID
GROUP BY Employees.FName,
         Employees.LName,
         Employees.MName;



SELECT FName, LName, MName, SUM(TotalPrice) AS TotalSold
FROM Employees -- [Total Sold]
         LEFT JOIN Orders
                   ON Employees.EmployeeID = Orders.EmployeeID
         LEFT JOIN OrderDetails
                   ON Orders.OrderID = OrderDetails.OrderID
GROUP BY Employees.FName,
         Employees.LName,
         Employees.MName;


SELECT Emp1.FName, Emp1.MName, Emp1.LName, Emp2.FName, Emp2.MName, Emp2.LName
FROM Employees Emp1
         JOIN Employees Emp2
              ON Emp1.EmployeeID = Emp2.ManagerEmpID;