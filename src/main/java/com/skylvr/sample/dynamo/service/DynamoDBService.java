package com.skylvr.sample.dynamo.service;

import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.skylvr.sample.dynamo.model.Employee;

import java.util.List;

public interface DynamoDBService {
    String save(Employee employee);

    ListTablesResult getTables();
    List<Employee> getAllEmployees();

}
