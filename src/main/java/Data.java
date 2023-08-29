import java.util.List;
import java.util.Objects;

public record Data(String name_system, String system_value_str, String system_value_int, String system_value_timestamp,
                   Integer system_row, List<Table> tables) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(name_system, data.name_system) &&
                Objects.equals(system_value_str, data.system_value_str) && Objects.equals(system_value_int, data.system_value_int) &&
                Objects.equals(system_value_timestamp, data.system_value_timestamp) && Objects.equals(system_row, data.system_row) &&
                tables.equals(data.tables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name_system, system_value_str, system_value_int, system_value_timestamp, system_row, tables);
    }
}
