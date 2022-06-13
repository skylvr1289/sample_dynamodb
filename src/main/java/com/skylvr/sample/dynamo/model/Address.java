package com.skylvr.sample.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;

import java.io.Serializable;

@DynamoDBDocument
@Data
public class Address  implements Serializable {
    private static final long serialVersionUID = 1L;
    @DynamoDBAttribute
    String pincode;
    @DynamoDBAttribute
    String state;
    @DynamoDBAttribute
    String district;
    @DynamoDBAttribute
    String street;
    @DynamoDBAttribute
    String house;
}
