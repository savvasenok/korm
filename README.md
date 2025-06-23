[![](https://www.jitpack.io/v/savvasenok/korm.svg)](https://www.jitpack.io/#savvasenok/korm)  [![](https://jitpack.io/v/savvasenok/korm/month.svg)](https://jitpack.io/#savvasenok/korm)

# Korm: Kotlin + Form
Android library with a suite of Material input elements to support screens with a lot of editing fields:
  + Text input
  + Number input
  + Checkboxes
  + Dropdown pickers

Currently library supports following:
  + Error handling
  + Outline and non-outlined input fields

# Example
Dark theme             |  Light theme
:-------------------------:|:-------------------------:
![image](https://github.com/user-attachments/assets/465fe2eb-2d26-404f-ae49-4b399f84a207)  |  ![image](https://github.com/user-attachments/assets/6dde5c62-645f-4ac4-b715-97b1f7282fd2)

# How to use
### Step 1: Add dependency

``` gradle
// settings.gradle.kts
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // other repos
        maven("https://www.jitpack.io")
    }
}
```

``` gradle
// your module gradle.kts file
implementation("com.github.savvasenok:korm:<version>")
```
Now you can use Korm models and UI in your app

### Step 2: Using built-in components
There are currently 2 ready-to-use classes to simplify data- and error-handling:
  1. `KormFieldBuilder` — use this one to build a list of `KormFieldModel`s
  2. `DefaultKormFieldUpdater` — implementation of `KormFieldUpdater`. Actually handles how user's input is processed

#### Step 2.1: Feeding the `KormFieldBuilder`
I suppose we have the following `ViewModel`. For the simplicity I implement the initialization in `init` block, but in your case it can also be after loading field-data from your API:
``` kotlin
class ExampleViewModel : ViewModel() {
    private val kormFieldBuilder = KormFieldBuilder()

    init {
        // Paste following code-sections here
    }
}
```

If your list of fields is mutable (e.g. loading from cache and then from API), please invoke the `KormFieldBuilder#clear` method to clear the previous setup. Can be omitted in this example
``` Kotlin
kormFieldBuilder.clear()
```

Use `.addTextField` to add text input field:
``` kotlin
kormFieldBuilder.addTextField(
  id = "user/name", // Unique string identifier
  value = "John",   // Initial text value. Pass an empty if no input
  enabled = true,   // Optional. Pass "false" to make field uneditable
  label = "Name",   // Optional. Refer to the Material design guidelines
  placeholder = "Enter your name", // Optional. Refer to the Material design guidelines
)
```

Use `.addNumberField` to add number input field:
``` kotlin
kormFieldBuilder.addNumberField(
  id = "user/age",  // Unique string identifier
  value = 42.24,    // Initial value. Pass "null" if no input
  enabled = true,   // Optional. Pass "false" to make field uneditable
  label = "Age",    // Optional. Refer to the Material design guidelines
  placeholder = "Enter you age", // Optional. Refer to the Material design guidelines
)
```

Use `.addDropdownField` to add number input field:
``` kotlin
val genders = listOf("Male", "Female", "Other")
kormFieldBuilder.addNumberField(
  id = "user/gender",   // Unique string identifier
  index = null,         // Int index of selected option. Pass "null" if not defined
  options = genders     // List of String options for user to choose
  enabled = true,       // Optional. Pass "false" to make field uneditable
  label = "Gender",     // Optional. Refer to the Material design guidelines
  placeholder = "Enter your gender", // Optional. Refer to the Material design guidelines
)
```

Use `.addCheckBoxField` to add checkbox:
``` kotlin
kormFieldBuilder.addNumberField(
  id = "using-korm",  // Unique string identifier
  description = "Do you like Korm?" // String description of this checkbox
  isChecked = true,   // Initial value
  enabled = true,     // Optional. Pass "false" to make field uneditable
)
```

#### Step 2.2: Display fields
Currently `kormFieldBuilder` holds all the data you provided, but nothing yet is provided to the UI. To make it work, we have to use `KormFieldUpdater`. Let's update our `ExampleViewModel`:
``` kotlin
class ExampleViewModel : ViewModel(), KormFieldUpdater /* <-- NEW */ {

    private val kormFieldBuilder = KormFieldBuilder()

    // NEW
    private val kormFieldUpdater = DefaultKormFieldUpdater(/* TODO: add error handler */)

    // NEW
    val viewState: StateFlow<List<KormFieldModel>> = kormFieldUpdater.uiFlow

    init {
        // Code from step 2.1

        // NEW
        val fields = kormFieldBuilder.getFields()
        kormFieldUpdater.setFields(fields) // Updater now is ready to display fields
    }
}
```

We have added new field `kormFieldUpdate: DefaultKormFieldUpdater`. This class has few methods to update provided fields: `update`/`updateText`/`updateNumber`. All of these methods require you to profide the `KormFieldId`, but you get them from `KormFieldBuilder`, so you don't have to worry about creating them. Of course, you also pass the value for update. All theme `update*` methods are implementations of `KormFieldUpdater` interface

You can also see, that our `ExampleViewModel` also implements `KormFieldUpdater`. In current example we have to manually implement each method and pass all the paramt to the `kormFieldBuilder`. You can utilize Kotlin's delegates and `by` keyword, but this topic is outside of the scope of this tutorial. When your viewmodel proxies these methods, it is easier to provide them to UI components later in this tutorial

That's all! Our `ExampleViewModel` is ready to accept input and return proper view state

### Step 3: UI
There is generic Composable called `KormFieldUi`, which is responsible for showing the proper input field and passing the updates to the ViewModel via callbacks. Long story short, here is the contents of your composable screen:
``` kotlin
for (item in viewState) {
    KormFieldUi(
        model = item,
        onCheckedChange = viewModel::update,
        onSelect = viewModel::update,
        onTextChange = viewModel::updateText,
        onNumberChange = viewModel::updateNumber
    )
}
```

TODO: How to get data from `DefaultKormFieldUpdater`
      Error handling
    
# What's coming next?
  + More default input fields: date, time, sliders etc
  + Support for custom pickers. E.g. you call other component to let user select complex data: location on map, phone number etc
  + Brand UI. If you have established Composables for inputs in your app, but you still want to use this library for handling the data and error, you could provide your own composables to display the data Korm gives you
  + Localized formatting of input and output for numbers with separators
