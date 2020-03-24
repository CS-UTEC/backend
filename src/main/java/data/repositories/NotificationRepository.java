package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
