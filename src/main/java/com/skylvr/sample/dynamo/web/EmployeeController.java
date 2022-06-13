package com.skylvr.sample.dynamo.web;

import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.skylvr.sample.dynamo.model.Employee;
import com.skylvr.sample.dynamo.service.DynamoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    DynamoDBService dbService;

    @GetMapping(path = "/")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Application is healthy");
    }
    @GetMapping(path = "/employees")
    public ResponseEntity<List<Employee>> fetchAllEmployees() {
        return ResponseEntity.ok(dbService.getAllEmployees());
    }
    @PutMapping(path = "/employees")
    public ResponseEntity<String> tableExists(@RequestBody final Employee employee) {
        return ResponseEntity.ok(dbService.save(employee));
    }
    @GetMapping(path = "/initialize")
    public ResponseEntity<ListTablesResult> initializeTables() {
        return ResponseEntity.ok(dbService.getTables());
    }

}
