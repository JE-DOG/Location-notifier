import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.model.Kapt
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import ru.je_dog.location_notifier.build_logic.convention.core.ext.applyIfNotFind
import ru.je_dog.location_notifier.build_logic.convention.dependencies.DependenciesName
import ru.je_dog.location_notifier.convention.core.ext.implementation
import ru.je_dog.location_notifier.convention.core.ext.implementationProject
import ru.je_dog.location_notifier.convention.core.ext.kapt
import ru.je_dog.location_notifier.convention.core.ext.versionCatalog

class DataConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        val libs = versionCatalog()

        extensions.findByType(KaptExtension::class)?.apply{
            arguments {
                arg(
                    "room.schemaLocation",
                    "$projectDir/schemas"
                )
            }
        }

        with(pluginManager){

            applyIfNotFind("je_dog.android.library")

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