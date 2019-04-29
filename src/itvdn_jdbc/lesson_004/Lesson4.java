package itvdn_jdbc.lesson_004;

import java.io.IOException;
import java.sql.*;

public class Lesson4 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String pass = "pass";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(url, userName, pass);
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = null;
            try {
                rs = statement.executeQuery("SELECT * FROM Books");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    double price = rs.getDouble(3);
                    if(id == 4) {
                        rs.updateString("name", "Spartacus (discount)");
                        rs.updateDouble(3, price - 10);
                        rs.updateRow();
                    }
                }

            } catch (SQLException ex) {
                 ex.printStackTrace();
            } finally {
                if (rs !=null) {
                    rs.close();
                } else {
                    System.err.println("Ошибка чтения БД");
                }
            }
        }
        }
}