package ch.vd.keewebholster;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class KeeWebHolsterWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    public static final String PATH_PATTERNS = "/**";

    private final ResourceProperties resourceProperties;

    public KeeWebHolsterWebMvcConfigurerAdapter(final ResourceProperties resourceProperties) {
        this.resourceProperties = resourceProperties;
    }

    @Bean
    public SpringBootRelativeRedirectsEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        return new SpringBootRelativeRedirectsEmbeddedServletContainerFactory();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH_PATTERNS)
                .addResourceLocations(resourceProperties.getStaticLocations())
                //.setCachePeriod(resourceProperties.getCachePeriod())
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new KeeWebHolsterResourceResolver());
    }
}
