import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get

class MavenLocalPublishingPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.afterEvaluate {
            extensions.configure<PublishingExtension>("publishing") {
                publications {
                    create<MavenPublication>("release") {
                        // Utilizing components.release requires configuring software components
                        from(components["release"])

                        groupId = "com.gini_logger"
                        artifactId = "core"
                        version = "1.0.7"
                    }

                    repositories {
                        mavenLocal()
                    }
                }
            }
        }
    }
}