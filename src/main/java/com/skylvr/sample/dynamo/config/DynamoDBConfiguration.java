package com.skylvr.sample.dynamo.config;

import com.amazonaws.ClientConfigurationFactory;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {
    Logger logger = LoggerFactory.getLogger(DynamoDBConfiguration.class);

    @Value("${amazon.aws.accessKey}")
    private String awsAccessKey;
    @Value("${amazon.aws.secretKey}")
    private String awsSecretKey;
    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Value("${application.dynamoDBUrl}")
    private String dynamoDBUrl;


    @Bean
    public DynamoDBMapper getMapper() {
        return new DynamoDBMapper(getDynamoDbClient());
    }

    @Bean
    public AmazonDynamoDB getDynamoDbClient() {
        logger.info("ApplicationProperties dynamoDb: {}, awsRegion: {}", dynamoDBUrl, awsRegion);
        logger.info("System.Properties: {}", System.getProperties());
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
                        ))
                .withClientConfiguration(new ClientConfigurationFactory().getConfig().withProtocol(Protocol.HTTP))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDBUrl, awsRegion))
                .build();
    }
}
