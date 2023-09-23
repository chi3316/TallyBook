package PengTallyBook.Gui.Panel;

import PengTallyBook.Util.GuiUtil;

import java.awt.*;

public class HistoryPanel extends WorkingPanel{
    static {
        GuiUtil.UseLNF();
    }

    public static HistoryPanel instance = new HistoryPanel();

    private HistoryPanel() {
        this.setLayout(new BorderLayout());
        this.add(MonthPickerPanel.instance,BorderLayout.NORTH);
        this.add(HistoryListPanel.instance,BorderLayout.CENTER);
    }
    @Override
    public void updateData() {
        HistoryListPanel.instance.updateData();
    }

    public static void main(String[] args) {
        GuiUtil.showPanel(instance,0.85);
    }

    @Override
    public void addListener() {

    }
}