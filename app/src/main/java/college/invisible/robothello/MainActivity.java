package college.invisible.robothello;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView helloView = (TextView) findViewById(R.id.hello_view);
        final String hello_string = getResources().getString(R.string.hello_string);
        final String goodbye_string = getResources().getString(R.string.goodbye_string);
        helloView.setText(hello_string);

        final MainActivity mainActivity = this;

        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, ListActivity.class);
                startActivity(intent);

                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
            }
        });

        RelativeLayout rl = (RelativeLayout) this.findViewById(R.id.relative_layout);
        Context context = rl.getContext();

        final List<String> storyLines = Arrays.asList(
                "Robot has information that will help some freedom fighters battle an evil army.",
                "Robot meets orphan wants to escape desert planet.",
                "The dark lord of the evil army wants to capture the droid and crush the freedom fighters.",
                "A battle happens, involving a superweapon.",
                "Freedom fighters win."
        );

        int prevId = R.id.hello_view;

        List<TextView> tvs = new ArrayList<>();

        for (int i = 0; i < storyLines.size(); i++) {
            final TextView story_view = new TextView(this);

            int currId = prevId + 1;
            story_view.setId(currId);
            //story_view.setText(storyLines.get(i));
            story_view.setTextColor(Color.BLUE);

            RelativeLayout.LayoutParams params1 =
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params1.addRule(RelativeLayout.BELOW, prevId);
            prevId = currId;
            rl.addView(story_view, params1);
            tvs.add(story_view);
        }

        tvs.get(0).setText(storyLines.get(0));

        for (int i = 0; i < tvs.size() - 1; i++) {
            TextView current_view = tvs.get(i);
            final TextView next_view = tvs.get(i+1);
            final String nextStoryLine = storyLines.get(i+1);
            current_view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                   next_view.setText(nextStoryLine);
                }
            });

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
