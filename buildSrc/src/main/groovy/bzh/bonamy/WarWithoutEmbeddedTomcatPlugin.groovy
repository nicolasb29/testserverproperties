package bzh.bonamy

import org.gradle.api.Plugin
import org.gradle.api.Project

class WarWithoutEmbeddedTomcatPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        def excludeEmbeddedTomcat = { cp ->
            cp.findAll { !it.name.startsWith('tomcat-embed-') }
        }

        project.tasks.matching { it.name == 'war' }.configureEach {
            classpath(excludeEmbeddedTomcat(project.configurations.providedRuntime))
        }

        project.tasks.matching { it.name == 'bootWar' }.configureEach {
            classpath(excludeEmbeddedTomcat(project.configurations.providedRuntime))
        }
    }
}
