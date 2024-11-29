package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.MissionRequestDTO;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;
    @Operation(summary = "미션 생성 api")
    @PostMapping("/add")
    public ApiResponse<Long> addMissionToStore(@RequestBody @Valid MissionRequestDTO.CreateMissionDto request) {
        Long missionId = missionCommandService.addMissionToStore(request);
        return ApiResponse.onSuccess(missionId);
    }

    @Operation(summary = "미션 진행 중으로 변경 api")
    @PostMapping("/start")
    public ApiResponse<Long> startMission(@RequestBody @Valid MissionRequestDTO.StartMissionDto request) {
        Long memberMissionId = missionCommandService.startMission(request);
        return ApiResponse.onSuccess(memberMissionId);
    }


}