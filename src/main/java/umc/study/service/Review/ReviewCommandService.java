package umc.study.service.Review;

import umc.study.domain.Review;
import umc.study.web.dto.review.ReviewRequestDTO;
import umc.study.web.dto.review.ReviewResponseDTO;

public interface ReviewCommandService {
    Review saveReview(Long memberId, Long storeId, String reviewBody, Float score);
    ReviewResponseDTO addReview(Long storeId, ReviewRequestDTO request);
}
