package umc.study.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionConverter {
    //member mission converter
    public static MissionResponseDTO.MemberMissionPreViewDTO missionPreViewDTO(MemberMission mission){
        if (mission == null || mission.getMember() == null || mission.getMission() == null) {
            System.out.println("Invalid mission data: " + mission);
            return null;
        }
        return MissionResponseDTO.MemberMissionPreViewDTO.builder()
                .memberName(mission.getMember().getNickname())
                .storeName(mission.getMission().getStore().getName())
                .reward(mission.getMission().getReward())
                .missionSpec(mission.getMission().getMissionSpec())
//                .deadlineAt(mission.getMission().getDeadline())
                .build();
    }
    public static MissionResponseDTO.MemberMissionPreViewListDTO missionPreViewListDTO(Page<MemberMission> missionList){
        List<MissionResponseDTO.MemberMissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MissionConverter::missionPreViewDTO).collect(Collectors.toList());
        return MissionResponseDTO.MemberMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}