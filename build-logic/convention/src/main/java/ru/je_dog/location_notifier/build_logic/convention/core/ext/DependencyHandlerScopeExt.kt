package ru.je_dog.location_notifier.convention.core.ext

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project
import ru.je_dog.location_notifier.build_logic.convention.dependencies.DependenciesConfiguration
import java.util.Optional

fun DependencyHandlerScope.implementation(library: Optional<Provider<MinimalExternalModuleDependency>>) = add(
    DependenciesConfiguration.implementation,library.get())

fun DependencyHandlerScope.implementationPlatform(platform: Optional<Provider<MinimalExternalModuleDependency>>) = add(
    DependenciesConfiguration.implementation,platform(platform.get()))

fun DependencyHandlerScope.testImplementation(library: Optional<Provider<MinimalExternalModuleDependency>>) = add(
    DependenciesConfiguration.testImplementation,library.get())

fun DependencyHandlerScope.androidTestImplementation(library: Optional<Provider<MinimalExternalModuleDependency>>) = add(
    DependenciesConfiguration.androidTestImplementation,library.get())

fun DependencyHandlerScope.androidTestImplementationPlatform(platform: Optional<Provider<MinimalExternalModuleDependency>>) = add(
    DependenciesConfiguration.androidTestImplementation,platform( platform.get() ))

fun DependencyHandlerScope.debugImplementation(library: Optional<Provider<MinimalExternalModuleDependency>>) = add(
    DependenciesConfiguration.debugImplementation,library.get())

fun DependencyHandlerScope.implementationProject(project: String) = add(DependenciesConfiguration.implementation, project(path = project) )

fun DependencyHandlerScope.kapt(library: Optional<Provider<MinimalExternalModuleDependency>>) =
    add(DependenciesConfiguration.kapt, library.get())

