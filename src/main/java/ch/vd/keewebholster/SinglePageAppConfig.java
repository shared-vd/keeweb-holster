package ch.vd.keewebholster;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.TransformedResource;

import java.io.IOException;

@Configuration
public class SinglePageAppConfig extends WebMvcConfigurerAdapter {

    private static final String PATH_PATTERNS = "/**";
    private static final String FRONT_CONTROLLER = "index.html";
    private static final String FRONT_CONTROLLER_ENCODING = "UTF-8";

    private final ResourceProperties resourceProperties;

    public SinglePageAppConfig(final ResourceProperties resourceProperties) {
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
                .setCachePeriod(resourceProperties.getCachePeriod())
                .resourceChain(true)
                .addResolver(new SinglePageAppResourceResolver());
    }

    private class SinglePageAppResourceResolver extends PathResourceResolver {

        private TransformedResource transformedResource(final Resource resource) throws IOException {
            String fileContent = IOUtils.toString(resource.getInputStream(), FRONT_CONTROLLER_ENCODING);
            fileContent = fileContent.replace("content=\"(no-config)\"", "content=\"/config.json\"");

            return new TransformedResource(resource, fileContent.getBytes());
        }

        @Override
        protected Resource getResource(final String resourcePath, final Resource location) throws IOException {
            {
                final Resource resource = location.createRelative(resourcePath);
                if (resource.exists() && resource.isReadable()) {
                    //if the asked resource is index.html, we serve it with the config rewritten
                    if (resourcePath.contains(FRONT_CONTROLLER)) {
                        return transformedResource(resource);
                    }
                    return resource;
                }
            }
            {
                //we just refreshed a page, no ?
                final Resource resource = location.createRelative(FRONT_CONTROLLER);
                if (resource.exists() && resource.isReadable()) {
                    return transformedResource(resource);
                }
            }
            return null;
        }
    }
}
