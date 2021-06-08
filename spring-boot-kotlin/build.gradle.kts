buildscript {
    repositories {
//        google()
//        gradlePluginPortal()
        jcenter()
        maven {
            url = uri("https://maven.aliyun.com/nexus/content/groups/public/")
        }
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
        maven {
            url = uri("https://maven.aliyun.com/nexus/content/repositories/jcenter")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
    dependencies {
//        classpath("com.android.tools.build:gradle:4.1.2")
    }
}

plugins {
    java
    scala
    kotlin("jvm") version "1.4.32"
}

group = "com.perkins"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("http://maven.oschina.net/content/groups/public/") }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(kotlin("org.scala-lang:scala-library:2.12.2"))
    implementation(kotlin("org.scala-lang:scala-compiler:2.12.2"))
    implementation(kotlin("org.scala-lang:scala-reflect:2.12.2"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    test {
        testLogging.showStackTraces = true
    }
    val myCheck by registering {
        doLast {
            /* assert on something meaningful */
            println("myCheck task do last")
        }
    }
    check {
        dependsOn(myCheck)
    }
    register("myHelp") {
        doLast { /* do something helpful */
            println("myHelp do last")
        }
    }
}