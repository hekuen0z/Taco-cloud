package edu.taco_cloud.repositories;

import edu.taco_cloud.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
