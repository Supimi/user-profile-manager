package com.helthyme.user.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "report-data")
public class ReportData {

    @DynamoDBHashKey(attributeName = "report_id")
    private String reportId;

    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "from_date")
    private Integer fromDate;

    @DynamoDBAttribute(attributeName = "end_date")
    private Integer endDate;

    @DynamoDBAttribute(attributeName = "file_name")
    private String fileName;
}
