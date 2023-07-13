package database.candles.dao.daoTR;

import database.candles.entity.CandlesWithTrueRange;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(CandlesWithTrueRange.class)
public interface CandlesDaoTR {

    @SqlQuery("SELECT COUNT(candle_id) FROM candles_tr")
    Integer getSizeOfTableCandles();

    @SqlUpdate("CREATE TABLE candles_tr (candle_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY" +
            ", time varchar(50)" +
            ", high numeric(10,3)" +
            ", low numeric(10,3)" +
            ", open numeric(10,3)" +
            ", close numeric(10,3)" +
            ", volume numeric(10,2)" +
            ", is_сomplete boolean" +
            ", TR numeric(10,2))")
    void createTableWithTrueRange();

    @SqlUpdate("INSERT INTO candles_tr (time, high, low, open, close, volume, is_сomplete)" +
            " VALUES (:time, :high, :low, :open, :close, :volume, :is_сomplete)")
    void insertCandleWithTrueRange(@Bind("time") String time
            , @Bind("high") double high
            , @Bind("low") double low
            , @Bind("open") double open
            , @Bind("close") double close
            , @Bind("volume") double volume
            , @Bind("is_сomplete") boolean is_сomplete);

    @SqlUpdate("DROP TABLE candles_tr")
    void dropTableWithTrueRange();

    @SqlUpdate("UPDATE candles_tr SET tr = :tr WHERE candle_id = :candle_id")
    void updateCandleTRWithTrueRange(@Bind("tr") double tr, @Bind("candle_id") int candle_id);

    @SqlQuery("SELECT * FROM candles_tr WHERE candle_id = :candle_id")
    CandlesWithTrueRange selectCandleFromDBWithTrueRange(@Bind("candle_id") int candle_id);
}