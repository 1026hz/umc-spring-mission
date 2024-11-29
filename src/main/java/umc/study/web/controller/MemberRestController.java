package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.converter.MemberConverter;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.validation.annotation.ExistStore;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final StoreQueryService storeQueryService;


    @Operation(summary = "회원가입 API")
    @PostMapping("/")
    public umc.study.apiPayload.ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return umc.study.apiPayload.ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
    //내가 작성한 리뷰 목록 조회
    @GetMapping("{memberId}/reviews")
    @Operation(summary = "내가 쓴 리뷰 목록 조회 API",description = "내가 쓴 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버 아이디, path variable 입니다")
    })
    public umc.study.apiPayload.ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getMemberReviewList(
            @ExistStore @PathVariable(name = "memberId") Long memberId,
            @ValidPage @RequestParam(name = "page") Integer page){

        Page<Review> reviewList = storeQueryService.getMemberReviewList(memberId, page);
        return umc.study.apiPayload.ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    //내가 진행중인 미션 목록 조회
    @GetMapping("{memberId}/missions")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API",description = "내가 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버 아이디, path variable 입니다")
    })
    public umc.study.apiPayload.ApiResponse<MissionResponseDTO.MemberMissionPreViewListDTO> getMemberMissionList(
            @PathVariable(name = "memberId") Long memberId,
            @ValidPage @RequestParam(name = "page") Integer page){
//        page = page -1;
        Page<MemberMission> missionList=storeQueryService.getMemberMissionList(memberId,page);
        System.out.println("page"+page);
        return umc.study.apiPayload.ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(missionList));
    }
}