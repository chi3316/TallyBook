package PengTallyBook.Gui.Panel;

import PengTallyBook.Gui.Listener.ConfigListener;
import PengTallyBook.Service.ConfigService;
import PengTallyBook.Util.ColorUtil;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel{
    static {
        GuiUtil.UseLNF();
    }
    public static ConfigPanel instance = new ConfigPanel();
    public JLabel lBudget = new JLabel("本月预算");
    public JTextField tfBudget = new JTextField();
    public JButton bTruncate = new JButton("重置数据库");
    public JButton bSubmit = new JButton("更新");

    private ConfigPanel() {
        GuiUtil.setColor(ColorUtil.grayColor, lBudget);
        GuiUtil.setColor(ColorUtil.blueColor, bSubmit);

        JPanel pNorth = new JPanel();
        int gap =40;
        pNorth.setLayout(new GridLayout(3,1,gap,gap));
        pNorth.add(lBudget);
        pNorth.add(tfBudget);
        pNorth.add(bSubmit);

        JPanel pSouth = new JPanel();
        pSouth.add(bTruncate);

        this.setLayout(new BorderLayout());
        this.add(pNorth,BorderLayout.NORTH);
        this.add(pSouth,BorderLayout.SOUTH);

        addListener();
    }
    @Override
    public void updateData() {
        ConfigService service = new ConfigService();
        tfBudget.setText(service.DEFAULT_BUDGET);
        tfBudget.grabFocus();
    }

    @Override
    public void addListener() {
        ConfigListener listener = new ConfigListener();
        bSubmit.addActionListener(listener);
        bTruncate.addActionListener(listener);
    }

    public static void main(String[] args) {
        GuiUtil.showPanel(instance,0.85);
    }
}
