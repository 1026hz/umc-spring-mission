package umc.study.web.dto.store;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StoreResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String regionName;
}