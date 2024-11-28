package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.store.StoreRequestDTO;
import umc.study.web.dto.store.StoreResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        // `dynamicQueryWithBooleanBuilder`는 QueryDSL을 기반으로 동작하는 메서드로, StoreRepository에 정의되어야 합니다.
        return storeRepository.dynamicQueryWithBooleanBuilder(name, score);
    }

    @Override
    @Transactional
    public StoreResponseDTO addStore(StoreRequestDTO storeRequest) {
        // 지역 확인 및 가져오기
        Region region = (Region) regionRepository.findByName(storeRequest.getRegionName())
                .orElseThrow(() -> new RuntimeException("해당 지역이 존재하지 않습니다."));

        // 가게 저장
        Store store = Store.builder()
                .name(storeRequest.getName())
                .address(storeRequest.getAddress())
                .region(region)
                .build();

        storeRepository.save(store);

        // 응답 DTO 생성
        return StoreResponseDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .regionName(region.getName())
                .build();
    }
}