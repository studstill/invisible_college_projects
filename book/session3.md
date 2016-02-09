# Robot Session 3: RecyclerView and List Adapters

## Goals

Tonight we'll learn how to

* Set up a Data Model
* Create a RecyclerView layout
* Connect it with a ListAdapter
* Put it together into a flashcard app.

In your existing Android project, create a new Activity and Layout called `ListActivity`.

## The Data Model

Create a new Java class called `SampleModel.java` and type the following things in it.

```
public class SampleModel {

    private String mQuestion;
    private String mAnswer;

    public enum Side {QUESTION, ANSWER};

    private Side mSide;

    public SampleModel() {
        this.mQuestion = "New question";
        this.mAnswer = "New answer";
        this.mSide = Side.QUESTION;
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }

    public void setAnswer(String answer) {
        this.mAnswer = answer;
    }

    public String getQuestion() {
        return this.mQuestion;
    }

    public String getAnswer() {
        return this.mAnswer;
    }

    public boolean isQuestion() {
        return this.mSide == Side.QUESTION;
    }

    public String getVisibleString() {
        if (this.mSide == Side.QUESTION) {
            return this.mQuestion;
        } else {
            return this.mAnswer;
        }
    }

    public void toggleSide() {
        if (this.mSide == Side.QUESTION) {
            this.mSide = Side.ANSWER;
        } else {
            this.mSide = Side.QUESTION;
        }
    }
}
```

## Setting up your List View in your Activity

In your layout file `activity_list.xml`, make sure it looks like the following:

```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MyActivity"
    >
    
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="vertical"
        tools:listitem="@layout/list_basic_item"
        />

    <ImageButton
        android:id="@+id/fab_add"
        android:layout_alignParentRight="true"
        android:layout_alignParentBottom="true"
        android:layout_width="@dimen/fab_size"
        android:layout_height="@dimen/fab_size"
        android:layout_gravity="bottom|right"
        android:layout_marginRight="16dp"
        android:layout_marginBottom="16dp"
        android:background="@drawable/ripple"
        android:stateListAnimator="@anim/anim"
        android:src="@drawable/ic_action_add"
        android:elevation="1dp"
        />


</RelativeLayout>
```

In the file `list_basic_item.xml` type the following:

```
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"

    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >

    <TextView
        android:id="@+id/textViewSample"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:textSize="20sp"
        android:padding="30dp"
        android:fontFamily="sans-serif-light"
        tools:text="Sample text"
        />

</LinearLayout>
```

Create a new class `SampleRecyclerAdapter.java` and add this to it:

```
public class SampleRecyclerAdapter extends RecyclerView.Adapter<SampleRecyclerAdapter.ViewHolder>
        implements RecyclerView.OnClickListener {

    private final ArrayList<SampleModel> sampleData = DemoApp.getSampleData(20);

    public SampleRecyclerAdapter(RecyclerView rv) {
        rv.setAdapter(this);
        rv.setOnClickListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        View rowView = LayoutInflater.from (parentViewGroup.getContext())
            .inflate(R.layout.list_basic_item, parentViewGroup, false);

        return new ViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final SampleModel rowData = sampleData.get(position);
        viewHolder.textViewSample.setText(rowData.getQuestion());

        viewHolder.itemView.setTag(rowData);
    }


    @Override
    public int getItemCount() {

        return sampleData.size();
    }

    public void removeData (int position) {

        sampleData.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int positionToAdd) {
        int nextSize = sampleData.size() + 1;
        SampleModel sampleModel = new SampleModel();
        sampleModel.setQuestion("" + nextSize);
        sampleModel.setAnswer("" + nextSize);
        sampleData.add(positionToAdd, sampleModel);
        notifyItemInserted(positionToAdd);
    }

    @Override
    public void onClick(View v) {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private final TextView textViewSample;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewSample = (TextView) itemView.findViewById(
                R.id.textViewSample);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // v is the LinearLayout, it turns out, the parent.
            int position = this.getLayoutPosition();
            SampleModel data = (SampleModel) this.itemView.getTag();
            data.toggleSide();
            textViewSample.setText(data.getVisibleString());
            //Log.d("Tag1", "At position + " + Integer.toString(position) + " " + v.toString());
        }
    }

}
```
In the file `ListActivity.java`, in the `onCreate` method:

```
        // Fab button
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                // Or read size directly from the view's width/height
                int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
                Outline fabOutline = new Outline();
                fabOutline.setOval(0, 0, fabSize, fabSize);
            }
        };
        View fabView = findViewById(R.id.fab_add);
        fabView.setOutlineProvider(viewOutlineProvider);

        // RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // RecyclerView layout manager
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // RecyclerView adapter
        final SampleRecyclerAdapter sampleRecyclerAdapter = new SampleRecyclerAdapter(recyclerView);
        recyclerView.setAdapter(sampleRecyclerAdapter);

        // RecyclerView add element logic
        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int positionToAdd = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                sampleRecyclerAdapter.addItem(positionToAdd);
            }
        });
```
