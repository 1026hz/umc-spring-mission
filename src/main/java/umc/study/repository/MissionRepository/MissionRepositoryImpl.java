package umc.study.repository.MissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.study.domain.Mission;
import umc.study.domain.QMission;
import umc.study.domain.QStore;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.QMemberMission;

import java.util.List;

@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Mission> findMissionsByStatus(Long memberId, MissionStatus status, int offset, int limit) {
        QMission mission = QMission.mission;
        QMemberMission memberMission = QMemberMission.memberMission;
        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.memberMissionList, memberMission)
                .where(memberMission.member.id.eq(memberId)
                        .and(memberMission.status.eq(status)))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Mission> findAvailableMissionsByRegion(Long regionId, MissionStatus status, int offset, int limit) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QMemberMission memberMission = QMemberMission.memberMission;

        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.store, store)
                .join(mission.memberMissionList, memberMission)  // MemberMission과 join
                .where(store.region.id.eq(regionId)
                        .and(memberMission.status.eq(status)))  // MemberMission의 status 조건
                .offset(offset)
                .limit(limit)
                .fetch();
    }

}