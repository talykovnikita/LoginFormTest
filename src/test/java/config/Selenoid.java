package config;

import org.aeonbits.owner.ConfigFactory;

public class Selenoid {
    public static SolenoidConfig credentials =
            ConfigFactory.create(SolenoidConfig.class, System.getProperties());
}