package com.android.bookathon;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import static com.android.bookathon.CognitoSyncClientManager.credentialsProvider;

/**
 * Created by LENOVO on 09-May-17.
 */

public class DynamoDBManager {

    static AmazonDynamoDB client;

    public static void init() {

        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
    }
}
