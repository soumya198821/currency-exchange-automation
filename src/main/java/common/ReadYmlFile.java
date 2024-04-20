package common;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class ReadYmlFile implements FileReader {
    Yaml yaml = new Yaml();
    File file;
    InputStream inputStream = null;

    @Override
    /**
     *  readFile method is used to read the yml file from the filepath provided to method.
     */
    public Map<String, Object> readFile(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        inputStream = new FileInputStream(file);
        return yaml.load(inputStream);
    }
}

