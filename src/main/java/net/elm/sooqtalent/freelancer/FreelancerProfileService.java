package net.elm.sooqtalent.freelancer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.exception.*;
import net.elm.sooqtalent.project.*;
import net.elm.sooqtalent.projectApplication.*;
import net.elm.sooqtalent.skill.Skill;
import net.elm.sooqtalent.skill.SkillRepository;
import net.elm.sooqtalent.skill.SkillService;
import net.elm.sooqtalent.user.Role;
import net.elm.sooqtalent.user.User;
import net.elm.sooqtalent.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FreelancerProfileService {
    private final FreelancerProfileRepository freelancerRepo;
    private final SkillRepository skillRepo;
    private final UserRepository userRepo;
    private final SkillService skillService;

    @Transactional
    public FreelancerProfileResponse createProfile(FreelancerProfileRequest request, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(user.getRole() != Role.FREELANCER) {
            throw new UnauthorizedRoleException("User must have FREELANCER role to create profile");
        }

        if(freelancerRepo.existsByUser(user)) {
            throw new ProfileExistsException("Freelancer profile already exists for this user");
        }

        Set<Skill> skills = request.getSkills().stream()
                .map(skillName -> skillRepo.findByName(skillName)
                        .orElseGet(() -> skillService.createSkill(skillName))
                )
                .collect(Collectors.toSet());

        FreelancerProfile profile = FreelancerProfile.builder()
                .title(request.getTitle())
                .skills(skills)
                .education(request.getEducation())
                .certifications(request.getCertifications())
                .githubUrl(request.getGithubUrl())
                .hourlyRate(request.getHourlyRate())
                .user(user)
                .build();

        FreelancerProfile savedProfile = freelancerRepo.save(profile);
        return FreelancerProfileMapper.toResponse(savedProfile);
    }

    public FreelancerProfileResponse getProfile(Long id) {
        return freelancerRepo.findById(id)
                .map(FreelancerProfileMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer profile not found"));
    }

    public FreelancerProfileResponse getProfileByUserId(Long userId) {
        return freelancerRepo.findByUserId(userId)
                .map(FreelancerProfileMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer profile not found"));
    }

    @Transactional
    public FreelancerProfileResponse updateProfile(Long id, FreelancerProfileRequest request) {
        FreelancerProfile profile = freelancerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer profile not found"));

        Set<Skill> skills = request.getSkills().stream()
                .map(skillName -> skillRepo.findByName(skillName)
                        .orElseGet(() -> skillService.createSkill(skillName))
                )
                .collect(Collectors.toSet());

        profile.setTitle(request.getTitle());
        profile.setSkills(skills);
        profile.setEducation(request.getEducation());
        profile.setCertifications(request.getCertifications());
        profile.setGithubUrl(request.getGithubUrl());
        profile.setHourlyRate(request.getHourlyRate());

        return FreelancerProfileMapper.toResponse(freelancerRepo.save(profile));
    }

    @Transactional
    public void deleteProfile(Long id) {
        if(!freelancerRepo.existsById(id)) {
            throw new ResourceNotFoundException("Freelancer profile not found");
        }
        freelancerRepo.deleteById(id);
    }
}