import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class CSVWriterTest {
    @Test
    public void CSVWriterTestOneTable() throws IOException {
        JSONReader jsonReader = new JSONReader();
        CSVWriter csvWriter = new CSVWriter();
        BufferedReader bf = new BufferedReader(new FileReader("generate_one_table_prop.json"));
        Worker worker = new Worker(jsonReader, csvWriter);
        worker.process(bf.lines().collect(Collectors.joining()));
//        JSONReader jsonReader = new JSONReader();
//        CSVWriter csvWriter = new CSVWriter();
//        BufferedReader bf = new BufferedReader(new FileReader("generate_one_table_prop.json"));
//        Worker worker = new Worker(jsonReader, csvWriter);
//        worker.process(bf.lines().collect(Collectors.joining()));
//        Generator generator = new Generator();
//        Gson gson = new Gson();
////        Data[] dataRead = gson.fromJson(bf, Data[].class);
////        for (Table oneTable : dataRead[0].tables()
////        ) {
////            List<List<String>> generated = generator.generate(oneTable.getRow(10), oneTable.getColumns());
////            File csvOutputFile = new File("csv_output.csv");
////            try (
////                    PrintWriter pw = new PrintWriter(csvOutputFile);
////            ) {
////                for (List<String> sub_list : generated
////                ) {
////                    pw.println(csvWriter.convertToCSV(sub_list));
////                }
////            }
////        }
//        Data[] data = gson.fromJson(bf, Data[].class);
//        for (Data d : data
//        ) {
//            for (Table t : d.tables()
//            ) {
//                try {
//
//                    GeneratedTable generatedTable = new Generator().generate(t.getRow(d.system_row()), t.getColumns(d.system_value_str(), d.system_value_int(), d.system_value_timestamp()));
//                    String serialized = csvWriter.serialize(generatedTable);
//                    csvWriter.write(serialized, t.getName());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
    }

    @Test
    public void CSVWriterTestAllTables() throws IOException {
        JSONReader jsonReader = new JSONReader();
        CSVWriter csvWriter = new CSVWriter();
        BufferedReader bf = new BufferedReader(new FileReader("M:\\IdeaProjects\\json-to-csv-generator\\src\\test\\resources\\generate_props.json"));
        Worker worker = new Worker(jsonReader, csvWriter);
        worker.process(bf.lines().collect(Collectors.joining()));
    }
}
