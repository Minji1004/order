package com.practice.store.user.repository;

import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.model.QUser;
import com.practice.store.user.model.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.practice.store.user.entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class UserQRepository {

    private final JPAQueryFactory queryFactory;

    /*
    Paging 처리 되는 유저 리스트 조회
 */
    public List<User> findByNameAndEmail(String name, String email, Pageable pageable) {
        return queryFactory
                .select(new QUser(userEntity))
                .from(userEntity)
                .where(
                        nameCondition(name),
                        emailCondition(email)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    /*
    검색한 유저 리스트의 전체 수 조회
     */
    public int getTotalCountByNameNadEmail(String name, String email){
        return queryFactory
                .select(userEntity.userId)
                .from(userEntity)
                .where(
                        nameCondition(name),
                        emailCondition(email)
                )
                .fetch().size();
    }

    private BooleanBuilder nameCondition(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        BooleanBuilder builder = new BooleanBuilder();
        return builder.and(userEntity.name.containsIgnoreCase(name));
    }

    private BooleanBuilder emailCondition(String email) {
        if (!StringUtils.hasText(email)) {
            return null;
        }

        BooleanBuilder builder = new BooleanBuilder();
        return builder.and(userEntity.email.containsIgnoreCase(email));
    }
}
