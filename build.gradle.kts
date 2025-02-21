plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.example"
version = "0.0.1"

//application {
//    mainClass.set("io.ktor.server.netty.EngineMain")
//
//    val isDevelopment: Boolean = project.ext.has("development")
//    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
//}
application {
    mainClass.set("com.example.ApplicationKt") // Replace with your actual main class
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.example.ApplicationKt"
    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("com.zaxxer:HikariCP:5.1.0") // Use the latest version
    implementation("org.jetbrains.exposed:exposed-core:0.43.0") // Exposed ORM (optional)
    implementation("org.jetbrains.exposed:exposed-dao:0.43.0")  // Exposed DAO (optional)
    implementation("org.jetbrains.exposed:exposed-jdbc:0.43.0") // Exposed JDBC (optional)
    implementation("org.postgresql:postgresql:42.7.2") // Example for PostgreSQL

    implementation("io.ktor:ktor-serialization-jackson:2.3.5") // Use the latest version

    implementation("org.jetbrains.exposed:exposed-java-time:0.43.0") // Use the latest version


    implementation("io.ktor:ktor-server-auth:2.3.5") // Ktor Authentication module
    implementation("io.ktor:ktor-server-auth-jwt:2.3.5") // Ktor JWT module
    implementation("com.auth0:java-jwt:4.4.0") // JWT library


}
