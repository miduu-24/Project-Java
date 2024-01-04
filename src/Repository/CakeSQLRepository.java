package Repository;
import Domain.Cake;
import org.sqlite.SQLiteDataSource;
import java.sql.*;

public class CakeSQLRepository extends MemoryRepository<Cake> {
    private String dbLocation = "jdbc:sqlite:";

    private Connection conn;

    public CakeSQLRepository(String dbLocation) {
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Cakes(id int, type varchar(200));");
                //add 100 cakes to the database if it is empty
                try (PreparedStatement statement = conn.prepareStatement("SELECT * from Cakes"); ResultSet rs = statement.executeQuery();) {
                    if (!rs.next()) {
                        for (int i = 0; i < 100; i++) {
                            Cake cake = new Cake(i, "cake" + i);
                            try (PreparedStatement statement2 = conn.prepareStatement("INSERT INTO Cakes VALUES (?, ?)")) {
                                statement2.setInt(1, cake.getId());
                                statement2.setString(2, cake.getType());
                                statement2.executeUpdate();
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void loadData() {
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Cakes"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Cake cake= new Cake(rs.getInt("id"), rs.getString("type"));
                    this.list.add(cake);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void add(Cake o) throws RepositoryException {
        super.add(o);

        try {
            // try-with-resources
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Cakes VALUES (?, ?)")) {
                // PreparedStatement impotriva SQL injection
                statement.setInt(1, o.getId());
                statement.setString(2, o.getType());
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
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Cakes WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
