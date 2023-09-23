package PengTallyBook.Gui.Listener;

import PengTallyBook.Gui.Panel.ConfigPanel;
import PengTallyBook.Service.ConfigService;
import PengTallyBook.Util.GuiUtil;
import PengTallyBook.Util.SQLUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        JButton button = (JButton) e.getSource();
        if(p.bSubmit == button) {
            if (!GuiUtil.checkNumber(p.tfBudget, "本月预算")) {
                return;
            }
            ConfigService service = new ConfigService();
            //改操作
            service.update(ConfigService.BUDGET, p.tfBudget.getText());
            JOptionPane.showMessageDialog(p, "设置成功");
        }

        if (p.bTruncate == button) {
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确实清空所有数据？")) {
                return;
            }
            SQLUtil.truncate();
            //重新初始化数据库
            ConfigService.init();
            JOptionPane.showMessageDialog(p, "重置成功");
        }
    }
}
