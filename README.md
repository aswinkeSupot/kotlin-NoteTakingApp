## 1. Add dependency for room database in app level build.gradle.kts
  Reference URL - https://developer.android.com/jetpack/androidx/releases/room

```
  dependencies {
     // Room Database
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
 }
```
## 2. Add kapt plugin in app level build.gradle.kts
```
  plugins {
    id("kotlin-kapt")
  }
```

## 3. Then Add below code in app level build.gradle.kts
```
  dependencies {
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
  }
```

## 4. Add Coroutines support for Room
  Reference URL - https://developer.android.com/kotlin/coroutines
```
  dependencies {
    // Add Coroutines dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
  }
```

## 5. Add Dependency for Navigation
  Add dependency for Navigation in app level build.gradle.kts
  Reference URL - https://developer.android.com/jetpack/androidx/releases/navigation#declaring_dependencies
  ```
  dependencies {
    // Navigation
    val nav_version = "2.7.7"
    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
  }
  ```

## 6. Add lifecycle depencency in app level build.gradle.kts
  Reference URL = https://developer.android.com/jetpack/androidx/releases/lifecycle
  ```
  dependencies {
    // Life Cycle Arch
    val lifecycle_version = "2.8.4"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
  }
```

## 7. Enable Databinding in app level build.gradle.kts
```
  android {
      buildFeatures{
         dataBinding = true
     }
  }
```

## 8. Add Safe Args in app level build.gradle.kts
  Reference URL = https://developer.android.com/jetpack/androidx/releases/navigation#declaring_dependencies
  ```
  plugins {
    id("androidx.navigation.safeargs")
  }
  ```

## 9. For Fixing the error - "Plugin [id: 'androidx.navigation.safeargs'] was not found in any of the following sources:"
  Add dependency for Navigation in project level build.gradle.kts
  ```
  buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val nav_version = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
  }
```

## 10. In project level build.gradle.kts add below code
  ```
  tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
  }
```

## 11. For creating Navigation Graph
```
  rec -> RTClick -> New -> Android Resource File
  Name - nav_graph , ResourceType - Navigation
```

## 12. For adding NavHosting in MainActivity xml file
```
  open activity_main -> In palette  click Containers
  -> NavHostFragment(Drag and drop to activity_main) select nav_graph
```

## 13. For create Fragment in nav_graph
```
  open nav_graph -> Click New Destination -> Create New Destination -> select Fragment Blank ->
  Give name to the Fragment "HomeFragment" -> Finish
```

## 14. Create a new entity class for Room Database
```
  create "Note" kotlin/dataClass in "model" package
```

## 15. Parcelize
  Reference URL = https://developer.android.com/kotlin/parcelize
  ```
  Add plugin in app level build.gradle.kts
  plugins {
    id("kotlin-parcelize")
  }
```





























