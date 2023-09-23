package PengTallyBook.Service;

import PengTallyBook.DAO.CategoryDAO;
import PengTallyBook.DAO.RecordDAO;
import PengTallyBook.Entity.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 对category的业务进行封装
 */
public class CategoryService {
    private RecordDAO recordDAO = new RecordDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    public void add(String name) throws SQLException, IOException {
        Category c = new Category();
        c.setName(name);
        categoryDAO.add(c);
    }

    public void update(int sign_number,String name)  {
        Category c = new Category();
        c.setSign_number(sign_number);
        c.setName(name);
        try {
            categoryDAO.update(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(int sign_number) throws SQLException, IOException {
        categoryDAO.delete(sign_number);
    }

    public List<Category> list() throws SQLException, IOException {
        List<Category> categories = categoryDAO.list();
        //把每一类记录的条数加到 Category对象的recordNumber字段中
        for(var x : categories) {
            var rs =  recordDAO.list(x.getSign_number());
            x.setRecordNumber(rs.size());
        }
        categories.sort((c1,c2) -> c2.getRecordNumber() - c1.getRecordNumber());
        return categories;
    }
}
