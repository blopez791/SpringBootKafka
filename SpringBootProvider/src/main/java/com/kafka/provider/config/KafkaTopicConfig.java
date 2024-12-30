package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic generateTopic(){

        Map<String,String> configuration = new HashMap<>();
        configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE); //delete borra por completo el mensaje, compact mantiene el mas actual
        configuration.put(TopicConfig.RETENTION_MS_CONFIG,"86400000"); // cuanto tiempo(milisegundos) se va a retener un mensaje, por defecto viene -1 es decir que no se van a borrar
        configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824"); // tamaño maximo del segmento, por defecto viene un 1GB, se debe establecer en bytes
        configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"1000012"); // tamaño maximo del mensaje, por defecto vienen en 1MB

        return TopicBuilder.name("kafkaTopicSpringBoot")
                .partitions(2)
                .replicas(2)
                .configs(configuration)
                .build();
    }
}
