package PengTallyBook.Gui.Panel;

import javax.swing.*;

/**
 * 公共抽象父类，子类面板都需要实现两个方法：
 * updateData()来更新数据
 * addListener()
 */
public abstract class WorkingPanel extends JPanel {
    public abstract void updateData();
    public abstract void addListener();
}
