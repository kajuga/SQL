package itvdn_jdbc.lesson_003;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String pass = "pass";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(url, userName, pass)) {
            PreparedStatement prepStat = null;
            try {
                prepStat = conn.prepareStatement("INSERT INTO Books (name, price) VALUES (?, ?)");
                prepStat.setString(1, "Schindler`s list");
                prepStat.setDouble(2, 33.5);
                prepStat.execute();

                ResultSet rs = null;
                try {
                    rs = prepStat.executeQuery("SELECT * FROM Books");
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String name = rs.getString(2);
                        double price = rs.getDouble(3);
                        System.out.println("id= " + id + " name= " + name + " price= " + price);
                    }
                } catch (SQLException exc) {
                    exc.printStackTrace();
                } finally {
                    if (rs != null) {
                        rs.close();
                    } else {
                        System.err.println("Ошибка чтения с БД!");
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                prepStat.close();
            }

            CallableStatement callableStatement = null;
            try {
                callableStatement = conn.prepareCall("{CALL booksCount(?)}");
                callableStatement.registerOutParameter(1, Types.INTEGER);
                callableStatement.execute();
                System.out.println("Количество записей в таблице: " + callableStatement.getInt(1));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                callableStatement.close();
            }


        }


    }

}
