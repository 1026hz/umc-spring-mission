package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.study.domain.enums.MissionStatus;

@Builder
@Getter
public class MemberMissionResponseDTO {
    private Long missionId;
    private Long memberId;
    private MissionStatus status;
}
