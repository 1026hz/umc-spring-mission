package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.member.MemberRequestDTO;
import umc.study.web.dto.member.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto joinDto) {
        return Member.builder()
                .name(joinDto.getName())
                .gender(Gender.values()[joinDto.getGender()])
                .email("default@email.com")
                .address(joinDto.getAddress())
                .specAddress(joinDto.getSpecAddress())
                .inactiveDate(null)
                .point(0)
                .build();
    }
}
