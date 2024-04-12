package fr.hetic;

import java.util.Objects;

public class ProcessorStrategy {
    public String TYPE;
    public String USER_DATABASE = null;
    public String HOST_DATABASE = null;
    public String PASSWORD_DATABASE = null;
    public String DIRECTORY = null;

    public ProcessorStrategy(String[] args) {
        String type = args[0];
        switch (type) {
            case "JDBC":
                this.TYPE = "JDBC";
                this.HOST_DATABASE = args[1];
                this.USER_DATABASE = args[2];
                this.PASSWORD_DATABASE = args[3];
                System.out.println("Hello" + USER_DATABASE + HOST_DATABASE + PASSWORD_DATABASE);
                break;
            case "FILE":
                this.TYPE = "FILE";
                this.DIRECTORY = args[1];
                break;
        }
    }

}
