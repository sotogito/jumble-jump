package jumble_jump.util.reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FontCsvReader {

    private final RawCsvReader rawCsvReader;

    public FontCsvReader(RawCsvReader rawCsvReader) {
        this.rawCsvReader = rawCsvReader;
    }

    public Map<Integer, String> readAsMap(String path) throws IOException {
        List<String[]> lines = rawCsvReader.readAll(path);
        Map<Integer, String> map = new HashMap<>();

        boolean isFirstLine = true;
        for (String[] line : lines) {
            if (isFirstLine) {
                isFirstLine = false; // 헤더 행을 건너뛰기
                continue;
            }
            if (line.length >= 2) {
                map.put(Integer.parseInt(line[0]), line[1]);
            }
        }
        return map;
    }
}