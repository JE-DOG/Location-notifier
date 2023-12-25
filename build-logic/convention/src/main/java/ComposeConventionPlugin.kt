import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.location_notifier.convention.core.ext.androidTestImplementation
import ru.je_dog.location_notifier.convention.core.ext.androidTestImplementationPlatform
import ru.je_dog.location_notifier.convention.core.ext.debugImplementation
import ru.je_dog.location_notifier.convention.core.ext.implementation
import ru.je_dog.location_notifier.convention.core.ext.implementationPlatform
import ru.je_dog.location_notifier.convention.core.ext.testImplementation
import ru.je_dog.location_notifier.convention.core.ext.versionCatalog
import ru.je_dog.location_notifier.build_logic.convention.dependencies.DependenciesName

class ComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        val libs = versionCatalog()

        dependencies {
            with(DependenciesName){
                with(libs) {

                    implementation(findLibrary(compose_navigation))

                    implementationPlatform(findLibrary(composeBom))

                    implementation(findLibrary(composeUi))
                    implementation(findLibrary(composeUiGraphics))
                    implementation(findLibrary(composeMaterial3))
                    implementation(findLibrary(composeMaterial))
                    implementation(findLibrary(androidxActivityCompose))
                    implementation(findLibrary(composeUiToolingPreview))

                    testImplementation(findLibrary(composeJunitUiTest))

                    debugImplementation(findLibrary(composeJunitUiTooling))
                    debugImplementation(findLibrary(composeJunitUiTestManifest))

                    androidTestImplementationPlatform(findLibrary(composeBom))

                    androidTestImplementation(findLibrary(composeJunitUiTestManifest))
                    androidTestImplementation(findLibrary(composeJunitUiTest))
                    androidTestImplementation(findLibrary(composeJunitUiTooling))

                }

            }
        }

    }

}