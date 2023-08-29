import com.google.gson.Gson;


public class JSONReader implements JsonInputReader {
    @Override
    public Data[] parseJson(String input) {
        Gson gson = new Gson();
        return gson.fromJson(input, Data[].class);
    }
}
