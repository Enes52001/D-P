package DAO;

import model.OVChipkaart;
import model.Product;
import model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            Date date = new Date(1999-12-12);
            for(int i=0 ; i<p.getOv().size() ; i++){
                stm = con.prepareStatement("insert into ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) \nvalues (?, ?, ?, ?)");
                stm.setInt(1, p.getOv().get(i).getKaartnummer());
                stm.setInt(2, p.getProduct_nummer());
                stm.setString(3, "status");
                stm.setDate(4, date);
                stm.executeQuery();
            }
            System.out.println("Product is succesvol toegevoegd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product p) {
        try {
            PreparedStatement stm = con.prepareStatement("update Product set naam = ?, beschrijving = ?, prijs = ? where product_nummer = ?");
            stm.setString(1, p.getNaam());
            stm.setString(2, p.getBeschrijving());
            stm.setDouble(3, p.getPrijs());
            stm.setInt(4, p.getProduct_nummer());
            stm.executeUpdate();

            stm = con.prepareStatement("delete from ov_chipkaart_Product where product_nummer = ?");
            stm.setInt(1, p.getProduct_nummer());
            stm.executeUpdate();

            Date date = new Date(1999-12-12);
            for(int i=0 ; i<p.getOv().size() ; i++){
                stm = con.prepareStatement("insert into ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) \nvalues (?, ?, ?, ?)");
                stm.setInt(1, p.getOv().get(i).getKaartnummer());
                stm.setInt(2, p.getProduct_nummer());
                stm.setString(3, "status");
                stm.setDate(4, date);
                stm.executeQuery();
            }


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

            stm = con.prepareStatement("delete from ov_chipkaart_product where product_nummer = ?");
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
    public ArrayList<Product> findByOvChipkaart(OVChipkaart ov) {
        try{
            ArrayList<Product> lijst = new ArrayList<>();
            PreparedStatement stm = con.prepareStatement("select product.product_nummer, naam, beschrijving, prijs\n" +
                    "from product \n" +
                    "left join ov_chipkaart_product  \n" +
                    "on product.product_nummer = ov_chipkaart_product.product_nummer\n" +
                    "where ov_chipkaart_product.kaart_nummer = ?");
            stm.setInt(1, ov.getKaartnummer());
            ResultSet result = stm.executeQuery();

            while(result.next()){
                Product p = new Product(result.getInt("product_nummer"), result.getString("naam"), result.getString("beschrijving"), result.getDouble("prijs"));
                lijst.add(p);
                p.addOv(ov);
                // mogelijke oplossing: een findbyProduct in OVChipkaartDAO aanmaken, en daarmee hier alle ov kaarten aanroepen, maar daar zou je deze functie weer aanroepen,
                // wat naar een eindeloze loop leidt
            }
            System.out.println("Producten die behoren aan kaartnummer "+ ov.getKaartnummer() + " zijn gevonden!");
            return lijst;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        try{
            List<Product> lijst = new ArrayList<>();
            PreparedStatement stm = con.prepareStatement("select * from Product");
            ResultSet result = stm.executeQuery();
            while(result.next()){
                Product product = new Product(result.getInt("product_nummer"), result.getString("naam"), result.getString("beschrijving"), result.getDouble("prijs"));
                lijst.add(product);

                PreparedStatement stm2 = con.prepareStatement("select * \n" +
                        "from ov_chipkaart \n" +
                        "where kaart_nummer in(select kaart_nummer\n" +
                        "\t\t\t\t\t from ov_chipkaart_product\n" +
                        "\t\t\t\t\t where product_nummer = ?)");
                stm.setInt(1, result.getInt("productnummer"));
                ResultSet result2 = stm2.executeQuery();
                result2.next();
                OVChipkaart ov = new OVChipkaart(result2.getInt("kaart_nummer"), result2.getDate("geldig_tot"), result2.getInt("Klasse"), result2.getDouble("saldo"));
                product.addOv(ov);
            }
            return lijst;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }
}
