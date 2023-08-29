import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    public GeneratedTable generate(Integer rows_amount, List<Column> columns) {
        List<List<String>> columnData = new ArrayList<>();
        List<GeneratedRow> generatedRowList = new ArrayList<>();
        List<String> header = new ArrayList<>();
        for (Column column : columns
        ) {
            if (column != null) {
                header.add(column.name());
            }
        }
        for (int i = 0; i < rows_amount; i++) {
            List<String> row = new ArrayList<>();
            for (Column column : columns
            ) {
                if (column != null) {
                    String type = column.type();
                    String format = column.format();
                    switch (type) {
                        case "str" -> {
                            int leftLimit = 97; // letter 'a'
                            int rightLimit = 122; // letter 'z'
                            int targetStringLength = 10;
                            Random random = new Random();
                            StringBuilder buffer = new StringBuilder(targetStringLength);
                            for (int j = 0; j < targetStringLength; j++) {
                                int randomLimitedInt = leftLimit + (int)
                                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                                buffer.append((char) randomLimitedInt);
                            }
                            String generatedString = buffer.toString();
                            row.add(generatedString);
                        }
                        case "int" -> {
                            Integer generatedInteger = ThreadLocalRandom.current().nextInt((int) Math.floor(Double.parseDouble(format)));
                            row.add(String.valueOf(generatedInteger));
                        }
                        case "timestamp" -> {
                            Instant tenYearsAgo = Instant.now().minus(Duration.ofDays(10 * 365));
                            Instant tenDaysAgo = Instant.now().minus(Duration.ofDays(10));
                            Instant random = between(tenYearsAgo, tenDaysAgo).plus(new Random().nextInt(), ChronoUnit.MILLIS);
                            row.add(DateTimeFormatter.ofPattern(format).withZone(ZoneId.from(ZoneOffset.UTC)).format(random));
                        }
                    }
                }
            }
            GeneratedRow generatedRow = new GeneratedRow(header, row);
            generatedRowList.add(generatedRow);
            columnData.add(row);
        }
        return new GeneratedTable(header, generatedRowList);
    }

    public static Instant between(Instant startInclusive, Instant endExclusive) {
        long startSeconds = startInclusive.getEpochSecond();
        long endSeconds = endExclusive.getEpochSecond();
        long random = ThreadLocalRandom
                .current()
                .nextLong(startSeconds, endSeconds);

        return Instant.ofEpochSecond(random);
    }

}
