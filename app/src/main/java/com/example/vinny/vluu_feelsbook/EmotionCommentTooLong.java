package com.example.vinny.vluu_feelsbook;

public class EmotionCommentTooLong extends Exception {
    /*
     * EmotionCommentTooLong extends the Exception class and is used to raise exceptions when
     * creating emotion comments that are longer than 100 characters.
     */

    EmotionCommentTooLong() {
        super("Comment has exceeded the 100 character limit");
    }

}
