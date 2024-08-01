import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.2" // Spring Boot 애플리케이션의 빌드와 패키징을 지원
    id("io.spring.dependency-management") version "1.0.12.RELEASE" // Spring의 의존성 관리 플러그인
    kotlin("jvm") version "1.6.21" // Kotlin 언어를 사용하여 JVM 애플리케이션을 개발
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("kapt") version "1.6.21" // Kotlin에서 Java Annotation Processor를 사용하여 Annotation들을 사용
}

group = "com.toy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
val qeurydslVersion = "5.0.0"

// Maven Central Repository를 사용하여 의존성을 가져옴
repositories {
    mavenCentral()
}

// Gradle의 소스 세트 설정
sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // Kotlin을 지원하는 Jackson 모듈로, JSON 직렬화 및 역직렬화를 지원
    implementation("org.springframework.boot:spring-boot-starter-web") // Spring MVC 및 웹 애플리케이션 개발을 위한 스타터
    implementation("org.springframework.boot:spring-boot-starter-validation") // Bean Validation을 사용하기 위한 스타터

    kapt("org.springframework.boot:spring-boot-configuration-processor") // Spring Boot의 설정 메타데이터를 생성
    kapt("com.querydsl:querydsl-apt:$qeurydslVersion:jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:2.0.6.RELEASE")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict") // -Xjsr305=strict는 null 안전성을 강화
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

noArg {
    annotation("javax.persistence.Entity") // 어노테이션이 붙은 클래스에 대해 기본 생성자를 생성
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable") // JPA와 Kotlin의 호환성을 높입
}
