package umc.study.web.dto.mission;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MissionResponseDTO {
    private Long id;
    private Long storeId;
    private String missionSpec;
    private Integer reward;
    private LocalDate deadline;
}
