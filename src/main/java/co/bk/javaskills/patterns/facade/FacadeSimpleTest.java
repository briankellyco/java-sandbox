package co.bk.javaskills.patterns.facade;

import java.sql.Connection;

public class FacadeSimpleTest {
    public static void main(String[] args) {
        String tableName="test";
         
        //generating MySql HTML report and Oracle PDF report without using Facade
        Connection con = MySqlHelper.getMySqlDBConnection();
        MySqlHelper mySqlHelper = new MySqlHelper();
        mySqlHelper.generateMySqlHTMLReport(tableName, con);
         
        Connection con1 = OracleHelper.getOracleDBConnection();
        OracleHelper oracleHelper = new OracleHelper();
        oracleHelper.generateOraclePDFReport(tableName, con1);
         
        //generating MySql HTML report and Oracle PDF report using Facade
        FacadeSimple.generateReport(FacadeSimple.DBTypes.MYSQL, FacadeSimple.ReportTypes.HTML, tableName);
        FacadeSimple.generateReport(FacadeSimple.DBTypes.ORACLE, FacadeSimple.ReportTypes.PDF, tableName);
    }
}
