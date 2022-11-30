import java.sql.*;
import java.util.*;

class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user", "test");
        props.setProperty("password", "test");
        Connection conn = DriverManager.getConnection(url, props);
        
        Statement st = conn.createStatement();
        st.executeUpdate("DROP TABLE IF EXISTS Persons");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS Persons (PersonID int PRIMARY KEY,LastName varchar(255),FirstName varchar(255));");
        // st.executeUpdate("DELETE FROM Persons WHERE PersonID=1;");
        st.executeUpdate("TRUNCATE TABLE Persons");     // Delete all rows
        
        st.executeUpdate("INSERT INTO Persons (PersonID, LastName, FirstName) VALUES (1, 'Bodas', 'Abhijeet');");
        st.executeUpdate("INSERT INTO Persons (PersonID, LastName, FirstName) VALUES (2, 'Barbare', 'Shiven');");
        st.executeUpdate("INSERT INTO Persons (PersonID, LastName, FirstName) VALUES (3, 'Shetty', 'Prakriti');");

        System.out.println("-------------> Running query");
        ResultSet rs = st.executeQuery("SELECT * from Persons WHERE PersonID=1");
        System.out.println("Results from first run are:");
        while (rs.next()) {
            System.out.println("Inside loop (first query)");
            System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
        }
        
        System.out.println("\n\n\n");
        
        System.out.println("-------------> Running same query again");
        rs = st.executeQuery("SELECT * from Persons WHERE PersonID=1");
        System.out.println("Results from second run are:");
        while (rs.next()) {
            System.out.println("Inside loop (second query)");
            System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
        }
        
        rs.close();
        st.close();
    }
}