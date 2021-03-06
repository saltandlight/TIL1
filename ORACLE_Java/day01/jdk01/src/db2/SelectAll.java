package db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAll {

	public static void main(String[] args) {
		// 1. JDBC Driver Loading...
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					System.out.println("Driver Loading Error");
				}
				// 2. Connection 1521
				String id="db";
				String password="db";
				String url=
						"jdbc:oracle:thin:@70.12.50.245:1521:xe";
//				String ip="70.12.50.220";
				
				Connection con = null;
				try {
					con = DriverManager.getConnection(url, id, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Connection Error");
					e.printStackTrace();
				}
				
				// 3. SQL ����
				String sql="SELECT * FROM T_PRODUCT";
				PreparedStatement pstmt=null;
				ResultSet rset=null;
				try {
					pstmt = con.prepareStatement(sql);
					rset = pstmt.executeQuery();
//					rset.next();
					while(rset.next()) {
						if(rset==null) break;
						String uid=rset.getString("ID");
						String upwd=rset.getString("NAME");
						String uname = rset.getString("PRICE");
						
						System.out.println(uid+" "+upwd+" "+uname);
									
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					pstmt.close();
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}

	}

}
