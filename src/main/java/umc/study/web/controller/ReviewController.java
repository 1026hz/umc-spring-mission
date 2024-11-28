package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.Review.ReviewCommandService;
import umc.study.web.dto.review.ReviewRequestDTO;
import umc.study.web.dto.review.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewController {

    private final ReviewCommandService reviewQueryService;

    @Operation(summary = "가게에 리뷰 추가 API")
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO> addReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewRequestDTO reviewRequest
    ) {
        ReviewResponseDTO response = reviewQueryService.addReview(storeId, reviewRequest);
        return ApiResponse.onSuccess(response);
    }
}