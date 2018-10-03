package com.example.vinny.vluu_feelsbook;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

abstract public class Emotion implements Serializable{

    protected String emotionName;
    protected String emotionComment;
    protected Date emotionDate;
    private static final Integer MAX_CHARACTERS = 100;

    public String getEmotionName() {
        return emotionName;
    }

    public String getEmotionComment() {
        return emotionComment;
    }

    public void setEmotionComment(String newComment) throws EmotionCommentTooLong {
        if (newComment.length() <= MAX_CHARACTERS) {
            emotionComment = newComment;
        }
        else {
            throw new EmotionCommentTooLong();
        }
    }

    public String getEmotionDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        return dateFormat.format(emotionDate);
    }

    public void setEmotionDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        emotionDate = dateFormat.parse(date);
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        return this.emotionName + " " + dateFormat.format(this.emotionDate);
    }
}
