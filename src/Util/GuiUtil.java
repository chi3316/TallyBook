package PengTallyBook.Util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GuiUtil {
    //放图片的文件夹地址
    private static final String imgFolder = "resources/imgs/";

    //定义皮肤

    /**
     * 使用Java的Swing用户界面（UI）工具包中的UIManager类来设置应用程序的外观和感觉（Look and Feel）
     * 具体来说，它将外观和感觉设置为com.pagosoft.plaf.PgsLookAndFeel，这是一个自定义的界面外观类。
     */
    public static void UseLNF() {
        try{
            javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**测试的时候调用，直接把面板放进去拉起一个窗体
     * @param p 待测试面板
     * @param stretch 拉伸比例
     */
    public static void showPanel(JPanel p,double stretch) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(500,500);
        jFrame.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(stretch);
        jFrame.setContentPane(cp);
        jFrame.add(p);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        cp.show(p);
    }

    public static void setColor(Color color,JComponent... cs) {
        for(JComponent c : cs) {
            c.setForeground(color);
        }
    }

    public static boolean checkNumber(JTextField textField,String name) {
        //去掉文本域传进来的空格
        String text = textField.getText().trim();
        if(text.length() <= 0) {
            JOptionPane.showMessageDialog(textField,"输入有误，"+ name + "不能为空");
            textField.grabFocus();
            return false;
        }
        //看输入的金额是否合法
        try{
            Integer.parseInt(text);
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(textField,"输入有误，" + name + "不是数字");
            return false;
        }
        return true;
    }
    /**
     * 把一个按钮设置成图文形式
     *
     * @param b        按钮
     * @param fileName 图片名
     * @param tip      按钮下文字
     */
    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon((new File(imgFolder, fileName)).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }
}
