package PengTallyBook.Service;

import PengTallyBook.DAO.RecordDAO;
import PengTallyBook.Entity.Record;
import PengTallyBook.Util.DateUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 对 record 数据库的业务进行封装
 */
public class RecordService {
    //static 这条记录是共享的，任何改动都会被记录
    private static RecordDAO dao = new RecordDAO();

    public void add(int sign_number,double amount,java.util.Date date,String desc) throws SQLException, IOException {
        Record record = new Record();
        record.setRecord_date(date);
        record.setSign_number(sign_number);
        record.setAmount(amount);
        record.setDesc(desc);
        dao.add(record);
    }

    public void update(int sign_number,double amount,java.util.Date date,String desc,int serial_number)  {
        Record record = new Record();
        record.setRecord_date(date);
        record.setSign_number(sign_number);
        record.setAmount(amount);
        record.setDesc(desc);
        record.setSerial_number(serial_number);
        try {
            dao.update(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        dao.delete(id);
    }

    //获取指定月的 record 数据
    public List<Record> listMonth(Date startDay) {
        Date endDay = DateUtil.monthEnd(startDay);
        return dao.list(startDay, endDay);
    }
}
