import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        JSONReader jsonReader = new JSONReader();
        CSVWriter csvWriter = new CSVWriter();
        BufferedReader bf = new BufferedReader(new FileReader(args[0]));
        Worker worker = new Worker(jsonReader, csvWriter);
        worker.process(bf.lines().collect(Collectors.joining()));
    }

}
