package umc.study.web.dto.store;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StoreRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String regionName;
}
