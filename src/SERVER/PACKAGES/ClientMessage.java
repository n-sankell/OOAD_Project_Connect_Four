package SERVER.PACKAGES;

import java.io.Serializable;

public class ClientMessage implements Serializable {

    private final String message;

    public ClientMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
