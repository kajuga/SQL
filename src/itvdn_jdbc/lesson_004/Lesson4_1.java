package itvdn_jdbc.lesson_004;

import java.io.IOException;
import java.sql.*;

public class Lesson4_1 {
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
                if (rs.absolute(2))
                    System.out.println(rs.getString("name"));
                if (rs.previous())
                    System.out.println(rs.getString("name"));
                if (rs.last())
                    System.out.println(rs.getString("name"));
                if (rs.relative(-3)) {
                    ResultSetMetaData rsmd = rs.getMetaData();


                    while (rs.next()) {
                        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                            String field = rsmd.getColumnName(i);
                            String value = rs.getString(field);
                            System.out.print(field + ": " + value + " ");
                        }
                        System.out.println("");
                    }
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                } else {
                    System.err.println("Ошибка чтения БД");
                }
            }
        }


    }
}
