package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/config.properties"})
public interface BaseConfig extends Config {

    String driverDb();
    String urlDb();
    String userDb();
    String passwordDb();

    String sandboxHostname();
    String sharesHostname();
    String marketDataServiceHostname();
    String historyDataHostname();
    Integer sandboxPort();
    String token();

    String dbUrl();
    String dbName();
    String dbUsername();
    String dbPassword();
}
