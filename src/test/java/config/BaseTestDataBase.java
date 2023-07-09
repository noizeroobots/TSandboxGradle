package config;

import database.steps.InteractionWithDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;


public class BaseTestDataBase{

    private final SqlConnector connector = new SqlConnector();
    protected InteractionWithDB steps;

    protected Connection connection;

    @BeforeEach
    public void setUp(){
        connection = connector.openConnection();
        steps = new InteractionWithDB(connection);

    }

    @AfterEach
    public void tearDown(){
        connector.closeConnection(connection);
    }
}
