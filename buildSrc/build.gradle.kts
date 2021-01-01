plugins {
  kotlin("jvm") version "1.4.10"
  `java-gradle-plugin`
}

repositories {
  jcenter()
  maven(
    url = uri("https://jitpack.io")
  )
}

dependencies {
  implementation("com.github.dropbox:AffectedModuleDetector:3023ee6c432011a3bb6701a4647d926d329a7b72")
}
