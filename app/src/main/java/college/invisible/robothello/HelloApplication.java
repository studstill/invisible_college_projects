package college.invisible.robothello;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by ppham on 2/10/16.
 */
public class HelloApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(SampleModel.class);
        Parse.initialize(this,
                "08VYolhNgHbmKG4TQzW9hayjipJ7YmfrJ0v5qhNz",
                "pN2YKcRx8qF8JmklaHLffn8Skde2JUOHOG6xbKD9");
    }

}
