package com.example.boardserver.controller;


import com.example.boardserver.config.AWSConfig;
import com.example.boardserver.service.SnsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.SnsResponse;

@RestController
@Slf4j
public class SnsController {
    private final AWSConfig config;
    private final SnsService snsService;

    public SnsController(AWSConfig awsConfig, SnsService snsService){
        this.config=awsConfig;
        this.snsService = snsService;
    }

    @PostMapping("/create-topic")
    public ResponseEntity<String> createTopic(@RequestParam final String topicName ){
        final CreateTopicRequest createTopicRequest = CreateTopicRequest.builder()
                .name(topicName)
                .build();

        SnsClient snsClient = snsService.getSnsClient();
        final CreateTopicResponse createTopicResponse = snsClient.createTopic(createTopicRequest);


        if(!createTopicResponse.sdkHttpResponse().isSuccessful()){
            throw getResponseStatusException(createTopicResponse);
        }
        log.info("topic Name = {}" + createTopicResponse.topicArn());
        snsClient.close();
        return new ResponseEntity<>("TOPIC CREATING SUCCESS",HttpStatus.OK);
    }


    private ResponseStatusException getResponseStatusException(SnsResponse snsResponse){
        return new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, snsResponse.sdkHttpResponse().statusText().get());
    }

}
