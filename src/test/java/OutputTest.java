import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class OutputTest {
    @Test
    public void GeneratedAllTables(@TempDir Path tempDir) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("generate_props.json").getFile());
        String absolutePath = file.getAbsolutePath();
        BufferedReader bf = new BufferedReader(new FileReader(absolutePath));
        JSONReader jsonReader = new JSONReader();

        Path tmpdir = Files.createTempDirectory(Paths.get("target"), "tmpDirPrefix");
        Path propsPath = tempDir.resolve("Tables/");
        CSVWriter csvWriter = new CSVWriter();
        Gson gson = new Gson();
//        Data[] data = gson.fromJson(bf, Data[].class);
        Worker worker = new Worker(jsonReader, csvWriter);
        worker.process(bf.lines().collect(Collectors.joining()));
        tmpdir.toFile().deleteOnExit();
//        for(Data d:data){
//            for(Table t:d.tables()){
//                t.getName().
//            }
//        }
    }
}
