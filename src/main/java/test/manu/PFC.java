package test.manu;

import com.hubspot.dropwizard.guicier.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import test.manu.modules.DI;
import test.manu.modules.PFCModule;
import test.manu.resource.IBANResource;

public class PFC extends Application<Configuration> {

    private GuiceBundle<Configuration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new PFC().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        guiceBundle = GuiceBundle.defaultBuilder(Configuration.class)
                .modules(new PFCModule())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        DI.di().initialize(guiceBundle.getInjector());
        environment.jersey().register(DI.di().getInjector().getInstance(IBANResource.class));
    }
}
