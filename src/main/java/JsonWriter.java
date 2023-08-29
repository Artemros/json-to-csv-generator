import com.google.gson.Gson;

public class JsonWriter implements TableSerializer {
    @Override
    public String serialize(GeneratedTable table) {
        return new Gson().toJson(table);
    }

    @Override
    public void write(String data, String fileName) {

    }
}
