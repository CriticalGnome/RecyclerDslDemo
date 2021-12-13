# RecyclerDslDemo [![](https://jitpack.io/v/CriticalGnome/RecyclerDslDemo.svg)](https://jitpack.io/#CriticalGnome/RecyclerDslDemo)

Step 1. Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```
  
Step 2. Add the dependency
  ```groovy
  dependencies {
        //...
	    implementation 'com.github.CriticalGnome:RecyclerDslDemo:1.0'
	}
  ```

Step 3. Use it!
```kotlin
binding.recycler.adapter = recyclerViewAdapter(items) {
    define()
        .viewHolderOf(Item1Binding::inflate)
        .couldBeBoundTo<String> { string ->
            title.text = string
        }
    define()
        .viewHolderOf(Item2Binding::inflate)
        .couldBeBoundTo<String> { string ->
            body.text = string
        }
}
```
or
```kotlin
binding.recycler.adapter = recyclerViewAdapter {
    define()
        .viewHolderOf(Item1Binding::inflate)
        .couldBeBoundTo<String> { string ->
            title.text = string
        }
    define()
        .viewHolderOf(Item2Binding::inflate)
        .couldBeBoundTo<String> { string ->
            body.text = string
        }
}
binding.recycler.adapter?.model = items
```
