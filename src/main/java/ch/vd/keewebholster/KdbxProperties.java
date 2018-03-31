package ch.vd.keewebholster;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kdbx")
public class KdbxProperties {

    private String dir = "kdbxFiles";
    private String[] names = {"Secrets"};

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] name) {
        this.names = name;
    }
}
