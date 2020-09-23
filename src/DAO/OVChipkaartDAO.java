package DAO;

import model.OVChipkaart;
import model.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart o) ;
    boolean delete(OVChipkaart o) ;
    boolean update(OVChipkaart o);
    OVChipkaart findByKaartnummer(int kaartnummer);
    List<OVChipkaart> findAll();
    OVChipkaart findByReiziger(Reiziger r);
}
