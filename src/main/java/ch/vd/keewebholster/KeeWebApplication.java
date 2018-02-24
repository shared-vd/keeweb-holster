package ch.vd.keewebholster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        KdbxProperties.class,
        SinglePageAppConfig.class
})
public class KeeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeeWebApplication.class, args);
    }
}
