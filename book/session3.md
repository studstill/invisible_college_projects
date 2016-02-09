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
