package com.skylvr.sample.dynamo.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.skylvr.sample.dynamo.model.Address;
import com.skylvr.sample.dynamo.model.Employee;
import com.skylvr.sample.dynamo.service.DynamoDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DynamoDBRepository {
    private Logger LOGGER = LoggerFactory.getLogger(DynamoDBRepository.class);
    @Autowired
    DynamoDBMapper mapper;

    @Autowired
    AmazonDynamoDB dynamoDB;

    public void insertIntoDynamoDB(Employee employee) {
        mapper.save(employee);
    }

    public Employee getEmployeeDetails(String employeeId, String email) {
        return mapper.load(Employee.class, employeeId, email);
    }

    public List<Employee> getAllEmployees() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        // Change to your model class
        return mapper.scan(Employee.class, scanExpression);
    }

    public ListTablesResult initializeTables() {

        // Alternatively, you can scan your model package for the DynamoDBTable annotation
        List<Class> modelClasses = new ArrayList<>();
        modelClasses.add(Employee.class);

        for (Class cls : modelClasses) {
            LOGGER.info("Creating DynamoDB table for " + cls.getSimpleName());
            CreateTableRequest tableRequest = mapper.generateCreateTableRequest(cls);
            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            boolean created = TableUtils.createTableIfNotExists(dynamoDB, tableRequest);

            if (created) {
                LOGGER.info("Created DynamoDB table for " + cls.getSimpleName());
            } else {
                LOGGER.info("Table already exists for " + cls.getSimpleName());
            }
        }

        ListTablesResult tablesResult = dynamoDB.listTables();

        LOGGER.info("Current DynamoDB tables are: ");
        for (String name : tablesResult.getTableNames()) {
            LOGGER.info("\t" + name);
        }
        return tablesResult;
    }
}
