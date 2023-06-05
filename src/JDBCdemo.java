import java.sql.*;

public class JDBCdemo {
    public static void main(String[] args) throws SQLException {
commitdemo();
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

    public static void insertRecord(int id, String name, int salary) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcexample1";

        String userName = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url, userName, password);// connection establishment
        String query = "insert into employee values(?,?,?)";

        PreparedStatement pr = con.prepareStatement(query);
        pr.setInt(1, id);
        pr.setString(2, name);
        pr.setInt(3, salary);
        int rows = pr.executeUpdate();
        System.out.println(rows);

        con.close();

    }


    public static void deleteRecords(int id) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcexample1";

        String userName = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url, userName, password);// connection establishment
        String query = "delete from employee where emp_id= " + id;
        Statement st = con.createStatement();//statement object is needed to execute query
        int rows = st.executeUpdate(query);


        con.close();

    }


    public static void storeProcedureCall() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcexample1";

        String userName = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url, userName, password);// connection establishment
        CallableStatement cs = con.prepareCall("{call getEmp()}");

        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }

        con.close();

    }




    public static void commitdemo() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcexample1";

        String userName = "root";
        String password = "";

        String query="update employee set salary =55000 where emp_id=1";
        String query1="update employee set salary =56000 where emp_id=2";

        Connection con = DriverManager.getConnection(url, userName, password);// connection establishment
        Statement st=con.createStatement();
        con.setAutoCommit(false);
        int rows1=st.executeUpdate(query);
        System.out.println(rows1);
        int rows2=st.executeUpdate(query1);
        System.out.println(rows2);
        if(rows1+rows2==2) con.commit();
        con.close();

    }


}
