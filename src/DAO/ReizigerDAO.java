package DAO;

import model.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger r) ;
    boolean delete(Reiziger r) ;
    boolean update(Reiziger r);
    Reiziger findById(int id);
    List<Reiziger> findByGbDatum(String datum);
    List<Reiziger> findAll();

}
