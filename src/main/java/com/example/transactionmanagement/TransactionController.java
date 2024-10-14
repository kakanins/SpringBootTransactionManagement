package com.example.transactionmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class TransactionController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/transaction-test")
    @Transactional
    public String transactionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO test_table (name) VALUES ('Transaction 1')");
        if (true) {  // Simulating an error
            throw new RuntimeException("Simulated exception");
        }
        statement.executeUpdate("INSERT INTO test_table (name) VALUES ('Transaction 2')");
        return "Transaction completed";
    }
}
