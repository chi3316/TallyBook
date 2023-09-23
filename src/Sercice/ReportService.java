package PengTallyBook.Service;

import PengTallyBook.DAO.RecordDAO;
import PengTallyBook.Entity.Record;
import PengTallyBook.Util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportService {
    /**
     * @param d            指定的一天
     * @param monthRawData 这个月的所有数据的 record列表
     * @return 统计这一天花了多少钱
     */
    private int getDaySpend(Date d, List<Record> monthRawData) {
        int daySpend = 0;
        for (Record record : monthRawData) {
            if (record.getRecord_date().equals(d))
                daySpend += record.getAmount();
        }
        return daySpend;
    }

    /**
     * @return 一个当前月每天的 record 列表
     */
    public List<Record> listThisMonthRecords() {
        RecordDAO dao = new RecordDAO();
        List<Record> monthRawData = dao.listThisMonth();
        List<Record> result = new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date theDayOfThisMonth = c.getTime();
            int daySpend = getDaySpend(theDayOfThisMonth, monthRawData);
            r.setAmount(daySpend);
            result.add(r);
        }
        return result;
    }
}
