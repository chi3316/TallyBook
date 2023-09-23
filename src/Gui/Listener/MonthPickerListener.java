package PengTallyBook.Gui.Listener;

import PengTallyBook.Gui.Panel.HistoryListPanel;
import PengTallyBook.Gui.Panel.MonthPickerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MonthPickerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MonthPickerPanel p = MonthPickerPanel.instance;
        Integer month = (Integer)p.comboBoxMonth.getSelectedItem();
        Integer year = (Integer)p.comboBoxYear.getSelectedItem();
        //用SimpleDateFormat获取所选月月初的 Date
        try {
            p.date = new SimpleDateFormat("yyyy-MM").parse(String.format("%4d-%02d", year, month));
            //更新 HistoryListPanel ,重新获取数据
            HistoryListPanel.instance.updateData();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }
}
