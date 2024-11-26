package umc.study.web.dto.mission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionRequestDTO {
    private String missionSpec;
    private Integer reward;
    private LocalDate deadline;
}