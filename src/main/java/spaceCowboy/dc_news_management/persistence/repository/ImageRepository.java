package spaceCowboy.dc_news_management.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spaceCowboy.dc_news_management.persistence.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {}

