package com.example.vinny.vluu_feelsbook;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

public class EmotionJsonAdapter implements JsonSerializer<Emotion>, JsonDeserializer<Emotion> {
    @Override
    public JsonElement serialize(Emotion src, Type typeofsrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.add("Type", new JsonPrimitive(src.getClass().getSimpleName()));
        JsonObject element = new JsonObject();
        element.add("emotionName", context.serialize(src.emotionName, String.class));
        element.add("emotionComment", context.serialize(src.emotionComment, String.class));
        element.add("emotionDate", context.serialize(src.emotionDate, Date.class));
        object.add("Properties", element);
        return object;
    }

    @Override
    public Emotion deserialize(JsonElement jsonElement, Type typeofT, JsonDeserializationContext context){
        JsonObject object = jsonElement.getAsJsonObject();
        String type = object.get("Type").getAsString();
        JsonElement element = object.get("Properties");
        try {
            return context.deserialize(element, Class.forName("com.example.vinny.vluu_feelsbook." + type));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown element type: " + type, e);
        }
    }
}
