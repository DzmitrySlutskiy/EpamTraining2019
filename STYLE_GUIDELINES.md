# Android Project Guidelines

The aim of this document is to define project guidelines. These should be followed throughout the Android project in order to help us to keep our code base clean and consistent.

## 1. Project Guidelines
### 1.1 File Naming


#### 1.1.1 Class Files

Any classes that you define should be named using UpperCamelCase, for example:

	AndroidActivity, NetworkHelper, UserFragment, PerActivity


Any classes extending an Android framework component should **always** end with the component name. For example:

	UserFragment, SignUpActivity, RateAppDialog, PushNotificationService, NumberView

We use UpperCamelCase as this helps to separate the words used to create the name, making it easier to read. Naming classes to end with the framework component makes it super clear as to what the class is used for. For example, if you're looking to make changes to the RegistrationDialog then this naming convention makes it really easy to locate that class.

#### 1.1.2 Resource Files

When naming resource files you should be sure to name them using lower case letters and underscores instead of spaces, for example:

	activity_main, fragment_user, adapter_item_post

This convention again makes it really easy to locate the specific layout file that you're looking for. Within Android studio, the layout package is sorted in alphabetical order meaning that activity, fragment and other layout types become grouped - so you know where to begin looking for a file. Other than this, beginning the file name with the component name makes it clear what component/class the layout file is being used for.

##### 1.1.2.1 Drawable Files

Drawable resource files should be named using the corresponding prefix, for example:

|      Type     |   Prefix  |                 Example                 |
|:-------------:|:---------:|:---------------------------------------:| 
|icon           |ic_        |ic_general_alert                         |
|selector       |selector_  |selector_power_button                    |
|shape          |bg_        |bg_notification                          |
|layer-list     |bg_        |bg_bottom_line                           |
|animated-vector|ic_avd_    |ic_avd_indeterminate_downloading_progress|

