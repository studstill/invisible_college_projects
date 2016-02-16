# Robot Heart Session 4

Using Parse for shared data on the cloud.

## Goals

* Finish and fix our List demo from last time.
* Create a Parse app on parse.com
* Add Parse dependency in our Android project
* Initialize Parse and using an Application class.
* Convert your SampleModel to a ParseObject
* Add a query in your ListActivity to load data online

Stretch goal:

Use our `InputActivity` from Session 2 to ask the user for a flashcard question and answer.
When they click submit, push this new data from your app to Parse as a new FlashCard / SampleModel.
Then have your classmates reload their apps to retrieve your new flashcard.

Voila! You now have social flashcards.

In the future, we can use push data to update our flashcards automatically.

## Review from Last Time

Our use of a list view to display a collection of data is an example of the MVC (model-view-controller)
paradigm that you may be familiar with from web frameworks.

* Adapter (controller)
* Model (data)
* RecyclerView (view)

## Fix our List Demo

From last time, we will need to create `SampleModel` objects (our flash cards) and
add them to the list of our adapter.

In the file SampleRecyclerAdapter.java, change the lines at the beginning of the file to say:

```
    private final List<SampleModel> sampleData = new ArrayList<SampleModel>();

    public SampleRecyclerAdapter(RecyclerView rv) {

        sampleData.add(
                new SampleModel("What do you call an alligator in a trench coat?","An investigator")
        );

        sampleData.add(
                new SampleModel("What do you call a kitten on a hill?","A meowntain.")
        );


        rv.setAdapter(this);
        rv.setOnClickListener(this);
    }
```

## Create a Parse App

Note: Parse is shutting down its hosted service in January 2017 (less than a year from now).
However, don't worry! All the skills we're teaching you will still be relevant.
The Parse server software, along with all the client SDKs (including Android) are
open source. You can run your own Parse server, or use a Parse server run by the Invisible College,
and easily switch over your Android code with a single extra line:

```
Parse.initialize(new Parse.Configuration.Builder(myContext)
    .applicationId("YOUR_APP_ID")
    .clientKey("YOUR_APP_CLIENT_KEY")
    .server("http://localhost:1337/parse")


    ...

    .build()
);
```

Go to parse.com and create an account.
Then create a new app (let's call it `Robot Heart List`).

The main service we use Parse for is cloud data: how to host dynamically-changing information online
for our apps, rather than storing them statically in the APK itself.

Create a class (sometimes called a table in other databases) called `FlashCard`
and add two string columns to it called `question` and `answer`. These should be
familiar from your `SampleModel` class last time as all the things you need for
a flash card!

## Add the Parse dependency

Remember two sessions ago,
we added a third-party library (JAR file) for image loading called Picasso.
We'll do the same thing this time with Parse.

In your `build.gradle` file for the `app` module, add the following lines to
the bottom, where you see the other dependencies.

```
    compile 'com.parse.bolts:bolts-android:1.+'
    compile 'com.parse:parse-android:1.+'
```

## Initialize Parse and an Application

To initialize Parse once when our app starts up (and not every time an activity goes to sleep and wakes
up again, we're going to finally make use of a new concept: an `Application.`

This is just a software class that represents our overall app.

You'll create a new Java class called `HelloApplication.java` and add the following code to it:

```
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Our Robot Hello application.
 */
public class HelloApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(SampleModel.class);
        Parse.initialize(this,
                "woQOGKIEy0ET4LLxqxXRGeEgAZA9qkjdEAWqEYCG",
                "llSDAE93aNBDHd1zSkj9y29rYOOEb0K9SXV47mgn");

    }
}
```

Next, you'll update your `AndroidManifest.xml` to use this new application, with the following line

```
  <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:name=".HelloApplication"
        ...
```

Note the line that asks for permission for our app to access the Internet. We'll need that to
retrieve data from the clouds.

## Convert your SampleModel to a ParseObject

Our `SampleModel` class worked before to represent a single flash card.

Now we need to modify it so that Parse knows it is also a `ParseObject`, a therefore
a suitable object for living in the clouds. This will give it the necessary
behaviors to survive the long journey there and back.

In your `SampleModel.java` file, add the following lines to the beginning of your class,
merging it with the class declaration line that is already there.

```
@ParseClassName("FlashCard")
public class SampleModel extends ParseObject {

    public static final String DATA_QUESTION = "question";
    public static final String DATA_ANSWER = "answer";

    public String getQuestion() {
        return getString(DATA_QUESTION);
    }

    public String getAnswer() {
        return getString(DATA_ANSWER);
    }

    public void setQuestion(String question) {
        put(DATA_QUESTION, question);
    }

    public void setAnswer(String answer) {
        put(DATA_ANSWER, answer);
    }

```
## Add a query in your ListActivity to load data online

In your `ListActivity.java` file, add the following lines
to asynchornously call Parse and retrieve your data in the 
background.

```
        ParseQuery<SampleModel> query = new ParseQuery<>(SampleModel.class);
        query.include(SampleModel.class.getSimpleName());

        query.findInBackground(new FindCallback<SampleModel>() {
            @Override
            public void done(List<SampleModel> flashCards, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                SampleModel[] array = new SampleModel[flashCards.size()];
                flashCards.toArray(array); // fill the array

                sampleRecyclerAdapter.addItems(flashCards);
            }
        });
```

You'll also need to add the following method into your `SampleRecyclerAdapter.java`
in order to allow adding multiple items at once.

```
    public void addItems(List<SampleModel> items) {
        sampleData.addAll(items);
        notifyDataSetChanged();
    }
```
