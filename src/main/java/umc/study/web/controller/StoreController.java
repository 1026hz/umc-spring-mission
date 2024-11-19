package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.web.dto.store.StoreRequestDTO;
import umc.study.web.dto.store.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreQueryService storeQueryService;

    @Operation(summary = "특정 지역에 가게 추가하기 API")
    @PostMapping("/stores/new")
    public ApiResponse<StoreResponseDTO> addStore(@RequestBody @Valid StoreRequestDTO storeRequest) {
        StoreResponseDTO response = storeQueryService.addStore(storeRequest);
        return ApiResponse.onSuccess(response);
    }
}