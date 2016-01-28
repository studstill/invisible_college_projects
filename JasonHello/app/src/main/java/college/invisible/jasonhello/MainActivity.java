package college.invisible.jasonhello;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl = (RelativeLayout) this.findViewById(R.id.relative_view);
        Context context = rl.getContext();

        List<String> storyLines = Arrays.asList(
                "A droid has information that will help freedom fighters. ",
                "The droid, when it gives information, will explode on the person. ",
                "The explosion will result in a great world of abundance: food, water, love, empathy."
        );
        final HashMap<Integer, String> map = new HashMap<>();
        int prevId = R.id.hello_view;

        for (int i = 0; i < storyLines.size(); i++) {
            final TextView tv = new TextView (this);
            int currId = prevId + 1;
            tv.setId(currId);
            tv.setText(storyLines.get(i));
            tv.setTextColor(Color.BLACK);
            Integer a = tv.hashCode();
            map.put(a, storyLines.get(i));

            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params1.addRule(RelativeLayout.BELOW, prevId);
            prevId = currId;
            tv.setOnClickListener(new View.OnClickListener() {
                boolean wasTouched = true;
                public void onClick(View v) {
                    if (wasTouched) {
                        tv.setBackgroundColor(Color.BLACK);
                        tv.setTextColor(Color.WHITE);
                        wasTouched = !wasTouched;
                    } else {
                        tv.setBackgroundColor(Color.WHITE);
                        tv.setTextColor(Color.BLACK);
                        wasTouched = !wasTouched;
                    }
                }
            });
            //lL.addView(tv);
            rl.addView(tv, params1);
        }

    }

}
