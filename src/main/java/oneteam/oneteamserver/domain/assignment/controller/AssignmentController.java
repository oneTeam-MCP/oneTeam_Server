package oneteam.oneteamserver.domain.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.assignment.dto.AssignmentResponse;
import oneteam.oneteamserver.domain.assignment.service.AssignmentService;
import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.global.annotation.CurrentMember;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "과제 API", description = "이캠퍼스 과제 관련 API 입니다.")
@RequestMapping("/api/v1/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("/unsubmitted")
    @Operation(
            summary = "내 미제출 과제 조회",
            description = "로그인한 사용자의 미제출 과제( submission_status = \"미제출\" )만 전부 조회합니다."
    )
    public List<AssignmentResponse> getMyUnsubmittedAssignments(
            @CurrentMember Member member
    ) {
        return assignmentService.getMyUnsubmittedAssignments(member.getStudentId());
    }
}
