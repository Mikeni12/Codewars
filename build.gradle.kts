plugins {
    kotlin("jvm") version "1.6.10"
}

repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("test"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks {
    sourceSets {
        main {
            java.srcDir("src")
        }
    }
}
