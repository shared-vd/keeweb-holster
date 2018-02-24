package ch.vd.keeweb;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class SinglePageAppConfig extends WebMvcConfigurerAdapter {

    private static final String PATH_PATTERNS = "/**";
    private static final String FRONT_CONTROLLER = "index.html";
    private static final String RESOURCES_LOCATION = "classpath:/static/";

    private final String baseHref;
    private final ResourceProperties resourceProperties;

    public SinglePageAppConfig(final ResourceProperties resourceProperties) {
        this.baseHref = "/";
        this.resourceProperties = resourceProperties;
    }

    @Bean
    public SpringBootRelativeRedirectsEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        return new SpringBootRelativeRedirectsEmbeddedServletContainerFactory();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH_PATTERNS)
                .addResourceLocations(RESOURCES_LOCATION)
                .setCachePeriod(resourceProperties.getCachePeriod())
                .resourceChain(true)
                .addResolver(new SinglePageAppResourceResolver());
    }

    private class SinglePageAppResourceResolver extends PathResourceResolver {
        @Override
        protected Resource getResource(final String resourcePath, final Resource location) throws IOException {
            Resource resource = location.createRelative(resourcePath);
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }

            //we just refreshed a page, no ?
            resource = location.createRelative(FRONT_CONTROLLER);
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
            return null;
        }
    }
}
