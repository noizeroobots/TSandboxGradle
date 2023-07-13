package database.candles.dao;

import database.candles.entity.Candles;
import database.candles.entity.CandlesWithTrueRange;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Candles.class)
public interface CandlesDao {

    @SqlUpdate("DELETE FROM candles WHERE time = :time")
    void deleteCandle(@Bind("time") String time);

    @SqlUpdate("CREATE TABLE candles (candle_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY" +
            ", time varchar(50)" +
            ", high numeric(10,3)" +
            ", low numeric(10,3)" +
            ", open numeric(10,3)" +
            ", close numeric(10,3)" +
            ", volume numeric(10,2)" +
            ", isComplete boolean)")
    void createTable();

    @SqlUpdate("DROP TABLE candles")
    void dropTableCandles();

    @SqlUpdate("INSERT INTO candles (time, high, low, open, close, volume, isComplete)" +
            " VALUES (:time, :high, :low, :open, :close, :volume, :isComplete)")
    void insertCandle(@Bind("time") String time
            , @Bind("high") double high
            , @Bind("low") double low
            , @Bind("open") double open
            , @Bind("close") double close
            , @Bind("volume") double volume
            , @Bind("isComplete") boolean isComplete);

    @SqlQuery("SELECT * FROM candles WHERE time = :time")
    Candles findCandleByTime(@Bind("time") String time);

    @SqlUpdate("DELETE FROM candles a " +
            "USING candles b " +
            "WHERE a.candle_id < b.candle_id AND a.time = b.time")
    void deleteDuplicateRows();

    @SqlQuery("SELECT * FROM candles WHERE candle_id = :candle_id")
    Candles selectCandleFromDB(@Bind("candle_id") Integer candle_id);

    @SqlQuery("SELECT COUNT(candle_id) FROM candles")
    Integer getSizeOfTableCandles();
}
