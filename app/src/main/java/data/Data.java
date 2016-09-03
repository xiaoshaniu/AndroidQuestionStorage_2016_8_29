package data;

/**
 * Created by shijun on 2016/8/31.
 */
public class Data {
    int id;
    String name;
    String icon;

    public Data() {
    }

    public Data(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
