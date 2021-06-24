package test.manu.modules;

import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public enum DI {

    INSTANCE;

    private Injector injector;

    public Injector getInjector() {
        return this.injector;
    }

    public static DI di() {
        return INSTANCE;
    }

    public void initialize(Injector injector) {
        this.injector = injector;
    }
}
