public class Worker {
    private final JsonInputReader reader;

    private final TableSerializer serializer;

    public Worker(JsonInputReader reader, TableSerializer serializer) {
        this.reader = reader;
        this.serializer = serializer;
    }

    public void process(String input) {
        Data[] data = reader.parseJson(input);
        for (Data d : data) {
            for (Table t : d.tables()) {
                GeneratedTable generatedTable = new Generator().generate(t.getRow(d.system_row()),
                        t.getColumns(d.system_value_str(), d.system_value_int(), d.system_value_timestamp()));
                String serialized = serializer.serialize(generatedTable);
                serializer.write(serialized, t.getName());
            }
        }
    }
}
