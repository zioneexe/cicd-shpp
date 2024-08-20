plugins {
    id("java")
    id("io.freefair.lombok") version "8.7.1"
    id("jacoco")
    id("org.sonarqube") version "4.4.1.3373"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:2.1.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("ch.qos.logback:logback-classic:1.5.6")
}

tasks.shadowJar {
    archiveBaseName.set("prac2")
    archiveVersion.set("1.0")
    archiveClassifier.set("")

    manifest {
        attributes(mapOf("Main-Class" to "prac.shpp.App"))
    }
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
    dependsOn(tasks.shadowJar)
}

tasks.jar {
    enabled = false
}