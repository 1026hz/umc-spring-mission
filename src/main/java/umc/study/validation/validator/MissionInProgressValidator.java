package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.enums.MissionStatus;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.validation.annotation.MissionInProgress;

@Component
@RequiredArgsConstructor
public class MissionInProgressValidator implements ConstraintValidator<MissionInProgress, Long> {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        Long memberId = 2L; //하드코딩
        //해당 미션이 존재하는지
        boolean missionExists = missionRepository.existsById(missionId);
        if (!missionExists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("해당 미션이 존재하지 않습니다.")
                    .addConstraintViolation();
            return false;
        }
        //해당 미션이 진행중인지
        boolean isInProgress = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(memberId, missionId, MissionStatus.CHALLENGING);

        if (isInProgress) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("해당 미션은 이미 진행 중입니다.")
                    .addConstraintViolation();
            return false;
        }


        return true;
    }
}
