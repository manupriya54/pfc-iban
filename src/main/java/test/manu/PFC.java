package test.manu;

import com.hubspot.dropwizard.guicier.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import test.manu.healthcheck.PFCHealthCheck;
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
        environment.jersey().register(guiceBundle.getInjector().getInstance(IBANResource.class));
        environment.healthChecks().register("service", new PFCHealthCheck());
    }
}
