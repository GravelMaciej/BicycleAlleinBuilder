package webweeg.start.dateiBase;

import webweeg.start.model.FahradKomponent;

import java.sql.SQLException;
import java.util.List;

public interface FahradKomponentRepository {

    static List<FahradKomponent> selectAll() {
        return null;
    }

    FahradKomponent selectByKomponentId(int komponentId) throws SQLException;

    FahradKomponent selectBy(int komponentId) throws SQLException;

    void updateFahradKomponent(FahradKomponent fahradKomponent);

    void deleteFahradKomponent(FahradKomponent fahradKomponent);
}
