import java.sql.*;

public class JDBCdemo {
    public static void main(String[] args) throws SQLException {

insertRecord(5,"Amma",100000);
readRecords();

    }


    public static void readRecords() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcexample1";

        String userName = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url, userName, password);// connection establishment
        String query = "select * from employee";
        Statement st = con.createStatement();//statement object is needed to execute query
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }

        con.close();

    }
    public static void insertRecord(int id,String name,int salary) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcexample1";

        String userName = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url, userName, password);// connection establishment
        String query = "insert into employee values(?,?,?)";

      PreparedStatement pr= con.prepareStatement(query);
      pr.setInt(1,id);
      pr.setString(2,name);
      pr.setInt(3,salary);
     int rows= pr.executeUpdate();
        System.out.println(rows);

        con.close();

    }



}
