import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.location_notifier.build_logic.convention.dependencies.DependenciesName
import ru.je_dog.location_notifier.convention.core.ext.implementation
import ru.je_dog.location_notifier.convention.core.ext.kapt
import ru.je_dog.location_notifier.convention.core.ext.versionCatalog

class KotlinLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        val libs = versionCatalog()

        with(pluginManager){

            apply("org.jetbrains.kotlin.jvm")
            apply("org.jetbrains.kotlin.kapt")

        }

        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        dependencies {

            with(DependenciesName){
                with(libs){

                    //Dagger
                    implementation(findLibrary(dagger_core))
                    kapt(findLibrary(dagger_compiler))

                }
            }

        }

    }
}