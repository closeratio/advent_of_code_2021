import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    }
}

plugins {
    java
    kotlin("jvm") version "1.6.0"
}

allprojects {

    apply(plugin = "kotlin")
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {

        if (project.name.startsWith("day_")) {
            implementation(project(":aoc_2021:common"))
        }

        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))

        testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
        testImplementation("org.hamcrest:hamcrest:2.2")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}


