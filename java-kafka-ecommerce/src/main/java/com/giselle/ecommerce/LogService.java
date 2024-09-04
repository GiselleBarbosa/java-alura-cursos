package com.giselle.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) {
        var logService = new LogService();

        try (var service = new KafkaService(LogService.class.getSimpleName(),
                Pattern.compile("ECOMMERCE.*"),
                logService::parse, String.class)) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("------------------------------------------");
        System.out.println("LOG: ");
        System.out.println("record.key " + record.key());
        System.out.println("record.value " + record.value());
        System.out.println("record.partition "+record.partition());
        System.out.println("record.offset() " + record.offset());
    }
}