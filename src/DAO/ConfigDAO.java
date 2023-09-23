package PengTallyBook.DAO;

import PengTallyBook.Entity.Config;
import PengTallyBook.Util.DBUtil;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ConfigDAO {
    public void add(Config config) {
        String sql = "INSERT INTO config(`key`,`value`) values (?,?)";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,1)) {
            preparedStatement.setString(1,config.getKey());
            preparedStatement.setString(2,config.getValue());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while(generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                config.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int update(Config config) {
        String sql = "UPDATE config SET `key` = ?,`value` = ? WHERE `id` = ?";
        int result = 0;
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,config.getKey());
            preparedStatement.setString(2,config.getValue());
            preparedStatement.setInt(3,config.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(int id) {
        String sql = "DELETE FROM config WHERE id = ?";
        int result = 0;
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Config get(int id) {
        String sql = "SELECT * FROM config WHERE id = ?";
        Config config = null;
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id_ = resultSet.getInt(1);
                String key = resultSet.getString(2);
                String value = resultSet.getString(3);
                config = new Config(id_, key, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public Config getByKey(String key) {
        String sql = "SELECT * FROM config WHERE `key` = ?";
        Config config = null;
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String key_ = resultSet.getString(2);
                String value = resultSet.getString(3);
                config = new Config(id, key, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public List<Config> list(int start,int counts) {
        String sql = "SELECT * FROM config ORDER BY id DESC LIMIT ?,?";
        List<Config> list = null;
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,counts);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var config = new Config(resultSet.getInt(1),
                        resultSet.getString(2),resultSet.getString(3));
                list.add(config);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Config> list() {
        return list(0,Integer.MAX_VALUE);
    }
}
