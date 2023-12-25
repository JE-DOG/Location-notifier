import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.location_notifier.build_logic.convention.dependencies.DependenciesName
import ru.je_dog.location_notifier.convention.core.ext.implementation
import ru.je_dog.location_notifier.convention.core.ext.implementationProject
import ru.je_dog.location_notifier.convention.core.ext.kapt
import ru.je_dog.location_notifier.convention.core.ext.versionCatalog

class DomainConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        with(pluginManager){
            apply("je_dog.kotlin.library")
        }

        dependencies {

            implementationProject(":core")
            implementationProject(":core:domain")

        }

    }
}