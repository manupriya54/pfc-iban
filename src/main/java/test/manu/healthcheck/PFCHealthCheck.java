package test.manu.healthcheck;

import com.codahale.metrics.health.HealthCheck;

public class PFCHealthCheck extends HealthCheck {

    @Override
    protected Result check() {
        return Result.healthy();
    }
}
