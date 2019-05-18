package hello;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Greeting {

    private final long id;
    private final String content;
    private final String createdTime;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdTime = df.format(new Date());
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedTime(){
        return createdTime;
    }
}
