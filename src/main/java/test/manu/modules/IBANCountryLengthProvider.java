package test.manu.modules;

import com.google.inject.Inject;
import com.google.inject.Provider;
import test.manu.PFC;

import java.io.IOException;
import java.util.Properties;

public class IBANCountryLengthProvider implements Provider<Properties> {

    @Inject
    public IBANCountryLengthProvider() {
    }

    @Override
    public Properties get() {
        Properties prop = new Properties();
        try {
            prop.load(PFC.class.getClassLoader().getResourceAsStream("data.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data file");
        }
        return prop;
    }
}
