package common;

import java.io.FileNotFoundException;
import java.util.Map;

public interface FileReader {
    Map<String, Object> readFile(String filePath) throws FileNotFoundException;
}
