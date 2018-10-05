package com.example.vinny.vluu_feelsbook;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

abstract public class Emotion implements Serializable {
    /*
     * This is the Emotion class that holds the information for each of the emotions. Since it
     * is an Abstract class, it cannot be instantiated, and only the subclassed specific emotions
     * will be instantiated and each of those emotions will be able to use these methods. It
     * contains a setter and getter for the comment and date of the emotion as well as a toString
     * method that allows for printing of the emotion.
     */

    protected String emotionName;
    protected String emotionComment;
    protected Date emotionDate;
    private static final Integer MAX_CHARACTERS = 100;

    /* Getter of the emotion's name */
    public String getEmotionName() {
        return emotionName;
    }

    /* Getter of the emotion's comment */
    public String getEmotionComment() {
        return emotionComment;
    }

    /* Setter of the emotion's comment. Will throw an EmotionCommentTooLong exception if the
     * comment is longer than 100 characters
     */
    public void setEmotionComment(String newComment) throws EmotionCommentTooLong {
        if (newComment.length() <= MAX_CHARACTERS) {
            emotionComment = newComment;
        } else {
            throw new EmotionCommentTooLong();
        }
    }

    /* Getter of the emotion's date */
    public String getEmotionDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        return dateFormat.format(emotionDate);
    }

    /* Setter of the emotion's date */
    public void setEmotionDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        emotionDate = dateFormat.parse(date);
    }

    /* Method used to get a string representation of the emotion. If the emotion comment is more
     * than 25 characters it will instead display the minimum requirement of the name of the
     * emotion and the date.
     */
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        if (this.emotionComment == null) {
            emotionComment = "";
        }
        if (emotionComment.length() >= 25) {
            return this.emotionName + " " + dateFormat.format(this.emotionDate);
        }
        return this.emotionName + " " + dateFormat.format(this.emotionDate) + " " + this.emotionComment;
    }
}
