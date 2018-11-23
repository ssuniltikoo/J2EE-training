package com.jp;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class DAL {
	
	Connection con;
	ResultSet rs;
	PreparedStatement prStmt;
	Statement stmt;
	
	
	public  DAL(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			 con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr", "hr");
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public  boolean ValidateUser(String userName,String password){
		boolean flag = false;
		String sql = "select count(*) from Employees where First_Name=? and Last_Name=? ";
		try {
			
			prStmt = con.prepareStatement(sql);
			prStmt.setString(1, userName);
			prStmt.setString(2,password);
			rs = prStmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			if(count==1)
				flag=true;
			rs.close();
			prStmt.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return flag;
		
	}
	
	
	public ArrayList<String> getDbTables(){	
		ArrayList<String> dbTables = new ArrayList<>();
		try {
			DatabaseMetaData dbmd =con.getMetaData();
			rs = dbmd.getTables(null, null, null, new String[] {"TABLE","VIEW"}	);//new String[] {"TABLE","VIEW"}						
			while(rs.next()){
				String tables =rs.getString(3);
				dbTables.add(tables);
			}
			
			rs.close();			
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return dbTables;
	}
	
	
	public ArrayList<String[]> getTableData(String tableName){
		String sql = "SELECT * FROM  "+tableName;
		
		ArrayList<String[]> tableData = new ArrayList<>();
		 
		try{
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			int columnCount = rs.getMetaData().getColumnCount();
			String[] data; 
			while(rs.next()){
				data=new String[columnCount];
				for(int i=1;i<columnCount;i++){
					data[i-1]=rs.getString(i);
				}
				tableData.add(data);
				data=null;
				
			}
			rs.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return tableData;
	}

}
