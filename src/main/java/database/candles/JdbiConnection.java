package database.candles;

import config.BaseConfig;
import groovy.lang.Singleton;
import io.qameta.allure.Attachment;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

@Getter
@Singleton
public class JdbiConnection {

    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv(), System.getProperties());

    private static JdbiConnection jdbiConnection;

    public static JdbiConnection getConnection() {
        if (jdbiConnection == null) {
            jdbiConnection = new JdbiConnection();
        }
        return jdbiConnection;
    }

    private final Jdbi jdbi = Jdbi.create(config.dbUrl(), config.dbUsername(), config.dbPassword())
            .installPlugin(new SqlObjectPlugin())
            .installPlugin(new PostgresPlugin())
            .setSqlLogger(getSQLLogger());


    private SqlLogger getSQLLogger() {
        return new SqlLogger() {
            @Override
            public void logBeforeExecution(final StatementContext ctx) {
                addAttachment(
                        ctx.getRawSql()
                                + System.lineSeparator()
                                + ctx.getBinding()
                );
            }
        };
    }

    @Attachment(value = "sql", type = "text", fileExtension = "txt")
    public static String addAttachment(final String body) {
        return body;
    }
}
