package com.bob.tasksubmissionservice.implementation;

import com.bob.tasksubmissionservice.model.Submission;
import com.bob.tasksubmissionservice.model.TaskDto;
import com.bob.tasksubmissionservice.repository.SubmissionRepository;
import com.bob.tasksubmissionservice.service.SubmissionService;
import com.bob.tasksubmissionservice.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImplementation implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final TaskService taskService;

    public SubmissionServiceImplementation(SubmissionRepository submissionRepository, TaskService taskService) {
        this.submissionRepository = submissionRepository;
        this.taskService = taskService;
    }

    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception {
        TaskDto task = taskService.getTaskById(taskId, jwt);

        if (task != null) {
            Submission submission = new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGithubLink(githubLink);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepository.save(submission);
        }

        throw new Exception("Task not found with id:- " + taskId);
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return submissionRepository.findById(submissionId).orElseThrow(
                ()-> new Exception("Task submission not found with id:- " + submissionId)
        );
    }

    @Override
    public List<Submission> getAllTaskSubmission() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionsByTaskId(Long taskId) {
        return submissionRepository.findByTaskId(taskId);
    }

    @Override
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
        Submission submission = getTaskSubmissionById(id);
        submission.setStatus(status);

        if (status.equals("ACCEPT")) {
            taskService.completeTask(submission.getTaskId());
        }

        return submissionRepository.save(submission);
    }
}
