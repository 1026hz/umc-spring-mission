package umc.study.web.dto.review;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewResponseDTO {
    private Long id;
    private Long storeId;
    private Long memberId;
    private String body;
    private Float score;
}