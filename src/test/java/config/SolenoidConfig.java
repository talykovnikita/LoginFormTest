package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/credentials.properties"
})
public interface SolenoidConfig extends Config {

    @Key("selenoid_login")
    String login();

    @Key("selenoid_password")
    String password();

    @Key("selenoid_protocol")
    String protocol();

    @Key("selenoid_domain")
    String domain();
}