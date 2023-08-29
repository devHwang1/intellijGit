package com.busanit.domain;

import com.busanit.entity.Board;
import com.busanit.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
@AllArgsConstructor
public class BoardSpecification {
    private BoardRepository boardRepository;
    public static Specification<Board> equalTitle(String title) {
        return new Specification<Board>() {
            @Override
            public Predicate toPredicate(Root<Board> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("title"),title);
            }
        };
    }
    public static Specification<Board> equalContent(String content) {
        return new Specification<Board>() {
            @Override
            public Predicate toPredicate(Root<Board> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("content"),"%"+content+"%");
            }
        };
    }




}
