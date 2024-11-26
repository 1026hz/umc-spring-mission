package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.repository.MemberMissionRepository;
import umc.study.validation.annotation.MissionChallenge;

@Component
@RequiredArgsConstructor
public class MissionChallengeValidator implements ConstraintValidator<MissionChallenge, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        return !memberMissionRepository.existsByMissionId(missionId);
    }
}