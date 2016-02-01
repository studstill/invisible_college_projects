package hello.robotheart.invisible.college.robothello;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tv = (TextView) findViewById(R.id.hello_view);
        final String hello_string = getResources().getString(R.string.hello_string);
        final String goodbye_string = getResources().getString(R.string.goodbye_string);
        tv.setText(hello_string);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(goodbye_string);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RelativeLayout rl = (RelativeLayout) this.findViewById(R.id.relative_layout);
        Context context = rl.getContext();

        List<String> storyLines = Arrays.asList(
                "A droid has information that will help freedom fighters. ",
                "The droid, when it gives information, will explode on the person. ",
                "The explosion will result in a great world of abundance: food, water, love, empathy."
        );
        int prevId = R.id.hello_view;

        for (int i = 0; i < storyLines.size(); i++) {
            final TextView story_view = new TextView(this);

            int currId = prevId + 1;
            story_view.setId(currId);
            story_view.setText(storyLines.get(i));
            story_view.setTextColor(Color.BLUE);

            RelativeLayout.LayoutParams params1 =
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params1.addRule(RelativeLayout.BELOW, prevId);
            prevId = currId;
            story_view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    System.out.println("Clicked " + story_view.getText());
                }
            });
            rl.addView(story_view, params1);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
