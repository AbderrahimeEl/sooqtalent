package net.elm.sooqtalent.skill;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepo;

    public List<Skill> getAllSkills() {
        return skillRepo.findAll();
    }

    @Transactional
    public Skill createSkill(String name) {
        if(skillRepo.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Skill with name " + name + " already exists");
        }
        else
        return skillRepo.save(Skill.builder().name(name).build());
    }
}
