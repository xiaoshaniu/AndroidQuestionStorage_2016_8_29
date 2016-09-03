package data;

import java.io.Serializable;

/**
 * Created by shijun on 2016/9/1.
 */
public class Question implements Serializable {
    String content;
    int id;
    int pubTime;
    int typeid;
    String answer;
    String options;

    public Question() {
    }

    public Question(String content, int id, int pubTime, int typeid, String options, String answer) {
        this.content = content;
        this.id = id;
        this.pubTime = pubTime;
        this.typeid = typeid;
        this.options = options;
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPubTime() {
        return pubTime;
    }

    public void setPubTime(int pubTime) {
        this.pubTime = pubTime;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", pubTime='" + pubTime + '\'' +
                ", typeid=" + typeid +
                ", answer='" + answer + '\'' +
                ", options='" + options + '\'' +
                '}';
    }
}
