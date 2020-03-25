package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Notification;
import data.entities.UserApp;

public interface NotificationRepository extends MongoRepository<Notification, String> {
  Notification findByUser (UserApp user);
}
