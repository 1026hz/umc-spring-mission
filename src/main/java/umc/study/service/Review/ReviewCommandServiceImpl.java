package umc.study.service.Review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.review.ReviewRequestDTO;
import umc.study.web.dto.review.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
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

    @Override
    @Transactional
    public ReviewResponseDTO addReview(Long storeId, ReviewRequestDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();

        reviewRepository.save(review);

        return ReviewResponseDTO.builder()
                .id(review.getId())
                .storeId(store.getId())
                .memberId(member.getId())
                .body(review.getBody())
                .score(review.getScore())
                .build();
    }
}