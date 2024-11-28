package umc.study.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.MissionRepository.MissionRepositoryCustom;
import umc.study.web.dto.MemberMissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionChallengeService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberMissionResponseDTO challengeMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        // 하드 코딩된 유저
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (memberMissionRepository.existsByMissionAndMember(mission, member)) {
            throw new RuntimeException("이미 도전 중인 미션입니다.");
        }

        MemberMission memberMission = MemberMission.builder()
                .mission(mission)
                .member(member)
                .status(MissionStatus.CHALLENGING)
                .build();

        memberMissionRepository.save(memberMission);

        return MemberMissionResponseDTO.builder()
                .missionId(mission.getId())
                .memberId(member.getId())
                .status(memberMission.getStatus())
                .build();
    }
}
