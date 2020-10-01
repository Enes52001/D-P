package DAO;

import model.OVChipkaart;
import model.Product;
import model.Reiziger;

import java.sql.*;

public class ProductDAOPsql implements ProductDAO{
    private Connection con;
    private OVChipkaartDAO odao;

    public ProductDAOPsql(Connection con){
        this.con = con;
    }
    @Override
    public boolean save(Product p) {
        try{
            PreparedStatement stm = con.prepareStatement("insert into product (product_nummer, naam, beschrijving, prijs) \nvalues (?, ?, ?, ?)");
            stm.setInt(1, p.getProduct_nummer());
            stm.setString(2, p.getNaam());
            stm.setString(3, p.getBeschrijving());
            stm.setDouble(4, p.getPrijs());
            stm.executeQuery();
            System.out.println("Product is succesvol toegevoegd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product p, String status, Date last_update) {
        try {
            PreparedStatement stm = con.prepareStatement("update Product set naam = ?, beschrijving = ?, prijs = ? where product_nummer = ?");
            stm.setString(1, p.getNaam());
            stm.setString(2, p.getBeschrijving());
            stm.setDouble(3, p.getPrijs());
            stm.setInt(4, p.getProduct_nummer());
            stm.executeUpdate();

            System.out.println("Product is succesvol geupdate!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Product p) {
        try{

            PreparedStatement stm = con.prepareStatement("delete from product where product_nummer = ?");
            stm.setInt(1, p.getProduct_nummer());
            stm.executeUpdate();

            System.out.println("Product is succesvol verwijderd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public Product findByOvChipkaart(OVChipkaart ov) {
        try{
            PreparedStatement stm = con.prepareStatement("select * from ov_chipkaart_product where kaartnummer = ?");
            stm.setInt(1, ov.getKaartnummer());
            ResultSet result = stm.executeQuery();
            result.next();

            PreparedStatement stm2 = con.prepareStatement("select * from product where productnummer = ?");
            stm.setInt(1, result.getInt("productnummer"));
            ResultSet result2 = stm2.executeQuery();
            result2.next();

            Product p = new Product(result2.getInt("productnummer"), result2.getString("naam"), result2.getString("beschrijving"), result2.getDouble("prijs"));
            System.out.println("Product dat behoort aan kaartnummer "+ ov.getKaartnummer() + " is gevonden!");
            p.addOv(ov);
            return p;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }


}
