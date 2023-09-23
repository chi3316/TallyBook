package PengTallyBook.Util;

import PengTallyBook.Gui.Panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 工具类 CenterPanel 居中面板，实现了一个 可以有一个子面板 并且该子面板 居中 的面板
 */
public class CenterPanel extends JPanel {
    private double rate ;
    private JComponent c;
    private boolean stretch;

    public CenterPanel(double rate,boolean stretch) {
        this.rate = rate;
        this.stretch = stretch;
        //用于设置容器的布局管理器（Layout Manager）的方法，它决定了容器中组件的排列方式和布局规则。
        this.setLayout(null);
    }

    public CenterPanel(double rate) {
        this(rate,true);
    }

    /**
     * swing在更新界面的时候会调用这个方法
     */
    @Override
    public void repaint() {
        //只对本类的swing组件进行操作，其他不管
        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();
            //如果是拉伸
            if (stretch)
                c.setSize((int) (containerSize.width * rate),
                        (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);
            //重置位置使其居中
            c.setLocation(containerSize.width / 2 - c.getSize().width / 2,
                    containerSize.height / 2 - c.getSize().height / 2);
        }
        //调用父方法更新
        super.repaint();
    }

    /**
     * 新建一个CenterPanel后可以用show方法，就可以将 一个子面板 进行居中显示
     * @param p 待居中的子面板
     */
    public void show(JComponent p) {
        this.c = p;
        //获取当前面板的所有子面板
        Component[] cs = getComponents();
        //全部清除
        for (Component c : cs) {
            remove(c);
        }
        //加进来一个子面板
        add(p);
        //如果是一个实现了 WorkingPanel 的子类，会执行它的 updateData() 方法
        if ((p instanceof WorkingPanel)) {
            ((WorkingPanel) p).updateData();
        }
        //居中处理
        this.updateUI();
    }

    //测试代码
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(500,500);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CenterPanel centerPanel = new CenterPanel(0.85);
        jf.setContentPane(centerPanel);
        jf.setVisible(true);
        JButton jButton = new JButton("看不懂");
        //centerPanel.show(centerPanel);
        // Exception in thread "main" java.lang.IllegalArgumentException: adding container's parent to itself
        centerPanel.show(jButton);
    }
}
