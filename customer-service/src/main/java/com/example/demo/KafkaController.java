package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.SendTo;

@Component
public class KafkaController {

  private Logger logger = LoggerFactory.getLogger(KafkaController.class);

  @KafkaListener(groupId="${customerservice.consumer-group}", topics = "${customerservice.send-topics}")
  @SendTo
  public String listen(ConsumerRecord<String, Object> consumerRecord) {
      return getCustomer(String.valueOf(consumerRecord.value())) + " @" + getAddress(String.valueOf(consumerRecord.value()));
  }

  public String getCustomer(String customerIdValue) {
    logger.info("GETTING CUSTOMER WITH ID {}", customerIdValue);
    long customerId = Long.parseLong(customerIdValue);
    if (customerId < 0 || customerId > NAMES.size() - 1) {
      logger.info("No such CUSTOMER", customerId);
      return "";
    }
    return NAMES.get((int) customerId);
  }

  public String  getAddress(String customerIdValue) {
    logger.info("GETTING ADDRESS FOR CUSTOMER WITH ID {}", customerIdValue);
    long customerId = Long.parseLong(customerIdValue);
    if (customerId < 0 || customerId > NAMES.size() - 1) {
      logger.info("No such CUSTOMER", customerId);
      return "";
    }
    return STREETS.get((int) customerId);
  }

  private static final List<String> STREETS = Arrays.asList(
          "Jefferson Street",
          "Cambridge Road",
          "Olive Street",
          "Old York Road",
          "Aspen Drive",
          "Cherry Lane",
          "10th Street",
          "Pennsylvania Avenue",
          "Central Avenue",
          "8th Street West",
          "Route 11",
          "Street Road",
          "Prospect Street",
          "Devon Road",
          "Forest Avenue",
          "Elm Avenue",
          "Heather Court",
          "Route 202",
          "Willow Drive",
          "Evergreen Lane",
          "Windsor Drive",
          "Mulberry Street",
          "2nd Street North",
          "Sheffield Drive",
          "Ashley Court",
          "Valley Road",
          "Spruce Street",
          "Wood Street",
          "Bridge Street",
          "Monroe Drive",
          "Church Road",
          "Main Street South",
          "Adams Avenue",
          "Delaware Avenue",
          "Somerset Drive",
          "9th Street",
          "3rd Street East",
          "Augusta Drive",
          "Wall Street",
          "Broad Street",
          "Route 2",
          "Fairview Avenue",
          "5th Avenue",
          "Spruce Avenue",
          "Cemetery Road",
          "River Street",
          "Tanglewood Drive",
          "Cedar Street",
          "Canal Street",
          "Market Street");

  private static final List<String> NAMES = Arrays.asList(
          "Daria Domino",
          "Yukiko Yawn",
          "Diane Dalessio",
          "Elijah Elmore",
          "Tyron Thaler",
          "Stefani Stayton",
          "Shara Stam",
          "Earlean Eblin",
          "Jonell Janecek",
          "Thaddeus Tupper",
          "Dorthy Delany",
          "Shanel Saffell",
          "Ricki Rost",
          "Kandy Knecht",
          "Mirella Ma",
          "Rocky Ros",
          "Theo Tosi",
          "Marnie Manzer",
          "Talisha Trottier",
          "Thanh Tamez",
          "Hyacinth Hotard",
          "Dianne Dearman",
          "Marc Medlin",
          "Roxane Rossin",
          "Mellie Marino",
          "Eugene Eng",
          "Charis Curnutt",
          "Lillia Leming",
          "Gretta Gately",
          "Jessica Johnsen",
          "Raven Riera",
          "Roni Rost",
          "Garth Gipson",
          "Demetrice Dell",
          "Eugenie Ead",
          "Jared Julio",
          "Alaina Aguinald ",
          "Hortense Haun",
          "Willia Woodland",
          "Jessenia Jefferis",
          "Jinny June",
          "William Whang",
          "Dessie Down",
          "Katelyn Koren",
          "Barbara Bazaldua",
          "France Frese",
          "Eufemia Evangelista",
          "Neva Nock",
          "Rickey Redd",
          "Norma Nagler");


}
