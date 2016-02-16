package college.invisible.robothello;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ppham on 2/8/16.
 */

@ParseClassName("FlashCard")
public class SampleModel extends ParseObject {
    public static final String DATA_QUESTION = "question";
    public static final String DATA_ANSWER = "answer";

    public enum Side {QUESTION, ANSWER};

    private Side mSide;

    /* new SampleModel() */
    public SampleModel() {
        this.mSide = Side.QUESTION;
    }

    /* new SampleModel("question", "answer") */
    public SampleModel(String question, String answer) {
        this.mSide  = Side.QUESTION;
        setQuestion(question);
        setAnswer(answer);
    }

    public void setQuestion(String question) {
        put(DATA_QUESTION, question);
    }

    public void setAnswer(String answer) {
        put(DATA_ANSWER, answer);
    }

    public String getQuestion() {
        return getString(DATA_QUESTION);
    }

    public String getAnswer() {
        return getString(DATA_ANSWER);
    }

    public boolean isQuestion() {
        return this.mSide == Side.QUESTION;
    }

    public String getVisibleString() {
        if (this.mSide == Side.QUESTION) {
            return getQuestion();
        } else {
            return getAnswer();
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
