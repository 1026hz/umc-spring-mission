package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.validation.annotation.ExistStore;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;
    private final ReviewCommandService reviewCommandService;

    @PostMapping("")
    @Operation(summary = "특정 지역에 store 추가 API")
    public ApiResponse<Long> createStore(@RequestBody @Valid StoreRequestDTO.CreateStoreDto request) {
        Long storeId = storeCommandService.createStore(request);

        return ApiResponse.onSuccess(storeId);
    }

    //특정 가게 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @ValidPage @RequestParam(name = "page") Integer page){
        page =-1;
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        System.out.println("page"+page);

        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    //리뷰 생성
    @Operation(summary = "리뷰 생성 api")
    @PostMapping("/reviews")
    public ApiResponse<Long> createReview(@RequestBody @Valid ReviewRequestDTO.CreateReviewDto request) {
        Long reviewId = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(reviewId);
    }

}