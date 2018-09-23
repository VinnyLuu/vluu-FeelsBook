package com.example.vinny.vluu_feelsbook;
import org.junit.Test;


public class EmotionTest {

    @Test
    public void testEmotion() {
        LoveEmotion love1 = new LoveEmotion();
        FearEmotion fear1 = new FearEmotion();
        AngerEmotion anger1 = new AngerEmotion();
        JoyEmotion joy1 = new JoyEmotion();
        SadnessEmotion sad1 = new SadnessEmotion();
        SurpriseEmotion surprise1 = new SurpriseEmotion();
        EmotionHistory list = new EmotionHistory();
        System.out.println(list.size());
        System.out.println(love1.getEmotionDate());
        String lovecomment = "I love pizza";
        try {
            love1.setEmotionComment(lovecomment);
        } catch (EmotionCommentTooLong emotionCommentTooLong) {
            emotionCommentTooLong.printStackTrace();
        }
        System.out.println(love1.getEmotionComment());
        System.out.println(love1.getEmotionName());
        list.addEmotion(love1);
        list.addEmotion(fear1);
        list.addEmotion(anger1);
        list.addEmotion(joy1);
        list.addEmotion(sad1);
        list.addEmotion(surprise1);
        System.out.println(list.size());
        System.out.println(list.count("Love"));
        LoveEmotion love2 = new LoveEmotion();
        list.addEmotion(love2);
        System.out.println(list.count("Love"));
        list.removeEmotion(love2);
        System.out.println(list.count("Love"));

    }


}

