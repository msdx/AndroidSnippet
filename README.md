# Android Snippet
Some code snippets for Android project.

[ ![Download](https://api.bintray.com/packages/msdx/maven/AndroidSnippet/images/download.svg) ](https://bintray.com/msdx/maven/AndroidSnippet/_latestVersion)

# Usage

Make sure you declare the JCenter repository in your project's `build.gradle`:

    repositories {
        jcenter()
        maven { url "http://oss.jfrog.org/oss-snapshot-local/" } /// if you use snapshot version

    }

Then add a compile-time dependency on this library, adding the `dependencies` section to `build.gradle` if it doesn't already exist:

    dependencies {
        compile 'com.githang:androidsnippet:0.6.4'
        //compile 'com.githang:androidsnippet:0.6.4-SNAPSHOT'
    }

JavaDoc: [http://msdx.github.io/AndroidSnippet/](http://msdx.github.io/AndroidSnippet/)
    
# Release Note

**0.5**
- Add HttpStatus which copy from org.apache.http.HttpStatus.
- Add some method to AppManager.
- Add PullScallScrollView.
- Add some api about map app.
- Add some Intents.
- Add some api to ChoiceListAdapter.

**0.4**
- Modify BaseListAdapter:
 - setText(String) -> holdText(String);
 - setCheckable(boolean) -> holdCheckable(boolean);
- Modify AppManager:
 - addActivity(Activity) -> add(Activity);
 - +remove(Activity);
 - +start(Context, Class<? extends Activity>, int);
- Fixed WrapHeightViewPager bug: Missed the padding when computed the max height of children
- Add ChoiceListAdapter

**0.3.1**
- Add BaseListAdapter.
- Add UrlUtil.
- Add InsertContact.
- Move AppManager.

