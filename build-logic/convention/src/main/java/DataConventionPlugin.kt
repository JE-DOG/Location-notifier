import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.location_notifier.build_logic.convention.dependencies.DependenciesName
import ru.je_dog.location_notifier.convention.core.ext.implementation
import ru.je_dog.location_notifier.convention.core.ext.implementationProject
import ru.je_dog.location_notifier.convention.core.ext.kapt
import ru.je_dog.location_notifier.convention.core.ext.versionCatalog

class DataConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        val libs = versionCatalog()

        with(pluginManager){

            apply("je_dog.android.library")

        }

        dependencies {

            with(DependenciesName){
                with(libs){

                    //Room
                    implementation(findLibrary(room))
                    implementation(findLibrary(room_ktx))
                    kapt(findLibrary(room_compiler))

                }
            }

            implementationProject(":core")
            implementationProject(":core:data")
            implementationProject(":core:domain")

        }

    }
}