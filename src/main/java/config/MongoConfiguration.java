package config;

import static java.util.Arrays.asList;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(MongoClientSettings.builder()
        .applyToClusterSettings(builder -> 
            builder.hosts(Arrays.asList(new ServerAddress(host, port))))
        .build());
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    public CustomConversions customConversions() {
        return new MongoCustomConversions(asList(
                new ZonedDateTimeToDocumentConverter(),
                new DocumentToZonedDateTimeConverter()
        ));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initIndicesAfterStartup() {
      // here we should create the indexes
      // Example
      // https://github.com/eugenp/tutorials/blob/master/persistence-modules/java-mongodb/src/test/java/com/baeldung/geo/MongoGeospatialLiveTest.java
    }

}
