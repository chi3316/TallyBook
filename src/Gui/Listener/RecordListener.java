package PengTallyBook.Gui.Listener;

import PengTallyBook.Entity.Category;
import PengTallyBook.Gui.Panel.HistoryListPanel;
import PengTallyBook.Gui.Panel.MainPanel;
import PengTallyBook.Gui.Panel.RecordPanel;
import PengTallyBook.Gui.Panel.SpendPanel;
import PengTallyBook.Service.RecordService;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p = RecordPanel.instance;
        if(p.cbmodel.categories.size() == 0) {
            JOptionPane.showMessageDialog(p,"请先添加分类");
            return;
        }
        String spend = p.tSpend.getText();
        if(!GuiUtil.checkNumber(p.tSpend,"输入的金额")) {
            return;
        }
        if(spend.equals("0")) {
            JOptionPane.showMessageDialog(p,"输入的金额不能为0");
            return;
        }
        Category c = p.getSelectedItem();
        String desc = p.tDesc.getText();
        Date date = p.datePicker.getDate();
        //根据 updateId 确定模式
        if (p.updateId < 0) { //默认的添加模式
            try {
                new RecordService().add(Integer.parseInt(spend), c.getSign_number(),date, desc);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(p, "添加成功");
            MainPanel.instance.workingPanel.show(SpendPanel.instance);
        } else { //修改模式
            new RecordService().update(p.updateId, Integer.parseInt(spend), date, desc,c.getSign_number());
            JOptionPane.showMessageDialog(p, "修改成功");
            // TODO : MainPanel.instance.workingPanel.show(HistoryPanel.instance);
            //重置p.updateId
            p.updateId = -1;
            //刷新 HistoryListPanel
           HistoryListPanel.instance.updateData();
        }
    }
}
