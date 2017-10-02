package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {
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

            robe = robaDao.queryForEq(Roba.POLJE_OPIS,"Plasticna stolica");
            robe.get(0).setOpis("Drvena stolica");
            robaDao.update(robe.get(0));


            robe = robaDao.queryForAll();
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
