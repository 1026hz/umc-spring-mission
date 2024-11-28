package umc.study.service.Mission;

import umc.study.web.dto.mission.MissionRequestDTO;
import umc.study.web.dto.mission.MissionResponseDTO;

public interface MissionCommandService {
    MissionResponseDTO addMission(Long storeId, MissionRequestDTO request);
}
