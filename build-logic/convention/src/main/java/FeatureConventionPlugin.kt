import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.location_notifier.build_logic.convention.core.ext.applyIfNotFind
import ru.je_dog.location_notifier.convention.core.ext.implementation
import ru.je_dog.location_notifier.convention.core.ext.implementationProject
import ru.je_dog.location_notifier.convention.core.ext.versionCatalog
import ru.je_dog.location_notifier.build_logic.convention.dependencies.DependenciesName
import ru.je_dog.location_notifier.convention.core.ext.kapt

class FeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        with(pluginManager){
            applyIfNotFind("je_dog.android.library")
            applyIfNotFind("je_dog.android.compose")
        }

        dependencies {

            implementationProject(":core")
            implementationProject(":core:feature")
            implementationProject(":core:data")
            implementationProject(":core:domain")

        }

    }
}