package java.by.htp.ahremenko.task61;

import java.by.htp.ahremenko.task61.dao.BookDao;
import java.by.htp.ahremenko.task61.model.Book;
import java.sql.*;
import java.util.ResourceBundle;

public class ConnectorDb {
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        return DriverManager.getConnection(url, user, pass);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        BookDao bookDao = new BookDao(connection);
        Book book = bookDao.getEntityById(1);
        System.out.println(book);
    }
}
