package JSON;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import entity.Pacgum;
import entity.Pickable;

import java.lang.reflect.Type;

public class PickableJsonDeserialize implements JsonDeserializer<Pickable> {
    @Override
    public Pickable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String type = json.getAsJsonObject().get("type").getAsString();
        switch(type) {
            case "Pacgum":
                return context.deserialize(json, Pacgum.class);
            default:
                throw new IllegalArgumentException("Neither Pacgum or fruit");
        }
    }
}
