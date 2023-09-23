package PengTallyBook.Entity;

public class Category {
    private String name;
    private int sign_number;
    private int recordNumber;

    public Category(String name) {
        this.name = name;
    }
    public Category(int sign_number) {
        this.sign_number = sign_number;
    }
    public Category() {}
    public Category(String name,int sign_number) {
        this.name = name;
        this.sign_number = sign_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSign_number() {
        return sign_number;
    }

    public void setSign_number(int sign_number) {
        this.sign_number = sign_number;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
