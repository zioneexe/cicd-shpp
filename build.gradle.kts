plugins {
    id("java")
    id("io.freefair.lombok") version "8.7.1"
    id("jacoco")
    id("org.sonarqube") version "4.4.1.3373"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val mockitoCoreVersion = "2.1.0"
val mockitoInlineVersion = "5.2.0"
val mockitoJunitJupiterVersion = "5.12.0"
val logbackClassicVersion = "1.5+"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:$mockitoCoreVersion")
    testImplementation("org.mockito:mockito-inline:$mockitoInlineVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoJunitJupiterVersion")
    implementation("ch.qos.logback:logback-classic:$logbackClassicVersion")
}

tasks.register<Jar>("thinJar") {
    description = "Creates a thin jar."
    group = JavaBasePlugin.BUILD_NEEDED_TASK_NAME

    archiveClassifier.set("thin")

    manifest {
        attributes(
            "Main-Class" to "prac.shpp.App",
            "Class-Path" to "config/ " + configurations.runtimeClasspath.get().joinToString { "lib/" + it.name }
        )
    }

    from(sourceSets.main.get().output.classesDirs)

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.register<Zip>("thinJarZip") {
    description = "Packages everything into a zip archive with thin jar (dependencies, resources and properties outside)."
    group = JavaBasePlugin.COMPILE_CLASSPATH_PACKAGING_SYSTEM_PROPERTY

    dependsOn("thinJar")

    archiveFileName.set("thinJar.zip")
    destinationDirectory.set(layout.buildDirectory.dir("zip"))

    from(tasks.named<Jar>("thinJar").get().outputs.files.singleFile)

    from("src/main/resources") {
        into("config/")
    }

    from(configurations.runtimeClasspath) {
        into("lib/")
    }

    includeEmptyDirs = false
}

tasks.test {
    useJUnitPlatform()
    ignoreFailures = true
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.5".toBigDecimal()
            }
        }

        rule {
            isEnabled = false
            element = "CLASS"
            includes = listOf("org.gradle.*")

            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "0.3".toBigDecimal()
            }
        }
    }
}

sonar {
    properties {
        property("sonar.projectKey", "zioneexe_prac2")
        property("sonar.organization", "zioneexe")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.junit.reportPaths", "build/test-results/test")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}

tasks.build {
    dependsOn(tasks.named("thinJarZip"))
}

tasks.jar {
    enabled = false
}