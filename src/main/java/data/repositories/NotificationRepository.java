package data.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Notification;

public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {
}
