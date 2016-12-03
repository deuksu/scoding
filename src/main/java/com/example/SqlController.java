package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SqlController {

    private static final Logger log = LoggerFactory.getLogger(SqlController.class);
    
    @Autowired JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/sql1")
	public List<String> sqlTest1(@RequestParam(value="name") String name) {
	     
		log.info("name = " + "SELECT * FROM CUSTOMERS where FIRST_NAME ='" + name+"'");
		List<String> rtn = new ArrayList<String>();
//		 SqlRowSet rs = jdbcTemplate.queryForRowSet("SELECT * FROM CUSTOMERS where FIRST_NAME ='" + name+"'");
//		 while (rs.next()) {
//             rtn.add(rs.getString("FIRST_NAME"));
//         }
		
		 try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		     Statement stat = conn.createStatement()) {
		     try (ResultSet rs = stat.executeQuery("SELECT * FROM CUSTOMERS where FIRST_NAME ='" + name+"'")) {
		         while (rs.next()) {
		             rtn.add(rs.getString("FIRST_NAME"));
		         }
		     }
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		 
		 log.info("END sql statement");
		 return rtn;
	}
	
	@RequestMapping("/sql2")
	public List<String> sqlTest2(@RequestParam(value="name") String name) {
	     
		log.info("name = " + name);
		List<String> rtn = new ArrayList<String>();
//		 SqlRowSet rs = jdbcTemplate.queryForRowSet("SELECT * FROM CUSTOMERS where FIRST_NAME ='" + name+"'");
//		 while (rs.next()) {
//             rtn.add(rs.getString("FIRST_NAME"));
//         }
		 String sql = "SELECT * FROM CUSTOMERS where FIRST_NAME = ?";
		 try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		     PreparedStatement stat = conn.prepareStatement(sql)) {
			 stat.setString(1, name);
		     ResultSet rs = stat.executeQuery();
		         while (rs.next()) {
		             rtn.add(rs.getString("FIRST_NAME"));
		         }
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		 
		 log.info("END sql statement");
		 return rtn;
	}
	
	@RequestMapping("/sql/update")
	public void update(@RequestParam(value="name") String name) {
		log.info("Start sql statement");
		
		 try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		     Statement stat = conn.createStatement()) {
			 stat.execute("UPDATE CUSTOMERS SET FIRST_NAME ='" + name+"'");
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		 
		 log.info("END sql statement");
	}	
}
