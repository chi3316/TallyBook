package PengTallyBook.Service;

import PengTallyBook.DAO.RecordDAO;
import PengTallyBook.Entity.Record;
import PengTallyBook.Gui.Page.SpendPage;
import PengTallyBook.Util.DateUtil;
import PengTallyBook.Util.GuiUtil;

import java.util.List;

/**
 * 对数据库业务进行封装，给SpendPanel使用
 */
public class SpendService {
    public SpendPage getSpendPage() {
        RecordDAO dao = new RecordDAO();
        List<Record> thisMonthRecords = dao.listThisMonth();
        List<Record> todayRecords = dao.listToday();
        int totalDays = DateUtil.thisMonthTotalDay();

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;
        int monthBudget = new ConfigService().getIntBudget();
        for(Record record : thisMonthRecords) {
            monthSpend += record.getAmount();
        }
        for(Record record : todayRecords) {
            todaySpend += record.getAmount();
        }

        monthAvailable = monthBudget - monthSpend;
        avgSpendPerDay = monthSpend / totalDays;
        monthLeftDay = DateUtil.thisMonthLeftDay();
        dayAvgAvailable = monthAvailable/monthLeftDay;
        usagePercentage = monthSpend * 100 / monthBudget;

        SpendPage spendPage = new SpendPage(monthSpend, todaySpend, avgSpendPerDay,
                monthAvailable, dayAvgAvailable, monthLeftDay, usagePercentage);
        return spendPage;
    }
}
