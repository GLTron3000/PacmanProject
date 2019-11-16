package JSON;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import entity.Entity;
import entity.Fantom;
import entity.Pacman;
import entity.Wall;

import java.lang.reflect.Type;

@Deprecated
public class EntityJsonDeserialize implements JsonDeserializer<Entity> {
    @Override
    public Entity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String type = json.getAsJsonObject().get("type").getAsString();
        switch(type) {
            case "Pacman":
                return context.deserialize(json, Pacman.class);
            case "Wall":
                return context.deserialize(json, Wall.class);
            case "Fantom":
                return context.deserialize(json, Fantom.class);
            default:
                throw new IllegalArgumentException("Neither Pacman, Fantom or Wall");
        }
    }
}
