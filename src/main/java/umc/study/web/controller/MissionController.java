package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.Mission.MissionCommandService;
import umc.study.service.MissionChallengeService;
import umc.study.validation.annotation.MissionChallenge;
import umc.study.web.dto.MemberMissionResponseDTO;
import umc.study.web.dto.mission.MissionRequestDTO;
import umc.study.web.dto.mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionController {

    private final MissionCommandService missionService;
    private final MissionChallengeService missionChallengeService;

    @Operation(summary = "가게에 미션 추가 API")
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO> addMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionRequestDTO missionRequest
    ) {
        MissionResponseDTO response = missionService.addMission(storeId, missionRequest);
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "가게의 미션을 도전 중인 미션에 추가 API")
    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponseDTO> challengeMission(
            @RequestParam @MissionChallenge Long missionId
    ) {
        MemberMissionResponseDTO response = missionChallengeService.challengeMission(missionId);
        return ApiResponse.onSuccess(response);
    }
}
