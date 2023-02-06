package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private Logger logger = LoggerFactory.getLogger(Controller.class);

  @Autowired
  private ReplyingKafkaTemplate<String, Object, Object> template;
  
  @Value("${apiservice.send-topics}")
  private String SEND_TOPICS;

  @GetMapping(path = "customers/{id}")
  public Object getCustomerWithAddress(@PathVariable("id") String customerId) throws Exception {
    logger.info("COLLECTING CUSTOMER AND ADDRESS WITH ID {} FROM UPSTREAM SERVICE", customerId);

    ProducerRecord<String, Object> record = new ProducerRecord<>(SEND_TOPICS, customerId);
    RequestReplyFuture<String, Object, Object> replyFuture = template.sendAndReceive(record);
    SendResult<String, Object> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
    ConsumerRecord<String, Object> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
    return consumerRecord.value();
  }

}
