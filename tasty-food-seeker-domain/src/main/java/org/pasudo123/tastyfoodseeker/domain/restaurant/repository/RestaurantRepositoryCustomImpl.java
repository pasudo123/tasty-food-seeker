package org.pasudo123.tastyfoodseeker.domain.restaurant.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Gu;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.pasudo123.tastyfoodseeker.domain.restaurant.model.QRestaurant.restaurant;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantRepositoryCustomImpl implements RestaurantRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Restaurant> fetchByGuAndPager(Gu paramGu, PageRequest pageRequest) {

        final QueryResults<Restaurant> results = jpaQueryFactory
                .selectFrom(restaurant)
                .where(eqGu(paramGu))
                .orderBy(restaurant.name.asc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageRequest, results.getTotal());
    }

    private BooleanExpression eqGu(final Gu gu) {
        if(gu.isAll()) {
            return null;
        }

        return restaurant.gu.eq(gu);
    }
}
