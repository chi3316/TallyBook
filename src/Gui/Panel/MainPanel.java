package PengTallyBook.Gui.Panel;

import PengTallyBook.Gui.Listener.ToolBarListener;
import PengTallyBook.Util.CenterPanel;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 界面类 MainPanel 主界面
 * 主要有一个 Toolbar 的工具栏 和 一个  workingPanel 的 CenterPanel 的 工作区
 * workingPanel工作区内为本包中的各个页面，它们都是抽象类 WorkingPanel 的 子类
 * 各个页面有它们独自的监听器负责用户操作的监听
 */
//TODO : 完成备份和恢复面板
public class MainPanel extends JPanel {
    //静态代码块，加载面板的皮肤，统一外观
    static {
        GuiUtil.UseLNF();
    }
    public static MainPanel instance = new MainPanel();
    public CenterPanel workingPanel;
    private JToolBar tb = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bHistory = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public MainPanel() {
        GuiUtil.setImageIcon(bSpend, "home.png", "本月一览");
        GuiUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GuiUtil.setImageIcon(bHistory, "history.png", "历史消费");
        GuiUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GuiUtil.setImageIcon(bCategory, "category.png", "消费分类");
        GuiUtil.setImageIcon(bConfig, "config.png", "设置");
        GuiUtil.setImageIcon(bBackup, "backup.png", "备份");
        GuiUtil.setImageIcon(bRecover, "restore.png", "恢复");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bHistory);
        tb.add(bReport);
        tb.add(bCategory);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        //设置工具栏为不可浮动（non-floatable）的状态
        tb.setFloatable(false);
        workingPanel = new CenterPanel(0.85);

        this.setLayout(new BorderLayout());
        this.add(tb, BorderLayout.NORTH);
        this.add(workingPanel, BorderLayout.CENTER);

        addListeners();
    }

    public void addListeners() {
        ToolBarListener l = new ToolBarListener();
        bSpend.addActionListener(l);
        bHistory.addActionListener(l);
        bBackup.addActionListener(l);
        bCategory.addActionListener(l);
        bConfig.addActionListener(l);
        bRecord.addActionListener(l);
        bRecover.addActionListener(l);
        bReport.addActionListener(l);
    }
    public static void main(String[] args) {
        GuiUtil.showPanel(MainPanel.instance, 1);
    }
}
