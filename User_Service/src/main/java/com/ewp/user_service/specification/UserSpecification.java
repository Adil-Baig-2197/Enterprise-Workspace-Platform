package com.ewp.user_service.specification;

import com.ewp.user_service.model.Users;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<Users> getUserSpecification(String search){
        return new Specification<Users>() {
            @Override
            public @Nullable Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(search==null || search.isEmpty()){
                    return criteriaBuilder.conjunction();
                }
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("name"),"%"+search+"%"));
                // list.add(); later to add something that also require search by

                return criteriaBuilder.or(list.toArray(new Predicate[0]));
            }
        };
    }
}