package com.example.Library_Management.specification;

import com.example.Library_Management.entity.User;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class UserSpecification {

    public static Specification<User> build(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String likeSearch = "%" + search.toLowerCase() + "%";

            Predicate firstnamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstname")), likeSearch);
            Predicate lastnamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastname")), likeSearch);
            Predicate emailPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), likeSearch);
            Predicate rolePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("role").as(String.class)), likeSearch);

            return criteriaBuilder.or(firstnamePredicate, lastnamePredicate, emailPredicate, rolePredicate);
        };
    }
}
