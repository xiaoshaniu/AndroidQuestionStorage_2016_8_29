package data;

/**
 * Created by shijun on 2016/9/2.
 */
public class Choice {
    String title;
    String checke;

    public Choice() {
    }

    public Choice(String title, String checke) {
        this.title = title;
        this.checke = checke;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChecke() {
        return checke;
    }

    public void setChecke(String checke) {
        this.checke = checke;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "title='" + title + '\'' +
                ", checke='" + checke + '\'' +
                '}';
    }
}
