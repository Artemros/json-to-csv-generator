public interface TableSerializer {
    String serialize(GeneratedTable table);

    void write(String data, String fileName);
}
