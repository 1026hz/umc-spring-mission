package umc.study.service.MissionService;


import umc.study.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Long addMissionToStore(MissionRequestDTO.CreateMissionDto request);
    Long startMission(MissionRequestDTO.StartMissionDto request);
}