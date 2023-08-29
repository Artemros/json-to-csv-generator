import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Table {
    private final String name;
    private final Boolean generate_data;
    private final Integer row_amount;
    private final List<Map<String, List<String>>> column;

    public Table(String name, Boolean generate_data, Integer row, List<Map<String, List<String>>> column) {
        this.name = name;
        this.generate_data = generate_data;
        this.row_amount = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(name, table.name) && Objects.equals(generate_data, table.generate_data) &&
                Objects.equals(row_amount, table.row_amount) &&
                column.equals(table.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, generate_data, row_amount, column);
    }

    public String getName() {
        return name;
    }

    public Boolean getGenerate_data() {
        return generate_data;
    }

    public Integer getRow(Integer systemRow) {
        if (row_amount == null) {
            return systemRow;
        } else {
            return row_amount;
        }
    }

    public List<Column> getColumns(String systemValueStr, String systemValueInt, String systemValueTimestamp) {

        return column
                .stream()
                .flatMap(m -> m.entrySet()
                        .stream()
                        .limit(1)
                        .map(e -> {
                                    if (e.getValue().size() == 2) {
                                        return new Column(e.getKey(), e.getValue().get(0), e.getValue().get(1));
                                    } else if (e.getValue().size() == 1) {
                                        switch (e.getValue().get(0)) {
                                            case "int":
                                                return new Column(e.getKey(), e.getValue().get(0), systemValueInt);
                                            case "str":
                                                return new Column(e.getKey(), e.getValue().get(0), systemValueStr);
                                            case "timestamp":
                                                return new Column(e.getKey(), e.getValue().get(0), systemValueTimestamp);
                                        }
                                    }
                                    return null;
                                }
                        )
                )
                .collect(Collectors.toList());
    }
}
