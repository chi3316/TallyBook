package PengTallyBook.StartUp;

import PengTallyBook.Gui.Frame.MainFrame;
import PengTallyBook.Gui.Panel.MainPanel;
import PengTallyBook.Gui.Panel.SpendPanel;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;

public class Start {
    public static void main(String[] args) throws Exception {
        GuiUtil.UseLNF();
        SwingUtilities.invokeAndWait(() -> {
            MainFrame.instance.setVisible(true);
            //居中显示子面板并更新数据
            MainPanel.instance.workingPanel.show(SpendPanel.instance);
        });
    }
}
