package itvdn_jdbc.lesson_003;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.io.OutputStream;
import java.sql.*;

public class BlobExample {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String pass = "pass";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(url, userName, pass);
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE Images (name VARCHAR(15), date DATE, image BLOB)");

            PreparedStatement preparedStatement = null;
            try {

                BufferedImage image = ImageIO.read(new File("/home/kajuga/projects/SQL/src/itvdn_jdbc/lesson_003/kajuga.jpg"));
                Blob nigga = conn.createBlob();
                try (OutputStream outputStream = nigga.setBinaryStream(1)) {
                    ImageIO.write(image, "jpg", outputStream);
                }
                preparedStatement = conn.prepareStatement("INSERT INTO Images (name, date, image) VALUES (?, {d ?}, ?)");
                preparedStatement.setString(1, "NIGGA");
                preparedStatement.setDate(2, Date.valueOf("2018-04-07"));
                preparedStatement.setBlob(3, nigga);
                preparedStatement.execute();

                ResultSet resultSet = null;
                try {
                    resultSet = preparedStatement.executeQuery("SELECT * FROM Images");
                    while (resultSet.next()) {
                        Blob newNigga = resultSet.getBlob("image");
                        BufferedImage image1 = ImageIO.read(newNigga.getBinaryStream());
                        File outputFile = new File("/home/kajuga/projects/SQL/src/itvdn_jdbc/lesson_003/saved.jpg");
                        ImageIO.write(image1, "jpg", outputFile);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if (resultSet != null) {
                        resultSet.close();
                    } else {
                        System.err.println("Ошибка чтения БД!!");
                    }
                }
            } catch (SQLException exx) {
                exx.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        }
    }
}
