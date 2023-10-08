package co.bk.javaskills.patterns.facade;

import java.sql.Connection;


public class MySqlHelper {

	public static Connection getMySqlDBConnection(){
		//get MySql DB connection using connection parameters
		return null;
	}

	public void generateMySqlPDFReport(String tableName, Connection con){
		//get data from table and generate pdf report
		System.out.println("MySqlHelper.generateMySqlPDFReport()");
	}
	
	public void generateMySqlHTMLReport(String tableName, Connection con){
		//get data from table and generate pdf report
		System.out.println("MySqlHelper.generateMySqlHTMLReport()");
	}
}