Note: Icons name should be the same as in Zeplin Styleguide (https://zpl.io/brE9d3a) with section prefix. 
For example:

	ic_general_search, ic_navigation_tv

This convention again helps to group similar items within Android Studio. It also makes it clear as to what the item is used for. For example, naming a resource button_cancel could mean anything! Is this a selector resource or a rounded button background? Correct naming helps to clear any ambiguity that may arise.

When creating selector state resources, they should be named using the corresponding suffix:

| State   |   Suffix  |          Example            |
|---------|---------- |-----------------------------|
|Default  |_default   |bg_secondary_button_default  |
|Disabled |_disabled  |bg_secondary_button_disabled |
|Pressed  |_selected  |bg_secondary_button_selected |
|Focused  |_focused   |bg_secondary_button_focused  |
|Active   |_active    |bg_secondary_button_active   |

Using clear prefixes such as the above helps to make it absolutely obvious as to what a selector state resource is used for. Prefixing resources with the color or any other identifier again requires the developer to open the selector file to be educated in what the different selector state resources are.

##### 1.1.2.2 Layout Files

When naming layout files, they should be named starting with the name of the Android Component that they have been created. For example:

|      Component          |    Class Name Example   |   XML Prefix   |
|-------------------------|-------------------------|----------------|
|AdapterView Item         |ViewHolder               |adapter_item_   |
|Fragment                 |MainFragment             |fragment_       |
|Dialog                   |SeekBarDialog            |dialog_         |
|View                     |CarouselView             |view_           |
|Activity                 |MainActivity             |activity_       |
|RemoteViews(Widget)      |                         |widget_         |
|RemoteViews(Notification)|                         |notification_   |
|<include>                |                         |layout_         |


Not only does this approach makes it easy to find files in the directory hierarchy, but it really helps when needing to identify what corresponding class a layout file belongs to.

##### 1.1.2.3 Menu Files

Menu files don’t need to be prefixed with the menu_ prefix. This is because they are already in the menu package in the resources directory, so it is not a requirement.

##### 1.2.2.4 Values Files

All resource file names should be plural, for example:

	attrs.xml, strings.xml, styles.xml, colors.xml, dimens.xml

##### 1.2.2.5 Color

All brand has his own color names, and please use the names of this colors from style guide.
DON'T add new colors that not included in colors style guide.
Example: if Zeplin's Screen has INTERACTION background, we should use R.color.Interaction or @color/Interaction, and we will set right colors for all brands.
All colors are in module_ui, base colors are in main package, and brands colors (like Interaction, Moonlight and etc.) are in brands packages.

## 2. Code Guidelines
### 2.1 Java Language Rules

#### 2.1.1 Never ignore exceptions

Avoid not handling exceptions in the correct manner. For example:

```java
    public void setUserId(final String id) {
        try {
            userId = Integer.parseInt(id);
        } catch (NumberFormatException e) {

        }
    }
```


This gives no information to both the developer and the user, making it harder to debug and could also leave the user confused if something goes wrong. When catching an exception, we should also always log the error to the console for debugging purposes and if necessary alert the user of the issue. For example:

```java
    public void setCount(final String newCount) {
        try {
            count = Integer.parseInt(newCount);
        } catch (NumberFormatException e) {
            count = 0;
            Log.e(TAG, "There was an error persing the count" + e );
            DialogFactory.showErrorMessage(R.string.error_message_parsing_count);
        }
    }
```

Here we handle the error appropriately by:

Showing a message to the user notifying them that there has been an error
Setting a default value for the variable if possible
Throw an appropriate exception


#### 2.1.2 Grouping exceptions

Where exceptions execute the same code, they should be grouped in-order to increase readability and avoid code duplication. For example, where you may do this:

```java
    public void openCustomTab(Context context, @Nullable Uri uri) {
        Intent intent = buildIntent(context, uri);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "There was an error opening the custom tab " + e);
        } catch (NullPointerException e) {
            Log.e(TAG, "There was an error opening the custom tab " + e);
        } catch (SomeOtherException e) {
            // Show some dialog
        }
    }
```

You could do this:

```java
    public void openCustomTab(Context context, @Nullable Uri uri) {
        Intent intent = buildIntent(context, uri);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e | NullPointerException e) {
            Log.e(TAG, "There was an error opening the custom tab " + e);
        } catch (SomeOtherException e) {
            // Show some dialog
        }
    }
```


#### 2.1.3 Fully qualify imports

When declaring imports, use the full package declaration. For example:

Don’t do this:

```import android.support.v7.widget.*;```

Instead, do this:

```import android.support.v7.widget.RecyclerView;```

#### 2.1.4 Don't keep unused imports

Sometimes removing the code from a class can mean that some imports are no longer needed. If this is the case then the corresponding imports should be removed alongside the code.



### 2.2 Java Style Rules

#### 2.2.1 Field definition and naming

All fields should be declared at the top of the file, following these rules:

* Private, non-static field names shouldn't start with m and not contain “_”. This is right:
    ```userSignedIn, userNameText, acceptButton```


* Function parameters don't starts with prefix p.

    ```int numOfChildren; String userName;```


* Static final fields (known as constants) are ALL_CAPS_WITH_UNDERSCORES.

    ```private static final int PAGE_COUNT = 0;```

* Field names that do not reveal intention should not be used. For example,

    ```int e; //number of elements in the list```

why not just give the field a meaningful name in the first place, rather than leaving a comment!

int numberOfElements;

That's much better!


##### 2.2.1.2 View Field Naming

When naming fields that reference views, the name of the view should be the last word in the name. For example:

| View           |           Name           |
|----------------|:------------------------:|
| TextView       | userNameView             |
| Button         | acceptLoginButton        |
| ImageView      | profileAvatarView        |
| RelativeLayout | profileLayout            |
| RecyclerView   | offlineUsersRecyclerView |


We name views in this way so that we can easily identify what the field corresponds to. For example, having a field named **user** is extremely ambiguous - giving it the name usernameView, userAvatarView or userProfieLayout helps to make it clear exactly what view the field corresponds with.

#### 2.2.2 Avoid naming with container types

Leading on from the above, we should also avoid the use of container type names when creating variables for collections. For example, say we have an arraylist containing a list of userIds:

Better

```List<String> userIds = new ArrayList<>();```

Than

```List<String> userIdList = new ArrayList<>();```

If and when container names change in the future, the naming of these can often get forgotten about - and just like view naming, it's not entirely necessary. Correct naming of the container itself should provide enough information for what it is.


#### 2.2.3 Avoid similar naming

Naming variables, method and/or classes with similar names can make it confusing for other developers reading over your code. For example:

	hasUserSelectedSingleProfilePreviously

	hasUserSelectedSignedProfilePreviously

Distinguishing the difference between these at a first glance can be hard to understand what is what. Naming these in a clearer way can make it easier for developers to navigate the fields in your code.

#### 2.2.4 Number series naming

When Android Studio auto-generates code for us, it's easy to leave things as they are - even when it generate horribly named parameters! For example, this isn't very nice:

```public void doSomething(String s1, String s2, String s3)```

It's hard to understand what these parameters do without reading the code. Instead:

```public void doSomething(String pUserName, String pUserEmail, String pUserId)```

That makes it much easier to understand! Now we'll be able to read the code following the parameter with a much clearer understanding‚

#### 2.2.5 Pronounceable names

When naming fields, methods, and classes they should:

* Be **readable**: Efficient naming means we'll be able to look at the name and understand it instantly, reducing cognitive load on trying to decipher what the name means.

* Be **speakable**: Names that are speakable avoids awkward conversations where you're trying to pronounce a badly named variable name.

* Be **searchable**: Nothing is worse than trying to search for a method or variable in a class to realize it's been spelled wrong or badly named. If we're trying to find a method that searches for a user, then searching for 'search' should bring up a result for that method.


#### 2.2.6 Treat acronyms as words

Any acronyms for class names, variable names etc should be treated as words - this applies for any capitalisation used for any of the letters. For example:

|        Do       |      Don't      |
|:---------------:|:---------------:|
|    setUserId    |    setUserID    |
|    String uri   |    String URI   |
|      int id     |      int ID     |
|    parseHtml    |    parseHTML    |
| generateXmlFile | generateXMLFile |



#### 2.2.7 Avoid justifying variable declarations

Any declaration of variables should not use any special form of alignment, for example:
This is fine:

```java
private int userId = 8;
private int count = 0;
private String username = "hitherejoe";
```

Avoid doing this:

```java
private int userId      = 8;
private int count       = 0;
private String username = "hitherejoe";
```

This creates a stream of whitespace which is known to make text difficult to read for certain learning difficulties.

#### 2.2.8 Use spaces for indentation


For blocks, 4 space indentation should be used:

```java
    if (userSignedIn) {
        count = 1;
    }
```

Whereas for line wraps, 8 spaces should be used:

```java
     String userAboutText =
                "This is some text about the user and it is pretty long, can you see!";
```

#### 2.2.9 If-Statements

##### 2.2.9.1 Use standard brace style

Braces should always be used on the same line as the code before them. For example, don’t do this:

```java
    class SomeClass
    {
        private void someFunction()
        {
            if (isSomething)
            {

            }
            else if (!isSomethingElse)
            {

            }
            else
            {

            }
        }
    }
```

And instead, do this:

```java
    class SomeClass {
        private void someFunction() {
            if (isSomething) {

            } else if (!isSomethingElse) {

            } else {

            }
        }
    }
```


Not only is the extra line for the space not really necessary, but it makes blocks easier to follow when reading the code.

##### 2.2.9.2 Inline if-clauses

Don’t use a single line for if statements. For example:

```java
    if (user == null) return false;
```

Use braces

```java
    if (user == null) {
        return false;
    }
```



##### 2.2.9.3 Nested if-conditions

Where possible (less than 4 conditions), if-conditions should be combined to avoid over-complicated nesting. For example:

Do:

```java
    if (userSignedIn && userId != null) {

    }
```


Try to avoid:

```java
    if (userSignedIn) {
        if (userId != null) {

        }
    }
```


This makes statements easier to read and removes the unnecessary extra lines from the nested clauses.
If more than 1 move to method(see functions)

##### 2.2.9.4 Ternary Operators

Where appropriate, ternary operators can be used to simplify operations.

For example, this is easy to read:

```java
    userStatusImage = signedIn ? R.drawable.ic_tick : R.drawable.ic_cross;
```

and takes up far fewer lines of code than this:

```java
    if (signedIn) {
        userStatusImage = R.drawable.ic_tick;
    } else {
        userStatusImage = R.drawable.ic_cross;
    }
```

**Note**: There are some times when ternary operators should not be used. If the if-clause logic is complex or a large number of characters then a standard brace style should be used.

##### 2.2.9.5 Other cases

* Put empty lines before and after if/for/while/switch/try blocks if they in middle of the code

```java
// bad
String lang = config.getLanguage();
if (lang == null) {
    lang = DEFAULT_LANG;
}
return baseUrl + BASE_URL_PR + countryCode + SLASH + lang + BASE_URL_END;

// good
String lang = config.getOESPLanguage();

if (lang == null || StringUtil.isEmpty(lang)) {
    lang = DEFAULT_LANG;
}

return baseUrl + BASE_URL_PR + countryCode + SLASH + lang + BASE_URL_END;
```
```java
// bad
final int len = views.size();
for (int i = 0; i < len; i++) {
    // code
}
canvas.restore();

// good
final int len = views.size();

for (int i = 0; i < len; i++) {
    // code
}

canvas.restore();
```

* Put new line before return and break and call super if they not alone in blocks

```java
// bad
if (v.getBottom() >= getBottom()) {
    adapter.notifyGroupExpanded(groupPos);
    return expandGroup(groupPos);
}

// good
if (v.getBottom() >= getBottom()) {
    adapter.notifyGroupExpanded(groupPos);

    return expandGroup(groupPos);
}

// bad
public boolean collapseGroupWithAnimation(int groupPos) {
    int groupFlatPos = getFlatListPosition(getPackedPositionForGroup(groupPos));
    // code
    adapter.notifyDataSetChanged();
    return isGroupExpanded(groupPos);
}

// good
public boolean collapseGroupWithAnimation(int groupPos) {
    int groupFlatPos = getFlatListPosition(getPackedPositionForGroup(groupPos));
    // code
    adapter.notifyDataSetChanged();

    return isGroupExpanded(groupPos);
}

// bad
public boolean collapseGroupWithAnimation(int groupPos) {

    return isGroupExpanded(groupPos);
}

// good
public boolean collapseGroupWithAnimation(int groupPos) {
    return isGroupExpanded(groupPos);
}

// bad
for (int i = info.firstChildPosition; i < len; i++) {
    if (totalHeight < clipHeight) {
        // code
    } else {
        dummyView.addFakeView(childView);
        int averageHeight = totalHeight / (i + 1);
        totalHeight += (len - i - 1) * averageHeight;
        break;
    }
}

// good
for (int i = info.firstChildPosition; i < len; i++) {
    if (totalHeight < clipHeight) {
        // code
    } else {
        dummyView.addFakeView(childView);
        int averageHeight = totalHeight / (i + 1);
        totalHeight += (len - i - 1) * averageHeight;

        break;
    }
}

// bad
switch (month) {
    case DECEMBER:
        numDays = 31;

        break;
    case NOVEMBER:
        numDays = 30;
        counter++;
        break;
    default:
        System.out.println("Invalid month.");
        break;
}

// good
switch (month) {
    case DECEMBER:
        numDays = 31;

        break;
    case NOVEMBER:
        numDays = 30;
        counter++;

        break;
    default:
        System.out.println("Invalid month.");

        break;
}

@Override
protected void onPause() {
    super.onPause();

    //code
}

@Override
protected void onPause() {
    //code

    super.onPause();

    //code
}
```




##### 2.2.9.6 Use static function for starting new activity
This encapsulates all logic for creating Intent and pass arguments in final activity.

For example, create function show in Activity:

```java
    public static void show(final Context context, final String pParam){
        Intent intent = new Intent(context, ActivityToOpen.class);
        intent.putExtra(STRING_PARAM, pParam);
        intent.addFlags(YOUR_INTENT_FLAGS);
        context.startActivity(intent);
    }
```

And call this method in the second class:

```java
ActivityToOpen.show(context, "My parameter");
```

#### 2.2.10 Annotations

##### 2.2.10.1 Annotation practices

Taken from  the Android code style guide:

**@Override:**

*The @Override annotation must be used whenever a method overrides the declaration or implementation from a super-class. For example, if you use the @inheritdocs Javadoc tag, and derive from a class (not an interface), you must also annotate that the method @Overrides the parent class's method.*

**@SuppressWarnings:**

*The @SuppressWarnings annotation should only be used under circumstances where it is impossible to eliminate a warning. If a warning passes this "impossible to eliminate" test, the @SuppressWarnings annotation must be used, so as to ensure that all warnings reflect actual problems in the code.*

Annotations should always be used where possible. For example, using the @Nullable annotation should be used in cases where a field could be expected as null. For example:

```java
    private TextView mUserNameText;

    private void setName(@Nullable final CharSequence pName) {
        mUserNameText.setText(pName);
    }

```
##### 2.2.10.2 Annotation style

Annotations that are applied to a method or class should always be defined in the declaration, with only one per line:

```java
    @Annotation
    @AnotherAnnotation
    public class SomeClass {

        @SomeAnnotation
        public String getMeString() {

        }
    }
```

When using the annotations on fields, you should ensure that the annotation remains on the same line whilst there is room. For example:

```java
    @Bind(R.id.layout_coordinator) CoordinatorLayout mCoordinatorLayout

    @Inject MainPresenter mMainPresenter
```

We do this as it makes the statement easier to read. For example, the statement

```@Inject SomeComponent mSomeName```

reads as inject this component with this name.

#### 2.2.11 Limit variable scope

The scope of local variables should be kept to a minimum (Effective Java Item 29). By doing so, you increase the readability and maintainability of your code and reduce the likelihood of error. Each variable should be declared in the innermost block that encloses all uses of the variable.

Local variables should be declared at the point they are first used. Nearly every local variable declaration should contain an initializer. If you don't yet have enough information to initialize a variable sensibly, you should postpone the declaration until you do. - taken from the Android code style guidelines


#### 2.2.12 Unused elements

All unused **fields**, **imports**, **methods** and **classes** should be removed from the code base unless there is any specific reasoning behind keeping it there.


#### 2.2.13 Logging

Logging should be used to log useful error messages and/or other information that may be useful during development.
Should delete all logs except in try-catch block.

| Log                                | Reason                   |
|------------------------------------|--------------------------|
|Log.v(String tag, Object message)   |verbose                   |
|Log.d(String tag, Object message)   |debug                     |
|Log.i(String tag, Object message)   |information               |
|Log.w(String tag, Object message)   |warning                   |
|Log.e(String tag, Object message)   |error                     |
| Log.xd(Object tag, Object message) |log HttpRequest or debug  |

We can set the `Tag` for the log as a `static final` field at the top of the class, for example:


```private static final String TAG = MyActivity.class.getName();```



#### 2.2.14 Field Ordering

Any fields declared at the top of a class file should be ordered in the following order:

1. Enums
2. Constants
3. private global variables

Do not use public and protected fields, avoid initialization in field declaration.

For example:

```java
    public static enum {
        ENUM_ONE, ENUM_TWO
    }

    public static final String KEY_NAME = "KEY_NAME";
    public static final int COUNT_USER = 0;

    private int mUserCount;
    private String mUrrorMessage;

    public int mSomeCount;
    public String mSomeString;

```

Using this ordering convention helps to keep field declarations grouped, which increases both the locating of and readability of said fields.

#### 2.2.15 Class member ordering

To improve code readability, it’s important to organize class members in a logical manner. The following order should be used to achieve this:

1. Constants
2. Fields
3. Constructors
4. Override methods and callbacks (public or private)
5. Public methods
6. Private methods
7. Inner classes or interfaces

For example:

```java
public class MainActivity extends Activity {

    private int mCount;

    public static newInstance() { }

    @Override
    public void onCreate() { }

    public setUsername() { }

    private void setupUsername() { }

    static class AnInnerClass { }

    interface SomeInterface { }

}
```

Any lifecycle methods used in Android framework classes should be ordered in the corresponding lifecycle order. For example:

```java
public class MainActivity extends Activity {

    // Field and constructors

    @Override
    public void onCreate() { }

    @Override
    public void onStart() { }

    @Override
    public void onResume() { }

    @Override
    public void onPause() { }

    @Override
    public void onStop() { }

    @Override
    public void onRestart() { }

    @Override
    public void onDestroy() { }

    // public methods, private methods, inner classes and interfaces

}
```

#### 2.2.16 Method parameter ordering

When defining methods, parameters should be ordered to the following convention:

```java
public Post loadPost(Context context, int pPostId);


public void loadPost(Context context, int pPostId, Callback pCallback);
```

**Context** parameters always go first and **Callback** parameters always go last.

(NOTE: better to use ContextHolder.get() to prevent memory leak)

#### 2.2.17 Enums

Enums should only be used where actually required. If another method is possible, then that should be the preferred way of approaching the implementation. For example:

Instead of this:

```java
public enum SomeEnum {
    ONE, TWO, THREE
}
```


Do this:

```java
private static final int VALUE_ONE = 1;
private static final int VALUE_TWO = 2;
private static final int VALUE_THREE = 3;
```

Also, good option is to use @IntDef from support library

```java
   @IntDef({ConstantValue.VALUE_ONE, ConstantValue.VALUE_TWO, ConstantValue.VALUE_THREE,})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ConstantValue {
        int VALUE_ONE = 1;
        int VALUE_TWO = 2;
        int VALUE_THREE = 3;
    }
```

#### 2.2.18 Arguments in fragments and activities

When we pass data using an Intent or Bundle, the keys for the values must use the conventions defined below:

* Activity: passing data to an activity must be done using a reference to a EXTRA, as defined as below:

  ```private static final String EXTRA_NAME = "com.your.package.name.to.activity.EXTRA_NAME";```

* Fragment: passing data to a fragment must be done using a reference to an ARGUMENT, as defined as below:


  ```private static final String ARGUMENT_NAME = "ARGUMENT_NAME";```

When creating new instances of a fragment or activity that involves passing data, we should provide a static method to retrieve the new instance, passing the data as method parameters. For example:

**Activity**

```java
    public static void start(final Context context, final Post pPost) {
        Intent intent = new Intent(context, ActivityToOpen.class);
        intent.putParcelableExtra(EXTRA_POST, pPost);
        context.startActivity(intent);
    }
```

**Fragment**
```java
    public static PostFragment newInstance(final Post pPost){
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_POST, pPost);
        fragment.setArguments(args);

        return fragment;
    }
```


#### 2.2.19 Line Length Limit

Code lines should preferably no longer than 100 characters, this makes the code more readable. Sometimes to achieve this, we may need to:


- Extract data to a local variable
- Extract logic to an external method
- Line-wrap code to separate a single line of code to multiple lines

**Note**: For code comments and import statements it’s ok to exceed the 100 character limit.

##### 2.2.19.1 Line-wrapping techniques

When it comes to line-wraps, there are a few situations where we should be consistent in the way we format code.

* Breaking at Operators

    When we need to break a line at an operator, we break the line before the operator:

    ```java
    int mCount = countOne + countTwo - countThree + countFour * countFive - countSix
               + countOnANewLineBecauseItsTooLong;
    ```
    If desirable, you can always break after the `=` sign:

    ```java
    int mCount =
            countOne + countTwo - countThree + countFour * countFive + countSix;
    ```

* Method Chaining
    When it comes to method chaining, each method call should be on a new line.

```java
   //Don’t do this:

   Picasso.with(mContext).load("someUrl").into(mImageView);


   //Instead, do this:

   Picasso.with(mContext)
           .load("someUrl")
           .into(mImageView);
```

* Long Parameters

    In the case that a method contains long parameters, we should line break where appropriate. For example, when declaring a method we should break after the last comma of the parameter that fits:

```java
private void someMethod(Context context, String pSomeLongStringName, String pText,
                            long pThisIsALong, String pAnotherString) {
}

//calling method with break after the comma of each parameter

someMethod(mContext,
        "thisIsSomeLongTextItsQuiteLongIsntIt",
        "someText",
        01223892365463456,
        "thisIsSomeLongTextItsQuiteLongIsntIt");
```


#### 2.2.20 Method spacing

There only needs to be a single line space between methods in a class, for example:

```java
//do this

public String getUserName() {
    // Code
}

public void setUserName(String pName) {
    // Code
}

public boolean isUserSignedIn() {
    // Code
}




//not this

public String getUserName() {
    // Code
}


public void setUserName(String pName) {
    // Code
}


public boolean isUserSignedIn() {
    // Code
}

```

#### 2.2.21 Commentaries

Don’t leave author comments, these aren’t useful and provide no real meaningful information when multiple people are to be working on the class.

```java
/**
 * Created By Person 15/09/2017
 */
```

All commentaries should be deleted (except for very important). Commented code block or unused variable should be removed.

In most cases TODOs should contain link or ticket id which refers to required changes.

#### 2.2.22 Anonymous class

Anonymous classes should be no more than 20 lines and contain no more than 3 methods.
We have sonar message for breaking this rule: "Reduce this anonymous class number of lines from 'number' to at most 20, or make it a named class."

In this instance, we should extract this to the nested class.

In other cases, anonymous classes should extract in a variable. For example:

```java
    private final SimpleTextWatcher mTextChangeListener = new SimpleTextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    //set listener where need

    searchView.setOnTextChangeListener(mTextChangeListener);

```


### 2.3 XML Style Rules

#### 2.3.1 Use self-closing tags

When a View in an XML layout does not have any child views, self-closing tags should be used.

Do:

```xml
    <android.support.v7.widget.AppCompatImageView
        android:id="@+id/some_plase_image_view"
        android:layout_width="90dp"
        android:layout_height="90dp" />
```

Don’t:

```xml
    <android.support.v7.widget.AppCompatImageView
        android:id="@+id/some_plase_image_view"
        android:layout_width="90dp"
        android:layout_height="90dp"></android.support.v7.widget.AppCompatImageView>
```

**Note:** If you have some troubles with formatting please follow next steps:
In Android Studio go to File -> Settings and check if you use Default scheme:

<p align="center">
  <img src="docs/xml_code_style_settings.png" />
</p>

#### 2.3.2 Resource naming

All resource names and IDs should be written using lowercase and underscores, for example:

```text_username, activity_main, fragment_user, error_message_network_connection```

The main reason for this is consistency, it also makes it easier to search for views within layout files when it comes to altering the contents of the file.

##### 2.3.2.1 ID naming

All IDs should be named in following pattern:
{ui_place}_{element_name}. Where **element_name** are ```_text_view, _image_view, _check_box , _progress_bar and etc.```

|      Examples                              |
|--------------------------------------------|
| login_username_edit_text                   |
| title_card_accept_payment_button           |
| share_dialog_social_networks_recycler_view |


##### 2.3.2.2 Strings

All string names should begin with a prefix for the part of the application that they are being referenced from. For example:

| Screen                | String       | Resource Name             |
|-----------------------|--------------|---------------------------|
|Registration Fragment  |Register now  |REGISTRATION_REGISTER_NOW  |
|Sign Up Activity       |Cancel        |SIGN_UP_CANCEL             |
|Rate App Dialog        |No thanks     |RATE_APP_NO_THANKS         |


If it’s not possible to name the referenced like the above, we can use the following rules:

| Prefix  | Description                                  |
|---------|----------------------------------------------|
|ERROR_   |Used for error messages                       |
|TITLE_   |Used for dialog titles                        |
|ACTION_  |Used for option menu actions                  |
|MSG_     |Used for generic message such as in a dialog  |
|LABEL_   |Used for activity labels                      |

String resources should **always** be defined in the strings file and never hardcoded in layout or class files.

##### 2.3.2.3 Styles and themes

When defining both Styles & Themes, they should be named using UpperCamelCase. For example:

```xml
AppTheme.DarkBackground.NoActionBar
AppTheme.LightBackground.TransparentStatusBar
```

```xml
ProfileButtonStyle
 TitleTextStyle
```


#### 2.3.3 Attributes ordering

Ordering attributes not only looks tidy but it helps to make it quicker when looking for attributes within layout files. As a general rule,


1. View Id
2. Style
3. Layout width and layout height
4. Other `layout_` attributes, sorted alphabetically
5. Remaining attributes, sorted alphabetically

For example:

```xml
    <Button
        android:id="@+id/some_id"
        style="@style/some_style"
        android:layout_width="2dp"
        android:layout_height="15dp"
        android:layout_gravity="center_vertical"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:background="@color/some_color"
        android:padding="16dp"
        android:text="@string/app_name"
        android:textColor="@color/black" />
```

Note: This formatting can be carried out by using the format feature in android studio -

`cmd + shift + L`

Doing this makes it easy to navigate through XML attributes when it comes to making changes to layout files.

#### 2.3.4 All from resources

Dimens should be extracted only if they used in different places or used for varied screen size. For example, the dimension for ui-components from Zeplin style guidelines.

Layouts should not have hardcoded colours or strings (besides tools:text).



#### 2.3.5 Vector Drawable formatting
After downloading vector drawable and importing to the project, you should format file and use colors from resources.

After import vector drawable

<p align="center">
  <img src="docs/avd_before_formatting.png" />
</p>

After extract color to resources and xml formatting

<p align="center">
  <img src="docs/avd_after_formatting.png" />
</p>


### 2.4 Tests style rules

#### 2.4.1 Unit tests

Any Unit Test classes should be written to match the name of the class that the test is targeting, followed by the Test suffix. For example:


| Class                | Test Class               |
|----------------------|--------------------------|
|DataManager           |DataManagerTest           |
|UserProfilePresenter  |UserProfilePresenterTest  |
|PreferencesHelper     |PreferencesHelperTest     |


All Test methods should be annotated with the @Test annotation, the methods should be named using the following template:

```java
    @Test
    public void methodNamePreconditionExpectedResult() {

    }
```


So for example, if we want to check that the signUp() method with an invalid email address fails, the test would look like:

```java
    @Test
    public void signUpWithInvalidEmailFails() {

    }
```


Tests should focus on testing only what the method name entitles.
If there are extra conditions being tested in your Test method then this method should be divide on separate methods.

If a class we are testing contains many different methods, we should create separate classes with own responsibility. If for us hard to test class and need create many different tests, maybe will best decision simplify this class or method by splitting into separate parts.


## 3. Gradle Style
### 3.1 Dependencies

#### 3.1.1 Versioning

Where applicable, versioning that is shared across multiple dependencies should be defined as a variable within the dependencies scope. For example:

  ```
    ext.hznSupportLibsVersion = '25.4.0'

    compile 'com.android.support:mediarouter-v7:' + hznSupportLibsVersion
    compile 'com.android.support:support-v4:' + hznSupportLibsVersion
    compile 'com.android.support:recyclerview-v7:' + hznSupportLibsVersion
    compile 'com.android.support:support-anotations:' + hznSupportLibsVersion
    compile 'com.android.support:design:' + hznSupportLibsVersion
    compile 'com.android.support:percent:' + hznSupportLibsVersions
    compile 'com.android.support:customtabs:' + hznSupportLibsVersions

```

This makes it easy to update dependencies in the future as we only need to change the version number once for multiple dependencies.

#### 3.1.2 Grouping

Where applicable, dependencies should be grouped by package name, with spaces in-between the groups. For example:

```
    compile 'com.android.support:mediarouter-v7:' + hznSupportLibsVersion
    compile 'com.android.support:percent' + hznSupportLibsVersion

    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'io.reactivex.rxjava2:rxjava:2.1.2'

    compile 'com.jakewharton:butterknife:8.8.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
    compile 'com.jakewharton:timber:4.5.1'

    compile 'com.github.bumptech.glide:glide:4.0.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.0.0'

```

*compile* , *testCompile* and *androidTestCompile* dependencies should also be grouped into their corresponding section. For example:

```
    //app dependencies
    compile 'com.android.support:mediarouter-v7:' + hznSupportLibsVersion
    compile 'com.android.support:recyclerview-v7:' + hznSupportLibsVersion

    //instrumentation test dependencies
    androidTestCompile 'com.android.support:support-annotations:' + hznSupportLibsVersion

    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'io.reactivex.rxjava2:rxjava:2.1.2'

    //unit test dependencies
    testCompile 'org.robolectric.robolectrik:3.0'


```

Both of these approaches make it easy to locate specific dependencies when required as it keeps dependency declarations both clean and tidy.

#### 3.1.3 Independent Dependencies

Where dependencies are only used individually for application or test purposes, be sure to only compile them using compile , testCompile or androidTestCompile.
For example, where the robolectric dependency is only required for unit tests, it should be added using:

```testCompile 'org.robolectric:robolectric:3.0'```



# Kotlin Project Guidelines

### 1 Kotlin Style Rules
#### 1.1 Field definition and naming
* Fields name, constructor parameters shouldn't start with `m` prefix;
* Function parameters shouldn't have `p` prefix.


More cases you can follow in this link: https://android.github.io/kotlin-guides/style.html

#### 1.2 Kotlin data class
* Data classes SHOULD be used without interfaces.