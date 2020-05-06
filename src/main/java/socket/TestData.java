package socket;

import java.io.Serializable;

public class TestData implements Serializable {
    String text;

    public TestData(String text){
        this.text = text;
    }
}
