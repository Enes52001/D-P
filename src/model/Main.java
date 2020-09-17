package model;

import DAO.*;


import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip?user=postgres&password=wachtwoord");

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery("select * from reiziger");

            //HIER ONDER IS OPDRACHT P1
            System.out.println("Alle reizigers:\n");

            while (rs.next()) {
                String tv;
                if(rs.getString("tussenvoegsel") == null){
                     tv = "";

                }else{
                     tv = rs.getString("tussenvoegsel");
                }

                System.out.println("   #" + rs.getString("reiziger_id") + ": "
                        + rs.getString("voorletters")+" "
                        + tv+" "
                        + rs.getString("achternaam")+
                        " (" + rs.getString("geboortedatum") + ")"
                );
            }

            Adres HU = new Adres(6, "2345CJ", "15", "Heidelberglaan", "Utrecht");
            Reiziger Jan = new Reiziger(6, "J", "van", "Leeuwen", java.sql.Date.valueOf("2001-12-24"));
            OVChipkaart ov = new OVChipkaart(52, java.sql.Date.valueOf("2031-12-24"), 1, 50.0);

//            tests voor adres
//            AdresDAOPsql adresDAO = new AdresDAOPsql(con);
//            testAdresDAO(adresDAO, HU, Jan);

//            tests voor reiziger
//            ReizigerDAOPsql reizigerDAO = new ReizigerDAOPsql(con);
//            testReizigerDAO(reizigerDAO, Jan);

//            tests voor ov chipkaart;
            OVChipkaartDAOPsql OVDAO = new OVChipkaartDAOPsql(con);
            testOVChipkaartDao(OVDAO, ov, Jan);



            con.close();

        } catch (SQLException e) {
            System.err.println("er is een fout opgetreden: " + e.getMessage());
        }

    }
    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao, Reiziger reiziger) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");



        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";

        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.

        // EIGEN CRUD TESTS, je kan een voor een de dubbele slash's weghalen en uittesten

        //opdracht p2 tests

        // SAVE FUNCTIE
        //rdao.save(reiziger);

        // DELETE FUNCTIE
        //rdao.delete(reiziger);

        // UPDATE FUNCTIE
        //reiziger.setAchternaam("Holland");
        //reiziger.setGeboortedatum(java.sql.Date.valueOf("1999-10-04"));
        //rdao.update(reiziger);

        // FIND FUNCTIES
        //System.out.println(rdao.findById(3));
        //System.out.println(rdao.findByGbDatum("2002-09-17"));
        //System.out.println(rdao.findAll());


    }

    private static void testAdresDAO(AdresDAO adresDAO, Adres HU, Reiziger reiziger) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");
        HU.setReiziger(reiziger);

        // OPDRACHT P3 TESTS

        //adresDAO.save(HU);

        //adresDAO.delete(HU);

        //HU.setHuisnummer("52");
        //HU.setWoonplaats("Amersfoort");
        //adresDAO.update(HU);

        //System.out.println(adresDAO.findById(6));

        //System.out.println(adresDAO.findByReizigerId(6));

        //System.out.println(adresDAO.findAll());
    }

    private static void testOVChipkaartDao(OVChipkaartDAO ovdao, OVChipkaart ov, Reiziger reiziger) throws SQLException {
        System.out.println("\n---------- Test OVDAO -------------");
        ov.setReiziger(reiziger);

        // OPDRACHT P4 TESTS

        //ovdao.save(ov);

        //ovdao.delete(ov);

        //ov.setSaldo(10.0);
        //ov.setKlasse(2);
        //ovdao.update(ov);

        //System.out.println(ovdao.findAll().toString());

        //System.out.println(ovdao.findByKaartnummer(46392));

    }
}

