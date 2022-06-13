package com.skylvr.sample.dynamo.service;

import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.skylvr.sample.dynamo.model.Employee;
import com.skylvr.sample.dynamo.repository.DynamoDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamoDBServiceImpl implements DynamoDBService {
    private Logger log = LoggerFactory.getLogger(DynamoDBService.class);

    @Autowired
    private DynamoDBRepository dynamoDBRepo;

    @Override
    public String save(Employee employee) {
        log.info("inserting ", employee);
        try {
            this.dynamoDBRepo.insertIntoDynamoDB(employee);
            return "Employee saved";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occured while saving";
        }
    }
    @Override
   public ListTablesResult getTables(){
        return dynamoDBRepo.initializeTables();
   }

    @Override
    public List<Employee> getAllEmployees() {
        return dynamoDBRepo.getAllEmployees();
    }

}
