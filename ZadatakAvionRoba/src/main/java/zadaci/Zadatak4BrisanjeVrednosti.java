package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Zadatak4BrisanjeVrednosti {
    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {

            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");
            avionDao = DaoManager.createDao(connectionSource,Avion.class);
            robaDao = DaoManager.createDao(connectionSource,Roba.class);

            List<Roba> robe = robaDao.queryForAll();
            for (Roba a:robe) {
                System.out.println(a);
            }
            robe = robaDao.queryForEq(Roba.POLJE_NAZIV,"Voda");
            Roba roba = robe.get(0);
            robaDao.delete(roba);
            for (Roba a:robe) {
                System.out.println(a);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connectionSource != null){
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
