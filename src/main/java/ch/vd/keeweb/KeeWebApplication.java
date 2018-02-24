package ch.vd.keeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        SinglePageAppConfig.class
})
public class KeeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeeWebApplication.class, args);
    }
}
