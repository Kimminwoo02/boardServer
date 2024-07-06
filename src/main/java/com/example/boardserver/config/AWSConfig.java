package com.example.boardserver.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AWSConfig {
    @Value("${sns.topic.arn}")
    private String snsTopic;

    @Value("${sns.accessKey}")
    private String awsAccessKey;

    @Value("${sns.secretKey}")
    private String awsSecretKey;

    @Value("${sns.region}")
    private String snsRegion;
}
