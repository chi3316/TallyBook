package PengTallyBook.Gui.Panel;

import PengTallyBook.Gui.Listener.MonthPickerListener;
import PengTallyBook.Util.CenterPanel;
import PengTallyBook.Util.DateUtil;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
/**
 * MonthPickerPanel 是 HistoryPanel 中的月份选择器
 * 外部通过调用 MonthPickerPanel.instance.date 来获取选择器的时间
 * 本类写死了起始年份并保证了一定的年份使用的扩展性
 */

public class MonthPickerPanel extends WorkingPanel{
    static {
        GuiUtil.UseLNF();
    }
    public static MonthPickerPanel instance = new MonthPickerPanel();
    private final int startYear = 2003;

    //当前面板实例的时间
    public Date date = DateUtil.monthBegin();
    public JComboBox<Integer> comboBoxMonth = new JComboBox<>(makeMonths());
    public JComboBox<Integer> comboBoxYear = new JComboBox<>(makeYears());
    public JButton bSubmit = new JButton("查询");

    private MonthPickerPanel() {
       this.setLayout(new GridLayout(1,3,8,8));
       comboBoxYear.setSelectedIndex(DateUtil.thisYear() - startYear);
       comboBoxMonth.setSelectedIndex(DateUtil.thisMonth());

       this.add(comboBoxMonth);
       this.add(comboBoxYear);
       this.add(bSubmit);
        addListener();
    }

    public static void main(String[] args) {
        GuiUtil.showPanel(MonthPickerPanel.instance,0.3);
    }
    /**
     * @return 2017 - 今年的Integer数组
     */
    private Integer[] makeYears() {
        int thisYear = DateUtil.thisYear();
        Integer[] result = new Integer[thisYear - startYear + 1];
        for (int i = 0; i <= thisYear - startYear; i++) {
            result[i] = startYear + i;
        }
        return result;
    }

    /**
     * @return 1-12的Integer数组
     */
    private Integer[] makeMonths() {
        Integer[] result = new Integer[12];
        for (int i = 0; i < 12; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        bSubmit.addActionListener(new MonthPickerListener());
    }
}
