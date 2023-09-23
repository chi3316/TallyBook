package PengTallyBook.Service;

import PengTallyBook.DAO.ConfigDAO;
import PengTallyBook.Entity.Config;

public class ConfigService {
    public static final String BUDGET = "budget";
    public static final String DEFAULT_BUDGET = "500";
    private static ConfigDAO configDAO = new ConfigDAO();

    static {
        init();
    }

    public static void init(String key,String value) {
        Config config = configDAO.getByKey(key);
        if(config == null) {
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            configDAO.add(c);
        }
    }

    public static void init() {
        init(BUDGET,DEFAULT_BUDGET);
    }

    public String get(String key) {
        return configDAO.getByKey(key).getValue();
    }

    public void update(String key,String value) {
        Config config = configDAO.getByKey(key);
        config.setValue(value);
        configDAO.update(config);
    }

    public int getIntBudget() {
        return Integer.parseInt(get(BUDGET));
    }
}
