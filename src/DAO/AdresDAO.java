package DAO;

import model.Adres;
import model.Reiziger;

import java.util.List;

public interface AdresDAO {
    boolean save(Adres r) ;
    boolean delete(Adres r) ;
    boolean update(Adres r);
    Adres findById(int id);
    Adres findByReiziger(Reiziger r);
    List<Adres> findAll();

}
