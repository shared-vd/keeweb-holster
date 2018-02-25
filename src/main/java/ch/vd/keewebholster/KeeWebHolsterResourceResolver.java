package ch.vd.keewebholster;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.TransformedResource;

import java.io.IOException;

public class KeeWebHolsterResourceResolver extends PathResourceResolver {

    public static final String FRONT_CONTROLLER = "index.html";
    public static final String FRONT_CONTROLLER_ENCODING = "UTF-8";

    private TransformedResource transformedResource(final Resource resource) throws IOException {
        String fileContent = IOUtils.toString(resource.getInputStream(), FRONT_CONTROLLER_ENCODING);
        fileContent = fileContent.replace("content=\"(no-config)\"", "content=\"/config.json\"");
        return new TransformedResource(resource, fileContent.getBytes());
    }

    @Override
    protected Resource getResource(final String resourcePath, final Resource location) throws IOException {
        final Resource resource = location.createRelative(resourcePath);
        if (resource.exists() && resource.isReadable()) {
            //if the asked resource is index.html, we serve it with the config rewritten
            if (resourcePath.contains(FRONT_CONTROLLER)) {
                return transformedResource(resource);
            }
            return resource;
        }
        return null;
    }
}
