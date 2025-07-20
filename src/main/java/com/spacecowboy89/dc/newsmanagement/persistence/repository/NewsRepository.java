package com.spacecowboy89.dc.newsmanagement.persistence.repository;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository {

    @Query(value = """
            select *
            from news
            order by id desc
            limit 15
            """, nativeQuery = true)
    public Optional<List<News>> findLast15News();

}
