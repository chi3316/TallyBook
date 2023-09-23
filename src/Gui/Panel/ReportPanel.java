package PengTallyBook.Gui.Panel;

import PengTallyBook.Service.ReportService;
import PengTallyBook.Util.ChartUtil;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends WorkingPanel{
    static {
        GuiUtil.UseLNF();
    }
    public static ReportPanel instance = new ReportPanel();
    public JLabel l = new JLabel();
    public ReportPanel() {
        this.add(l);
    }
    public static void main(String[] args) {
        GuiUtil.showPanel(ReportPanel.instance,0.85);
    }
    @Override
    public void updateData() {
        Image i = ChartUtil.getImage(new ReportService().listThisMonthRecords(), 400, 260);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
