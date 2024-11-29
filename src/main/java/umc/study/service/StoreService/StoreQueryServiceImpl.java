package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Region;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.RegionRepository.RegionRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final RegionRepository regionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    //특정 가게 리뷰 가져오기
    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
    //특정 멤버 리뷰 가져오기
    @Override
    public Page<Review> getMemberReviewList(Long MemberId, Integer page) {
        //멤버 하드코딩
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
    }

    //특정 멤버 진행중인 미션 목록 가져오기
    @Override
    public Page<MemberMission> getMemberMissionList(Long MemberId, Integer page) {
        //멤버 하드코딩
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMember(member, PageRequest.of(page, 10));
        System.out.println("Fetched MemberMission: " + memberMissionPage.getContent());
        System.out.println("Total elements: " + memberMissionPage.getTotalElements());
        System.out.println("Content size: " + memberMissionPage.getContent().size());
        return memberMissionPage;
    }
}