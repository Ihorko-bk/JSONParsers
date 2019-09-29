package name.ihorko.models.hard;

import java.util.Date;

public class MyValue {
    Date date;
    String whatDate;

    public MyValue() {
    }
    public MyValue(Date date, String whatDate) {
        this.date = date;
        this.whatDate = whatDate;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getWhatDate() {
        return whatDate;
    }
    public void setWhatDate(String whatDate) {
        this.whatDate = whatDate;
    }

    @Override
    public String toString() {
        return "MyValue{" +
                "date=" + date +
                ", whatDate='" + whatDate + '\'' +
                '}';
    }
}
