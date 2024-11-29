package umc.study.service.StoreService;

import umc.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Long createStore(StoreRequestDTO.CreateStoreDto request);
}