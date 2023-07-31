package am.hitech.service;

import am.hitech.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {

    public static Specification<User> searchUsers(String firstName, String lastName, String email) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (firstName != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("firstName")), firstName.toLowerCase() + "%")
                );
            }

            if (lastName != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("lastName")), lastName.toLowerCase() + "%")
                );
            }

            if (email != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("email")), email.toLowerCase() + "%")
                );
            }

            return predicate;
        };
    }
}
