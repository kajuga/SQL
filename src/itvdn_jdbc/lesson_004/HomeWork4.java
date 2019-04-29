package itvdn_jdbc.lesson_004;

import java.sql.*;

public class HomeWork4 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Создаем объект класса
        HomeWork4 hw = new HomeWork4();

        // Задаем параметры подключения к БД
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String password = "1234";
        Class.forName("com.mysql.jdbc.Driver");

        // Выполняем подключение к БД
        // Устанавливаем значения для параметров type и concurrency
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            // Получаем результирующий набор
            ResultSet rs = null;
            try {
                rs = statement.executeQuery("SELECT * FROM phonebook");

                // Вызываем созданные методы
//                hw.insertNewAbonent(rs, 111114444, "Peter"); // этот вызов закомментирован, во избежание дублирования записей
                hw.outputAbonents(rs);
            } catch (SQLException ex){
                ex.printStackTrace();
            } finally {
                if(rs != null)
                    rs.close();
                else
                    System.out.println("Ошибка чтения с БД!");
            }
        }
    }

    // Метод для вставки новой строки в таблицу Phonebook
    public void insertNewAbonent(ResultSet resultSet, int number, String name) throws SQLException {
        // Здесь используем прокручиваемый и обновляемый результирующий набор
        resultSet.moveToInsertRow();
        resultSet.updateInt("number", number);
        resultSet.updateString("lastName", name);
        resultSet.insertRow();
        resultSet.moveToCurrentRow();
    }

    // Метод для вывода записей с таблицы.
    public void outputAbonents(ResultSet resultSet) throws SQLException {
        // Для вывода используем метаданные
        ResultSetMetaData mdrs = resultSet.getMetaData();
        while (resultSet.next()){
            for (int i = 1; i <= mdrs.getColumnCount(); ++i) {
                String columnName = mdrs.getColumnName(i);
                String value = resultSet.getString(i);
                System.out.print(columnName + "= " + value + " ");
            }
            System.out.println("");
        }
    }
}
