package matyliano.exchange.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import matyliano.exchange.model.Currency;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class CurrencyReader {

    private static final Type CURRENCIES_TYPE = new TypeToken<List<Currency>>() {
    }.getType();


    public List<Currency> loadDataFrom(String fileName) throws IOException {
        ClassLoader classLoader = CurrencyReader.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(fileName);
        JsonReader reader = null;
        if (resourceAsStream != null) {
            File file = stream2file(resourceAsStream);
            reader = new JsonReader(new FileReader(file));
        }
        assert reader != null;
        return new Gson().fromJson(reader, CURRENCIES_TYPE);
    }

    public File stream2file(InputStream in) throws IOException {
        final File tempFile = File.createTempFile("ruleSet", ".xml");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }
}
