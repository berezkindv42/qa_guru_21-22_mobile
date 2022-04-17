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

    @Key("selenoid.user")
    String selenoidUser();

    @Key("selenoid.password")
    String selenoidPassword();

    @Key("device.host")
    String deviceHost();

}

//"browserstack.user","berezkindv_Q8M15k"
//"browserstack.key","qdjj3ZPvdvkzyry2eMnX"
//""app.key","bs://b38e1be64b9751d0092c16e5c4f3927aa26bb5c4"
