package umc.study.repository.MissionRepository;

import umc.study.domain.Mission;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findMissionsByStatus(Long memberId, MissionStatus status, int offset, int limit);
    List<Mission> findAvailableMissionsByRegion(Long regionId, MissionStatus status, int offset, int limit);
}