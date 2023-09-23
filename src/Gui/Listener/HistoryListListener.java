package PengTallyBook.Gui.Listener;

import PengTallyBook.Entity.Category;
import PengTallyBook.Entity.Record;
import PengTallyBook.Gui.Panel.HistoryListPanel;
import PengTallyBook.Gui.Panel.MainPanel;
import PengTallyBook.Gui.Panel.MonthPickerPanel;
import PengTallyBook.Gui.Panel.RecordPanel;
import PengTallyBook.Service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 只进行记录的删除操作，增加和修改交给RecordPanel
 */
public class HistoryListListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordService recordService = new RecordService();
        HistoryListPanel p = HistoryListPanel.instance;
        JButton button = (JButton)e.getSource();
        if(button == p.bAdd) {
            //交给RecordPanel处理
            //System.out.println("选择bAdd");
            MainPanel.instance.workingPanel.show(RecordPanel.instance);
            RecordPanel.instance.datePicker.setDate(MonthPickerPanel.instance.date);
        }

        if(button == p.bEdit) {
            if(!p.checkSelected()) {
                JOptionPane.showMessageDialog(p,"请先选择一行！");
                // return; 不要直接return
            }
            else {
                Record record = p.getSelectedREcord();
                //修改 updateId 设置
                RecordPanel.instance.updateId = record.getSerial_number();
                MainPanel.instance.workingPanel.show(RecordPanel.instance);
                //把旧数据读出来，写到 RecordPanel 待用户修改完直接更新入库即可
                RecordPanel.instance.tSpend.setText(String.valueOf(record.getAmount()));
                RecordPanel.instance.tDesc.setText(record.getDesc());
                //获取模型里的id而不是数据库的id，即帮用户选择分类
                RecordPanel.instance.categoryBox.setSelectedIndex(getModelId(record.getSign_number()));
                RecordPanel.instance.datePicker.setDate(record.getRecord_date());
            }
        }

        if(button == p.bDelete) {
            if(!p.checkSelected()) {
                JOptionPane.showMessageDialog(p,"请先选择一行！");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确实删除？")) {
                return;
            }
            recordService.delete(p.getSelectedREcord().getSerial_number());
            p.updateData();
        }
    }

    /**
     * @param cid 分类在数据库中的cid
     * @return 分类在 CategoryComboBoxModel 模型列表中的实际序数id
     */
    private int getModelId(int cid) {
        List<Category> categoryComBoxList = RecordPanel.instance.cbmodel.categories;
        for (int i = 0; i < categoryComBoxList.size(); i++) {
            if (categoryComBoxList.get(i).getSign_number() == cid) {
                return i;
            }
        }
        return 0; //若分类已经删除则返回第0个分类
    }
}
