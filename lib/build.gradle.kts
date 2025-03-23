plugins {
    id("org.springframework.boot") apply false
}

dependencies {
    implementation("org.springframework.security:spring-security-crypto")

    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
