package net.elm.sooqtalent.projectApplication;

import net.elm.sooqtalent.freelancer.FreelancerProfile;
import net.elm.sooqtalent.project.Project;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, Long> {
    List<ProjectApplication> findAllByFreelancerIdAndStatus(Long freelancerId, ApplicationStatus status);
    @Modifying
    @Query("UPDATE ProjectApplication a SET a.status = 'REJECTED' " +
            "WHERE a.project = :project AND a.id <> :acceptedId")
    void rejectOtherApplications(@Param("project") Project project,
                                 @Param("acceptedId") Long acceptedId);

    boolean existsByProjectIdAndFreelancerId(Long id, Long id1);
    boolean existsByProjectAndFreelancer(Project project, FreelancerProfile freelancer);
    Page<ProjectApplication> findAllByProjectId(Long projectId, Pageable pageable);
    Page<ProjectApplication> findAllByFreelancerId(Long freelancerId, Pageable pageable);
}
