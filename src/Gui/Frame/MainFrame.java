package PengTallyBook.Gui.Frame;

import PengTallyBook.Gui.Panel.MainPanel;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;
/**
 * 程序主窗体
 * 设置了程序窗体的长宽标题和退出操作等
 */
public class MainFrame extends JFrame {
    static {
        GuiUtil.UseLNF();
    }

    public static MainFrame instance = new MainFrame();

    private MainFrame() {
        this.setTitle("TallyBook");
        this.setSize(513,450);

        /*
        将一个自定义的Swing面板（MainPanel的实例）设置为当前窗口（或容器）的内容面板，
        从而将该面板显示在窗口中，并允许在该面板上添加其他Swing组件以构建用户界面。
        内容面板是容器中用于承载其他Swing组件（如按钮、文本框、标签等）的主要区域。
        通过设置内容面板，可以将其他Swing组件添加到容器中，以构建用户界面。
         */
        this.setContentPane(MainPanel.instance);

        /*
        用于在窗口（通常是一个javax.swing.JFrame或javax.swing.JDialog）上设置位置的方法。
        它将窗口放置在屏幕中央。
         */
        this.setLocationRelativeTo(null);

        /*
        用于设置窗口是否可以改变大小的方法。
        将参数设置为 false 时，表示禁止用户通过拖动窗口边框来改变窗口的大小。
        如果设置为 true，则窗口允许用户改变大小。
         */
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    测试窗口
//    public static void main(String[] args) {
//        instance.setVisible(true);
//    }
}
