package phoenix;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.telegram.telegrambots.ApiContextInitializer;
import phoenix.config.Config;
import phoenix.config.SecurityConfig;

import javax.servlet.*;
import java.util.EnumSet;

public class WebApplication implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ApiContextInitializer.init();
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(Config.class);
        ac.register(SecurityConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("ROOT", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        servletContext.addListener(new ContextLoaderListener(ac));

    }
}
