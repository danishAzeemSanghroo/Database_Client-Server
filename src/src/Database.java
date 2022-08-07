
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static int getTitles(String artist) throws Exception {
		String query="select no_of_albums from music_album where artist='"+artist+"'";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
                int num=0;
                Connection con=Database.getConnection();
                System.out.println("connection="+con);
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			while(result.next())
                        {
                        num = result.getInt("no_of_albums");
                        }
				
                            
                           
			
                    
			return num;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}        
        
   
    }
 /*   public static boolean establishDBConnection() throws ClassNotFoundException, SQLException {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root","root");  
            //here MiniProject is database name, root is username and password  
            
    return true;
    }
    */
    
    //postgreSQL
    public static boolean establishDBConnection()throws Exception
    {
        Connection connection=null;
    String jdbcURL="jdbc:postgresql://localhost:5432/music";
    String username="postgres";
    String password="root";
    try{
        connection = DriverManager.getConnection(jdbcURL,username,password);
   
    System.out.println("Connected");
    //connection.close();
    }
    catch(SQLException e){
    System.out.println("db connection failed, stopping");
    e.printStackTrace();
    }
    
    return connection.isValid(6666);
    }
    
   public static Connection getConnection()throws Exception
    {
        Connection connection=null;
    String jdbcURL="jdbc:postgresql://localhost:5432/music";
    String username="postgres";
    String password="root";
    try{
     connection = DriverManager.getConnection(jdbcURL,username,password);
    System.out.println("Connected");
   // connection.close();
    }
    catch(SQLException e){
    System.out.println("db connection failed, stopping");
    e.printStackTrace();
    }
    return connection;
    }    
    
    
}
