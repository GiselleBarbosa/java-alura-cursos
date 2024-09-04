package com.giselle.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {

    public static void main(String[] args) {
        var fraudService = new FraudDetectorService();

        try (var service = new KafkaService<Order>(FraudDetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                fraudService::parse, Order.class)) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("------------------------------------------");
        System.out.println("Processando novo pedido. Checando risco de fraude.");
        System.out.println("record.key " + record.key());
        System.out.println("record.value " + record.value());
        System.out.println("record.partition "+record.partition());
        System.out.println("record.offset() " + record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace(System.err);
        }
        System.out.println("Pedido processado!");
    }
}