package com.spacecowboy89.dc.newsmanagement.persistence.repository;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

    @Query(value = """
            select *
            from news
            order by id desc
            limit 15
            """, nativeQuery = true)
    public Optional<List<News>> findLast15News();

    public Optional<News> findByNewsCode(String newsCode);



    @Query(value = """
            select *
            from news
            where publicationDate beetwen :firstPublicationDate AND :secondPublicationDate
            """, nativeQuery = true)
    public Optional<List<News>> findByBeetwen2PublicationDate(LocalDateTime firstPublicationDate, LocalDateTime secondPublicationDate);

}
