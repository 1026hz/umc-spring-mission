package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.MissionInProgress;

import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    public static class CreateMissionDto {
        @NotNull
        private Long storeId;

        @NotBlank
        private String missionSpec;

        private Integer reward;

        @NotNull
        private LocalDate deadline;

    }

    @Getter
    public static class StartMissionDto {
        @NotNull
        @MissionInProgress
        private Long missionId;

        @NotNull
        private Long memberId;
    }
}
