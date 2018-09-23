package com.example.vinny.vluu_feelsbook;

import java.util.Date;

public abstract class Emotion {

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

    public Date getEmotionDate() {
        return emotionDate;
    }

    public void setEmotionDate(Date date) {
        emotionDate = date;
    }
}
