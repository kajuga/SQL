package itvdn_jdbc.lesson_004;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class RowSetLesson {
    //происываем url pass usera для драйвера
    static String url = "jdbc:mysql://localhost:3306/first_lesson";
    static String userName = "root";
    static String pass = "pass";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //тут статик методом получем результирующий набор
        ResultSet resultSet = getResSet();
//        while (resultSet.next())
//            System.out.println(resultSet.getString("name"));
        CachedRowSet rowSet = (CachedRowSet) resultSet;
        rowSet.setCommand("SELECT * FROM Books WHERE price > ?");
        rowSet.setDouble(1, 30);

        rowSet.setUrl(url);
        rowSet.setUsername(userName);
        rowSet.setPassword(pass);
        rowSet.execute();

        rowSet.absolute(2);
        rowSet.deleteRow();
        rowSet.beforeFirst();
        Connection connection = DriverManager.getConnection(url, userName, pass);
        connection.setAutoCommit(false);
        rowSet.acceptChanges(connection);

        while (rowSet.next()) {
            String name = rowSet.getString("name");
            double price = rowSet.getDouble(3);
            System.out.println(name + " " + price);
        }
    }
//полчение результирующего набора
    static ResultSet getResSet() throws ClassNotFoundException, SQLException {
        //регистрация драйвера
        Class.forName("com.mysql.jdbc.Driver");
        //try with resourses connection-statement-resultset выполняем запрос, кешруя строку, чтоб без соединения с бд
        try (Connection conn = DriverManager.getConnection(url, userName, pass);
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Books");
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet crs = factory.createCachedRowSet();
            crs.populate(rs);
            return crs;
        }
    }
}