package Repository;

import Domain.Cake;
import Domain.Comand;
import Domain.ComandException;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class ComandSQLRepository extends MemoryRepository<Comand>{
    private String dbLocation = "jdbc:sqlite:";

    private Connection conn;

    public ComandSQLRepository(String dbLocation) {
        this.dbLocation += dbLocation;
        openConnection();
        createSchema();
        loadData();
    }

    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(dbLocation);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Comands(id int, cakelist varchar(200), date varchar(200));");
                //add 100 comands to the database if it is empty
                try (PreparedStatement statement = conn.prepareStatement("SELECT * from Comands"); ResultSet rs = statement.executeQuery();) {
                    if (!rs.next()) {
                        for (int i = 0; i < 100; i++) {
                            ArrayList<Cake> cakes = new ArrayList<>();
                            cakes.add(new Cake(i, "cake" + i));
                            Comand comand = new Comand(i, cakes, "date" + i +".date" + i +".date" + i);
                            try (PreparedStatement statement2 = conn.prepareStatement("INSERT INTO Comands VALUES (?, ?, ?)")) {
                                statement2.setInt(1, comand.getId());
                                String cakes_string = "";
                                for (int j = 0; j < comand.getCakes().size(); j++){
                                    cakes_string += comand.getCakes().get(j).getId() + " " + comand.getCakes().get(j).getType();
                                    if (j != comand.getCakes().size() - 1){
                                        cakes_string += ",";
                                    }
                                }
                                statement2.setString(2, cakes_string);
                                statement2.setString(3, comand.getDate());
                                statement2.executeUpdate();
                            }
                        }
                    }
                } catch (ComandException e) {
                }
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void loadData() {
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Comands"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    ArrayList<Cake> cakes = new ArrayList<>();
                    String[] cakes_string = rs.getString("cakelist").split(",");
                    for (String cake : cakes_string){
                        String[] cake_string = cake.split(" ");
                        cakes.add(new Cake(Integer.parseInt(cake_string[0]), cake_string[1]));
                    }
                    Comand comand = new Comand(rs.getInt("id"),cakes, rs.getString("date"));
                    this.list.add(comand);
                }
            } catch (ComandException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void add(Comand o) throws RepositoryException {
        super.add(o);
        try {
            // try-with-resources
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Comands VALUES (?, ?, ?)")) {
                // PreparedStatement impotriva SQL injection
                statement.setInt(1, o.getId());
                String cakes_string = "";
                for (int i = 0; i < o.getCakes().size(); i++){
                    cakes_string += o.getCakes().get(i).getId() + " " + o.getCakes().get(i).getType();
                    if (i != o.getCakes().size() - 1){
                        cakes_string += ",";
                    }
                }
                statement.setString(2, cakes_string);
                statement.setString(3, o.getDate());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        super.remove(id);
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Comands WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
