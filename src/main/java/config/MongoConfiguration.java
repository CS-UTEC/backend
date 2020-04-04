package config;

import static java.util.Arrays.asList;

import java.util.Arrays;

import org.bson.Document;
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
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

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
                           .applyToClusterSettings(builder -> builder
                               .hosts(Arrays.asList(new ServerAddress(host, port))))
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
        MongoClient mongoClient = mongoClient();
        MongoDatabase db = mongoClient.getDatabase(database);
        MongoCollection<Document> ubication = db.getCollection("ubication");
        MongoCollection<Document> department = db.getCollection("department");
        MongoCollection<Document> province = db.getCollection("province");
        MongoCollection<Document> district = db.getCollection("district");
        MongoCollection<Document> userApp = db.getCollection("user_app");
        ubication.createIndex(Indexes.geo2dsphere("location"));
        department.createIndex(Indexes.geo2dsphere("geometry"));
        province.createIndex(Indexes.geo2dsphere("geometry"));
        district.createIndex(Indexes.geo2dsphere("geometry"));
        userApp.createIndex(Indexes.descending("timestamp"));
    }

}
