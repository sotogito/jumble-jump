package util.reader;

import domain.item.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ItemCsvReader {
    private final RawCsvReader rawCsvReader;

    public ItemCsvReader(RawCsvReader rawCsvReader) {
        this.rawCsvReader = rawCsvReader;
    }

    public List<Item> readItems(String path) throws IOException {
        List<Item> result = new ArrayList<>();

        List<String[]> read = rawCsvReader.readAll(path);
        for (int i = 1; i < read.size(); i++){
            String[] each = read.get(i);
            result.add(
                    new Item(
                            each[0],
                            Long.parseLong(read.get(i)[1]),
                            Integer.parseInt(read.get(i)[2])
                    )
            );
        }
        return result;
    }
}
