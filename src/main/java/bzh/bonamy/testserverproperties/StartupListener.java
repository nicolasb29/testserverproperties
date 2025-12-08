package bzh.bonamy.testserverproperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.server.autoconfigure.ServerProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Component
@ConditionalOnClass(ServerProperties.class)
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

    private final ServerProperties serverProperties;

    public StartupListener(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String contextPath = Optional.ofNullable(serverProperties.getServlet())
                .map(ServerProperties.Servlet::getContextPath)
                .orElse("/");

        log.info("Context path = {}", contextPath);
    }
}
