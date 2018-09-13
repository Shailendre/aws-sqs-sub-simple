package info.lazycompiler.awssqssubtest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageObject {

    private int id;
    private String subject;
    private String from;
    private String to;
    private String body;


    public static MessageObject build() {
        return new MessageObject();
    }


    public MessageObject withId(int i) {
        setId(i);
        return this;
    }

    public MessageObject withSubject(String sub){
        setSubject(sub);
        return this;
    }

    public MessageObject withFrom(String from){
        setFrom(from);
        return this;
    }

    public MessageObject withTo(String to){
        setTo(to);
        return this;
    }

    public MessageObject withBody(String body){
        setBody(body);
        return this;
    }
}
