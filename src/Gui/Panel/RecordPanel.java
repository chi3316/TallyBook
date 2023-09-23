package PengTallyBook.Gui.Panel;

import PengTallyBook.Entity.Category;
import PengTallyBook.Entity.Record;
import PengTallyBook.Gui.Listener.RecordListener;
import PengTallyBook.Gui.Model.CategoryComboBoxModel;
import PengTallyBook.Service.CategoryService;
import PengTallyBook.Util.GuiUtil;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * 具体的异常位置是在 javax.swing.DefaultListCellRenderer.getListCellRendererComponent 方法内部，
 * 这是与 Swing 下拉列表框有关的代码。通常，这种情况发生在 Swing 组件渲染时，可能是由于组件的外观、模型或数据等方面出现了问题。
 */
public class RecordPanel extends WorkingPanel{
    static {
        GuiUtil.UseLNF();
    }
    public static RecordPanel instance;
    public int updateId = -1; //如果changeId>0则为修改模式，<0为模式的添加模式

    static {
        try {
            instance = new RecordPanel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //标签
    private JLabel lSpend =new JLabel("￥ 支出");
    private JLabel lCategory =new JLabel("分类");
    private JLabel lDescribe =new JLabel("备注");
    private JLabel lDate =new JLabel("日期");

    //文本域
    public JTextField tSpend = new JTextField();
    public JTextField tDesc = new JTextField();

    //category的数据 : 将数据cbmodel与组件categoryBox关联起来
    public CategoryComboBoxModel cbmodel = new CategoryComboBoxModel();
    public JComboBox<Category> categoryBox = new JComboBox<>(cbmodel);

    //日期组件
    public JXDatePicker datePicker = new JXDatePicker(new java.util.Date());

    //保存按钮
    private JButton bSave = new JButton("保存");

    public RecordPanel() throws SQLException, IOException {
        //设置标签，按钮颜色
        GuiUtil.setColor(Color.gray,lSpend,lDate,lCategory,lDescribe);
        GuiUtil.setColor(Color.BLACK,bSave);

        //两个小面板
        JPanel pInput = new JPanel();
        JPanel pSave = new JPanel();

        int gap = 30;
        pInput.setLayout(new GridLayout(4,2,gap,gap));
        pInput.add(lSpend);
        pInput.add(tSpend);
        pInput.add(lCategory);
        pInput.add(categoryBox);
        pInput.add(lDescribe);
        pInput.add(tDesc);
        pInput.add(lDate);
        pInput.add(datePicker);

        pSave.add(bSave);

        //给主面板设置布局
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSave,BorderLayout.CENTER);

        addListener();
    }

    public static void main(String[] args) {
        GuiUtil.showPanel(RecordPanel.instance,0.85);
    }

    public Category getSelectedItem() {
        return (Category) categoryBox.getSelectedItem();
    }

    @Override
    public void updateData() {
        try {
            cbmodel.categories = new CategoryService().list();
        } catch(Exception e) {
            e.printStackTrace();
        }

        categoryBox.updateUI();
        tSpend.setText("");
        tDesc.setText("");
        datePicker.setDate(new Date());
        tSpend.grabFocus();
    }

    @Override
    public void addListener() {
        bSave.addActionListener(new RecordListener());
    }
}
