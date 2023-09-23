package PengTallyBook.DAO;

import PengTallyBook.Entity.Record;
import PengTallyBook.Util.DBUtil;
import PengTallyBook.Util.DateUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordDAO {
    public void add(Record record) throws SQLException, IOException {
        /*DESC是SQL的保留关键字之一，用于表示降序排序。因此，在SQL语句中使用DESC作为列名或表名时，会引发语法错误。
        为了解决这个问题，可以将DESC用反引号（`）括起来，以明确告诉MySQL它是一个列名而不是关键字
        */
        String sql = "INSERT INTO record(sign_number,amount,record_date,`desc`) values(?,?,?,?)";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1,record.getSign_number());
        preparedStatement.setDouble(2,record.getAmount());
        //setDate()所需要的参数是java.sql.Date类型，这里会有类型不匹配的问题，需要将util.Data转换为sql.Date
        //preparedStatement.setDate(3,DBUtil.utilToSql(record.getRecord_date()));
        //想要保留时分秒使用setTimestamp()
        Date utilDate = record.getRecord_date();
        preparedStatement.setTimestamp(3,DBUtil.utilToSql(utilDate));
        preparedStatement.setString(4,record.getDesc());
        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        while(generatedKeys.next()) {
            int serial_number = generatedKeys.getInt(1);
            record.setSerial_number(serial_number);
        }

        generatedKeys.close();
        preparedStatement.close();
        connection.close();

    }

    //表的字段名最好不要弄得太相似，容易看走眼
    public int update(Record record) throws SQLException, IOException {
        String sql = "UPDATE record SET sign_number = ?,amount = ?,record_date = ?,`desc` = ? WHERE serial_number = ?";
        Connection connection = DBUtil.getConnection();
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,record.getSign_number());
        preparedStatement.setDouble(2,record.getAmount());
        Date utilDate = record.getRecord_date();
        preparedStatement.setTimestamp(3,DBUtil.utilToSql(utilDate));
        preparedStatement.setString(4,record.getDesc());
        preparedStatement.setInt(5,record.getSerial_number());
        int rows =  preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return rows;
    }

    /*
    * try-with-resources（也称为“try-with-resources”）是一种用于自动关闭资源的语法结构。
    * 它允许在try块中使用资源（例如数据库连接、文件流等），
    * 并在try块结束时自动关闭这些资源，而不需要显式调用close()方法或编写finally块来进行资源清理。
    */
    public int delete(int serial_number) {
        String sql = "DELETE FROM record WHERE serial_number = ?";
        int result = 0;
        try(Connection connection = DBUtil.getConnection();
            var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,serial_number);
            result = preparedStatement.executeUpdate();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    //通过传入一个sign_number来获取该条record的全部信息
    public Record get(int serial_number) {
        String sql = "SELECT * FROM record WHERE serial_number = ?";
        Record record = null;
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1,serial_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               int signNumber =  resultSet.getInt(1);
               double amount =  resultSet.getDouble(2);
               Date date =  resultSet.getDate(3);
               String desc = resultSet.getString(4);
               int serialNumber = resultSet.getInt(5);
               record = new Record(signNumber,amount,date,desc,serialNumber);
            }
        }catch (IOException ie) {
            ie.printStackTrace();
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return record;
    }

    public List<Record> list(int start,int counts) {
        String sql = "SELECT * FROM record ORDER BY serial_number LIMIT ?,?";
        List<Record> lists = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,counts);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Record record = new Record(resultSet.getInt(1),
                                           resultSet.getDouble(2),
                                           resultSet.getDate(3),
                                           resultSet.getString(4),
                                           resultSet.getInt(5));
                lists.add(record);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return lists;
    }

    public List<Record> list() {
        return list(0,Integer.MAX_VALUE);
    }

    //展示某一类记录
    public List<Record> list(int sign_number) {
        String sql = "select * from record where `sign_number` = ?";
        List<Record> records = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, sign_number);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Record record = new Record(resultSet.getInt(1),
                        resultSet.getDouble(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return records;
    }

    //展示某一天的记录
    public List<Record> list(java.util.Date date) {
        String sql = "SELECT * FROM record WHERE record_date  = ?";
        List<Record> lists = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1,DBUtil.utilToSql(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Record record = new Record(resultSet.getInt(1),
                        resultSet.getDouble(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                lists.add(record);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return lists;
    }

    //展示今天的记录
    public List<Record> listToday( ) {
        return list(new java.util.Date());
    }

    //展示某一段时间的记录
    public List<Record> list(java.util.Date start, java.util.Date end) {
        String sql = "SELECT * FROM record WHERE record_date  >= ? AND record_date <= ?";
        List<Record> lists = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1,DBUtil.utilToSql(start));
            preparedStatement.setTimestamp(2,DBUtil.utilToSql(end));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Record record = new Record(resultSet.getInt(1),
                        resultSet.getDouble(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                lists.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    //返回这个月的记录
    public List<Record> listThisMonth() {
        return list(DateUtil.monthBegin(),DateUtil.monthEnd());
    }

    //获取所以记录的条数
    public int getTotal() {
        String sql = "SELECT COUNT(*) FROM record ";
        int counts = 0;
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))  {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                counts = resultSet.getInt(1);
                return counts;
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return counts;
    }
}

