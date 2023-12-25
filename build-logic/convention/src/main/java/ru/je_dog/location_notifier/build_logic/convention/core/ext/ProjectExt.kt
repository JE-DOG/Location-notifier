package ru.je_dog.location_notifier.convention.core.ext

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun Project.versionCatalog(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named(
    VERSION_CATALOG_NAME
)

private const val VERSION_CATALOG_NAME = "libs"