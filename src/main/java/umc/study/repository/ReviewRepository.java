package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
