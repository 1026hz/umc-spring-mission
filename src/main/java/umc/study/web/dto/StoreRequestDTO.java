package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class CreateStoreDto {

        @NotBlank
        @Size(max = 20)
        private String name;

        @NotBlank
        @Size(max = 20)
        private String address;

        private Float score;

        @NotNull
        private Long regionId;
    }
}