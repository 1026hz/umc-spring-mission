package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Review;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Review saveReview(Long memberId, Long storeId, String reviewBody, Float score) {
        Review review = Review.builder()
                .member(memberRepository.findById(memberId).orElseThrow())
                .store(storeRepository.findById(storeId).orElseThrow())
                .body(reviewBody)
                .score(score)
                .build();

        return reviewRepository.save(review);
    }
}