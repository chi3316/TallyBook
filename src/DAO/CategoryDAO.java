package PengTallyBook.DAO;

import PengTallyBook.Entity.Category;
import PengTallyBook.Util.DBUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对category表进行增删改查操作
 */
public class CategoryDAO {
    public void add(Category category) throws SQLException, IOException {
        String sql = "INSERT INTO category(name) values (?)";
        Connection connection = DBUtil.getConnection();
        var preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,category.getName());
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        // 遍历结果集
        while (rs.next()) {
            // 获取每一行的键值
            int sign_number = rs.getInt(1);
            // 打印输出  System.out.println("插入的记录的主键为：" + id);
            category.setSign_number(sign_number);
        }
        // 关闭资源
        rs.close();
        preparedStatement.close();
        connection.close();
    }

    public int delete(int signNumber) throws SQLException, IOException {
        String sql = "DELETE FROM category WHERE sign_number = ?";
        Connection connection = DBUtil.getConnection();
        var preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,signNumber);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result;
    }

    public int update(Category category) throws SQLException, IOException {
        String sql = "UPDATE category SET name = ? WHERE sign_number = ?";
        Connection connection = DBUtil.getConnection();
        var preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,category.getName());
        preparedStatement.setInt(2,category.getSign_number());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result;
    }

    //通过sign_number 获取 category的信息，返回一个Category对象
    public Category get(int sign_number) throws IOException,SQLException{
        String sql = "SELECT * FROM category WHERE sign_number = ?";
        Connection connection = DBUtil.getConnection();
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,sign_number);
        var resultSet = preparedStatement.executeQuery();
        int number = 0;
        String name = "";
        Category category = null;
        while (resultSet.next()) {
            name = resultSet.getString(1);
            number = resultSet.getInt(2);
        }
        preparedStatement.close();
        connection.close();
        return new Category(name,number);
    }

    //获取传入的从start下标开始的一共counts条记录的category的信息，返回一个list<Category>
    public List<Category> list(int start,int counts) throws SQLException, IOException {
        String sql = "SELECT * FROM category ORDER BY sign_number LIMIT ?,?";
        Connection connection = DBUtil.getConnection();
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,start);
        preparedStatement.setInt(2,counts);
        var resultSet = preparedStatement.executeQuery();

        List<Category> categories = new ArrayList<>();
        while(resultSet.next()){
            String name = resultSet.getString(1);
            int sign_number = resultSet.getInt(2);
            categories.add(new Category(name,sign_number));
        }
        preparedStatement.close();
        connection.close();
        return categories;
    }

    //重载list,不传入参数时返回全部的categories
    public List<Category> list() throws SQLException, IOException {
        return list(0, Short.MAX_VALUE);
    }
}
