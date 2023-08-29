import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class CSVWriter implements TableSerializer {


    @Override
    public String serialize(GeneratedTable table) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(table.columnNames().stream().collect(Collectors.joining(","))).append("\n");
        table.rows().forEach(
                r -> stringBuilder.append(convertToCSV(r.data())).append("\n"));
        return stringBuilder.toString();
    }


    public String convertToCSV(List<String> data) {
        return data.stream().map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    @Override
    public void write(String data, String fileName) {
        File csvOutputFile = new File(fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile + ".csv")) {
            pw.println(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
