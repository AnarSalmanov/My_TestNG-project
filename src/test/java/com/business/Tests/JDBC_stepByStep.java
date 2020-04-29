package com.business.Tests;

import com.business.Utilities.ConfigReader;
import com.business.Utilities.DBUtils;
import com.mongodb.DB;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.transform.sax.SAXSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC_stepByStep {

    //Connection info:
    //jdbc:postgresql:(jdbc db driver)//ip where db is : 5432(port) + /hr (dbname)
    private String hrdbURL = ConfigReader.getProperty("hrdbUrl");
    private String hrdbUsername = ConfigReader.getProperty("hrdbUser");  //hr
    private String hrdbPassword = ConfigReader.getProperty("hrdbPassword");  //hr


    @Test(enabled = false)
    public void dataBaseTesting() throws SQLException {
        DBUtils.createConnection(hrdbURL, hrdbUsername, hrdbPassword);
        String query = "SELECT first_name from employees ;";
        ResultSet resultEmployees = DBUtils.executeQuery(query);
        resultEmployees.next(); //if we don't use while loop,then we need to skip first row always.
        List<String> names = new ArrayList<>();
        while (resultEmployees.next()) {
            names.add(resultEmployees.getString("first_name"));
        }
        System.out.println(names);
        Assert.assertTrue(names.contains("John"));

        // Countries table
        ResultSet countriesRes = DBUtils.executeQuery("select * from countries ;");
        while (countriesRes.next()) {
            System.out.println(countriesRes.getObject("country_id") + " - "
                    + countriesRes.getObject("country_name") + " - "
                    + countriesRes.getObject("region_id"));
        }
        countriesRes.last(); // -> points to last row (void method);
        int rowCount = countriesRes.getRow(); //Before checking rows count MUST point to last row!
        System.out.println("Total rows count in table is " + rowCount); // -> 25
        DBUtils.destroy(); // close connection
    }


}
