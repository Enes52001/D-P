package DAO;

import model.OVChipkaart;
import model.Product;
import model.Reiziger;

import java.sql.Date;
import java.util.List;

public interface ProductDAO {
    boolean save(Product p);
    boolean update(Product p);
    boolean delete(Product p);
    List<Product> findByOvChipkaart(OVChipkaart ov);
    List<Product> findAll();
}
