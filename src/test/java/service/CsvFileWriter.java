package service;

import java.io.FileWriter;
import java.util.List;
import static constants.Constants.csvLinksAndStatusCodes;

public class CsvFileWriter {
    public static void writeValuesIntoFile(List<List<String>> rows) throws java.io.IOException {
        FileWriter writer = new FileWriter(csvLinksAndStatusCodes,false);

        writer.append("BoutiqueLink");
        writer.append(';');
        writer.append("HttpStatusCode");
        writer.append('\n');

        for (List<String> rowData : rows) {
            writer.append(String.join(";", rowData));
            writer.append("\n");
        }

        writer.flush();
        writer.close();
    }
}
