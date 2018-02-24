package ch.vd.keewebholster;

import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

/**
 * Cette classe permet de post-configurer le Embedded Tomcat dans le cas de spring boot
 * Spring Boot met inconditionnellement le flag useRelativeRedirects à false
 * Ce flag permet de faire des redirect HTTP en relatif (/mycontext au lieu de http://localhost:1234/mycontext)
 * Les relative redirect sont non supporté par HTTP/1.0 mais on est en HTTP/1.1 maintenant
 * <p>
 * Si on ne met pas ce flag, Tomcat va faire des redirect sur http://localhost:1234/mycontext au lieu de
 * https://prestations.vd.ch/mycontext ce qui est faux
 * <p>
 * Avec le relative redirects, il va faire des redirect sur /mycontext
 */
public class SpringBootRelativeRedirectsEmbeddedServletContainerFactory extends TomcatEmbeddedServletContainerFactory {

    @Override
    protected void postProcessContext(Context context) {
        super.postProcessContext(context);

        logger.info("Configuration de tomcat pour utilisation de relative redirects. (useRelativeRedirects=true)");
        context.setUseRelativeRedirects(true);
    }
}
