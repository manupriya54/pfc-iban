package test.manu.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import test.manu.resource.IBANResource;
import test.manu.validator.IBANValidator;

import java.util.Properties;

public class PFCModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IBANResource.class);
        bind(IBANValidator.class);
        bind(Properties.class).annotatedWith(Names.named("countryIbanLength")).toProvider(IBANCountryLengthProvider.class);
    }
}
