package jumble_jump.util.reader;

import com.opencsv.CSVReader;
import jumble_jump.util.message.ErrorMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RawCsvReader {

    public List<String[]> readAll(String path) throws IOException {
        InputStream in = getClass().getResourceAsStream(path);

        if (in == null) {
            throw new IOException(
                    String.format(ErrorMessage.CANT_FIND_FONT_FILE_FORMAT_PATH,path));
        }

        InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);

        CSVReader csvReader = new CSVReader(isr);
        return csvReader.readAll();
    }
}
