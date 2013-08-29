package serverMonitoring.util.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Application Context Provider for common use
 * To load a certain bean, all you need to write is this :
 *   MyBean c = ApplicationContextProvider.getApplicationContext.getBean("BeanId", MyBean.class);
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    public ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        context = ctx;
    }
}
