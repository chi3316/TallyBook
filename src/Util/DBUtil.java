package PengTallyBook.Util;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class DBUtil {
    public static Connection getConnection() throws IOException, SQLException {
        var properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url,user,password);
    }

    public static java.sql.Timestamp utilToSql(java.util.Date d) {
        long time = d.getTime();
        return new java.sql.Timestamp(time);
    }
}
