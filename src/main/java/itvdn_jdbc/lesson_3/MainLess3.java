package itvdn_jdbc.lesson_3;

import java.sql.*;

public class MainLess3 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:postgresql://localhost:5432/first_lesson";
        String userName = "kajuga";
        String pass = "sashok";
        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(url, userName, pass)){
            PreparedStatement prepStat = null;
            PreparedStatement prepStat2 = null;
            try {
                prepStat = connection.prepareStatement("INSERT INTO Books (name, price) VALUES (?, ?)");
                prepStat.setString(1, "Mein Campf");
                prepStat.setDouble(2, 66.6);
                prepStat.execute();

                ResultSet rs = null;
                try {
                    rs = prepStat2.executeQuery("SELECT * FROM Books");
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String name = rs.getString(2);
                        double price = rs.getDouble(3);
                        System.out.println("Id = " + id + " name: " + name + " price= " + price);

                    }
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }finally {
                    if(rs != null) {
                        rs.close();
                    } else {
                        System.out.println("Error reading BD!");
                    }
                }

            } catch (SQLException exc) {
                exc.printStackTrace();
            } finally {
                prepStat.close();
                prepStat2.close();
            }
        }
    }
}