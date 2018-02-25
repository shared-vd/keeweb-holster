package ch.vd.keewebholster;

public class KeeWebTomcatRunner {

    public static void main(String ... args) {
        System.setProperty("kdbx.dir", "target/kdbx");

        KeeWebApplication.main(args);
    }
}
