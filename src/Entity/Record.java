package PengTallyBook.Entity;

import java.util.Date;
public class Record {
    private int sign_number;
    private double amount;
    private Date record_date;
    private String desc;
    private int serial_number;

    public Record() {}
    //添加记录的时候哪些是需要传入参数？哪些自动生成的？
    public Record(int sign_number, double amount, Date record_date, String desc,int serial_number) {
        this.sign_number = sign_number;
        this.amount = amount;
        this.record_date = record_date;
        this.desc = desc;
        this.serial_number = serial_number;
    }

    public int getSign_number() {
        return sign_number;
    }

    public double getAmount() {
        return amount;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public String getDesc() {
        return desc;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSign_number(int sign_number) {
        this.sign_number = sign_number;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }
}