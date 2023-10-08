package co.bk.javaskills.patterns.facade;

import java.sql.Connection;

/**
 * A "facade" is an example of a structural pattern.
 * 
 * Structural Patterns describe how objects and classes can be combined to form larger structures
 * 
 * GOF states facades:
 * "Provide a unified interface to a set of interfaces in a subsystem. Facade Pattern 
 *  defines a higher-level interface that makes the subsystem easier to use."
 *  
 * Facade pattern should be applied for similar kind of interfaces, its purpose is
 * to provide a single interface rather than multiple interfaces that does the similar kind of jobs.
 * 
 * @see http://www.journaldev.com/1557/facade-pattern-in-java-example-tutorial
 */
public class FacadeSimple {

	public static void generateReport(DBTypes dbType, ReportTypes reportType, String tableName){
        Connection con = null;
        switch (dbType){
        case MYSQL: 
            con = MySqlHelper.getMySqlDBConnection();
            MySqlHelper mySqlHelper = new MySqlHelper();
            switch(reportType){
            case HTML:
                mySqlHelper.generateMySqlHTMLReport(tableName, con);
                break;
            case PDF:
                mySqlHelper.generateMySqlPDFReport(tableName, con);
                break;
            }
            break;
        case ORACLE: 
            con = OracleHelper.getOracleDBConnection();
            OracleHelper oracleHelper = new OracleHelper();
            switch(reportType){
            case HTML:
                oracleHelper.generateOracleHTMLReport(tableName, con);
                break;
            case PDF:
                oracleHelper.generateOraclePDFReport(tableName, con);
                break;
            }
            break;
        }
         
    }
     
    public static enum DBTypes{
        MYSQL,ORACLE;
    }
     
    public static enum ReportTypes{
        HTML,PDF;
    }
}
