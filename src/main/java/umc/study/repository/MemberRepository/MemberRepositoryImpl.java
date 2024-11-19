package umc.study.repository.MemberRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.study.domain.Member;
import umc.study.domain.QMember;
import umc.study.domain.QReview;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Member findMemberDetails(Long memberId) {
        QMember member = QMember.member;
        QReview review = QReview.review;

        return jpaQueryFactory
                .selectFrom(member)
                .leftJoin(member.reviewList, review).fetchJoin()
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}