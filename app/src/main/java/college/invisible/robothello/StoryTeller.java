package hello.robotheart.invisible.college.robothello;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.widget.RelativeLayout.LayoutParams;

/**
 * Created by ppham on 2/1/16.
 */
public class StoryTeller {

    private int mLastId;
    private final RelativeLayout mRL;
    private Context mContext;
    private List<String> mStoryLines;
    private int mLineIndex;

    public StoryTeller(RelativeLayout rl, Context context, List<String> storyLines, int originalId) {
        mRL = rl;
        mContext = context;
        mStoryLines = new ArrayList();
        mLineIndex = 0;

        /*
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(mAssets.open("story.txt")));

            while (reader.ready()) {
                mStoryLines.add(reader.readLine());
            }
        } catch (IOException ioe) {
            System.err.println("Error loading story " + ioe.toString());
        }
        */

        this.mStoryLines = storyLines;

    }

    /* Set an onClickListener on the given TextView */
    public void setOnClickListener(TextView tv) {

        final StoryTeller st = this;

        // Create an onClickListener which listens for this item being clicked.
        tv.setOnClickListener( new View.OnClickListener() {

            private int mLineIndex;

            public void onClick(View v) {
                st.advanceStory(this.mLineIndex);
            }

            private View.OnClickListener init(int lineIndex){
                this.mLineIndex = lineIndex;
                return this;
            }
        }.init(mLineIndex));

    }

    /* Advance the story only if new_index == mLineIndex
       to make sure each story line only displays once.
     */
    public void advanceStory(int new_index) {

        // Only instantiate a new textview if we are the latest
        if (new_index != mLineIndex) {
            return;
        }

        final TextView tv = new TextView(mContext);
        int currId = mLastId + 1;
        tv.setId(currId);
        tv.setText(mStoryLines.get(mLineIndex));
        tv.setTextColor(Color.BLACK);

        // Create layout params and add the new TextView to the layout
        final TextView this_tv = tv;
        final LayoutParams params1 =
                new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.BELOW, this_tv.getId());

        mRL.addView(this_tv, params1);

        // Only set onCLickListener if we are not the last one
        if (mLineIndex < mStoryLines.size() - 1) {
            setOnClickListener(tv);
        }

        // Increment
        mLineIndex += 1;
        /*
        // Update the current ID to the last ID.
        mLastId = currId;

        // Create an onClickListener which listens for this item being clicked.
        tv.setOnClickListener( new View.OnClickListener() {

            private int mLineIndex;

            public void onClick(View v) {
                st.advanceStory(this.mLineIndex);
                mRL.addView(tv, params1);
            }

            private View.OnClickListener init(int lineIndex){
                this.mLineIndex = lineIndex;
                return this;
            }
        }.init(mLineIndex));
        */


    }
}