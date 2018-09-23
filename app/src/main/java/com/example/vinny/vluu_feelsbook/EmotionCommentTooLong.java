package com.example.vinny.vluu_feelsbook;

public class EmotionCommentTooLong extends Exception {

    EmotionCommentTooLong() {
        super("Comment has exceeded the 100 character limit");
    }

}
