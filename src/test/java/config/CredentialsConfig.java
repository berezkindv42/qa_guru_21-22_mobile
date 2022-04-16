package config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/credentials.properties"
})
public interface CredentialsConfig extends Config {

    @Key("browserstack.login")
    String browserstackLogin();
    @Key("browserstack.password")
    String browserstackPassword();
    @Key("app.key")
    String appKey();

}
