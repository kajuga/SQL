package itvdn_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Создание подключения
public class TestConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //задаю параментры подключения: URL, имя пользователя и пароль
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String pass = "pass";
//Регистрация драйвера с помощью статического инициализатора
//        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, userName, pass)) {
            System.out.println("Connection Successful");

        }



    }
}
