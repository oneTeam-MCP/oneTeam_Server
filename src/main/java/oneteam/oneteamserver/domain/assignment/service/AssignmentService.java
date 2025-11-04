package oneteam.oneteamserver.domain.assignment.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.assignment.Assignment;
import oneteam.oneteamserver.domain.assignment.dto.AssignmentResponse;
import oneteam.oneteamserver.domain.assignment.repository.AssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public List<AssignmentResponse> getMyUnsubmittedAssignments(String userId) {
        List<Assignment> assignments =
                assignmentRepository.findMyUnsubmitted(userId, "미제출");

        return assignments.stream()
                .map(AssignmentResponse::of)
                .toList();
    }
}
