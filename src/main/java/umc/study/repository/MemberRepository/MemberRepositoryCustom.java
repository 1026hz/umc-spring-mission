package umc.study.repository.MemberRepository;

import umc.study.domain.Member;

public interface MemberRepositoryCustom {
    Member findMemberDetails(Long memberId);
}
