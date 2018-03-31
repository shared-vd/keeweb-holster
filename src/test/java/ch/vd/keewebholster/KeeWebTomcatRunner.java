package ch.vd.keewebholster;

public class KeeWebTomcatRunner {

    public static void main(String ... args) {
        System.setProperty("kdbx.dir", "target/kdbx");
        System.setProperty("server.context-path", "/outils/keewebholster");
        System.setProperty("server.port", "12345");

        KeeWebApplication.main(args);
    }
}
