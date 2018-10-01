package com.example.vinny.vluu_feelsbook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Emotion implements Serializable{

    private String emotionName;
    private String emotionComment;
    private Date emotionDate;
    private static final Integer MAX_CHARACTERS = 100;
    //public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);

    public Emotion(String emotionName, Date emotionDate){
        this.emotionName = emotionName;
        this.emotionDate = emotionDate;
        this.emotionComment = "";

    }

    public Emotion(String emotionName, Date emotionDate, String emotionComment){
        this.emotionName = emotionName;
        this.emotionDate = emotionDate;
        this.emotionComment = emotionComment;
    }

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

    public Date getEmotionDate() {
        return emotionDate;
    }

    public void setEmotionDate(Date date) {
        emotionDate = date;
    }

    public String toString() {
        return this.emotionName + " " + this.emotionDate;
    }
}
