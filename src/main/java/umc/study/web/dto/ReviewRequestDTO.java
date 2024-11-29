package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewDto {
        @Schema(description="리뷰 제목")
        @NotBlank
        private String title;
        @Schema(description="리뷰 점수")
        @NotNull
        private Float score;
        @Schema(description="가게 id")
        @NotNull
        private Long storeId;
    }
}