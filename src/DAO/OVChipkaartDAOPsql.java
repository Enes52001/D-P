package DAO;

import model.OVChipkaart;
import model.Product;
import model.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection con;
    private ReizigerDAO rdao;
    private ProductDAO pdao;
    public OVChipkaartDAOPsql(Connection con) throws SQLException{
        this.con = con;
    }
    @Override
    public boolean save(OVChipkaart o) {
        try{
            PreparedStatement stm = con.prepareStatement("insert into ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                    "\nvalues (?, TO_DATE(?, 'yyyy-mm-dd'), ?, ?, ?)");
            stm.setInt(1, o.getKaartnummer());
            stm.setDate(2, o.getGeldig_tot());
            stm.setInt(3, o.getKlasse());
            stm.setDouble(4, o.getSaldo());
            stm.setInt(5, o.getReiziger_id());
            stm.executeQuery();
            System.out.println("ov chipkaart is succesvol toegevoegd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart o) {
        try{

            PreparedStatement stm = con.prepareStatement("delete from ov_chipkaart where kaart_nummer = ?");
            stm.setInt(1, o.getKaartnummer());

            stm.executeUpdate();

            System.out.println("ov chipkaart is succesvol verwijderd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart o) {
        try {
            PreparedStatement stm = con.prepareStatement("update ov_chipkaart set geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? where kaart_nummer = ?");
            stm.setDate(1, o.getGeldig_tot());
            stm.setInt(2, o.getKlasse());
            stm.setDouble(3, o.getSaldo());
            stm.setInt(4, o.getReiziger_id());
            stm.setInt(5, o.getKaartnummer());
            stm.executeUpdate();
            System.out.println("OV is geupdate!");
            return true;
        } catch (SQLException e) {
            System.err.println("er ging iets mis: " + e.getMessage());
            return false;
        }
    }

    @Override
    public OVChipkaart findByKaartnummer(int knummer) {
            try{
                PreparedStatement stm = con.prepareStatement("select * from ov_chipkaart where kaart_nummer = ?");
                stm.setInt(1, knummer);
                ResultSet result = stm.executeQuery();
                result.next();

                PreparedStatement stm2 = con.prepareStatement("select * \n" +
                        "from product \n" +
                        "where product_nummer in(select product_nummer\n" +
                        "\t\t\t\t\t from ov_chipkaart_product\n" +
                        "\t\t\t\t\t where kaart_nummer = ?)");
                stm.setInt(1, knummer);
                ResultSet result2 = stm.executeQuery();
                result2.next();

                OVChipkaart ov =  new OVChipkaart(result.getInt("kaart_nummer"), result.getDate("geldig_tot"),
                        result.getInt("klasse"), result.getDouble("saldo"));
                Product product = new Product(result2.getInt("product_nummer"), result2.getString("naam"), result2.getString("beschrijving"), result2.getDouble("prijs"));
                ov.addProduct(product);
                ov.setReiziger(rdao.findById(result.getInt("reiziger_id")));
                System.out.println("ov chipkaart met kaartnummer "+ knummer + " is gevonden!");

                ov.setProduct((ArrayList<Product>) pdao.findByOvChipkaart(ov));
                return ov;
            }catch(SQLException e){
                System.err.println("er ging iets mis: "+e.getMessage());
                return null;
            }
    }

    @Override
    public List<OVChipkaart> findAll() {
        try{
            List<OVChipkaart> lijst = new ArrayList<>();
            PreparedStatement stm = con.prepareStatement("select * from ov_chipkaart");
            ResultSet result = stm.executeQuery();
            while(result.next()){
                OVChipkaart ov = new OVChipkaart(result.getInt("kaart_nummer"), result.getDate("geldig_tot"),
                        result.getInt("klasse"), result.getDouble("saldo"));
                ov.setReiziger(rdao.findById(result.getInt("reiziger_id")));
                lijst.add(ov);

                ov.setProduct((ArrayList<Product>) pdao.findByOvChipkaart(ov));
            }
            return lijst;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }

    @Override
    public OVChipkaart findByReiziger(Reiziger r) {
        try{
            PreparedStatement stm = con.prepareStatement("select * from ov_chipkaart where reiziger_id = ?");
            stm.setInt(1, r.getId());
            ResultSet result = stm.executeQuery();
            result.next();
            OVChipkaart ov = new OVChipkaart(result.getInt("kaart_nummer"), result.getDate("geldig_tot"), result.getInt("klasse")
                    , result.getDouble("saldo"));
            result.getInt("reiziger_id");
            ov.setReiziger(r);
            System.out.println("Adres gekoppeld aan reizigers-ID "+ r.getId() + " is gevonden!");

            ov.setProduct((ArrayList<Product>) pdao.findByOvChipkaart(ov));

            return ov;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }
}
