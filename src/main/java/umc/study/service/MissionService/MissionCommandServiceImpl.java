package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

    // 가게에 미션 추가
    @Transactional
    @Override
    public Long addMissionToStore(MissionRequestDTO.CreateMissionDto request) {
        // 가게 정보 확인
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // 리워드 디폴트 500
        Integer reward = (request.getReward() != null) ? request.getReward() : 500;

        // 미션 생성
        Mission mission = Mission.builder()
                .reward(reward)
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();

        missionRepository.save(mission);

        return mission.getId();
    }
    // 미션 도전 ->memberMission에 추가하는
    @Transactional
    @Override
    public Long startMission(@Valid MissionRequestDTO.StartMissionDto request) {
        // 미션이 진행 중인지 체크
        Long missionId = request.getMissionId();

        // MissionInProgress 애너테이션에 의해 미션 진행 여부가 자동 검증됨
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션이 존재하지 않습니다."));

        // 멤버 확인
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        // MemberMission 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING) // 상태를 CHALLENGING으로 설정
                .build();

        // MemberMission 저장
        memberMissionRepository.save(memberMission);

        return memberMission.getId();
    }

}