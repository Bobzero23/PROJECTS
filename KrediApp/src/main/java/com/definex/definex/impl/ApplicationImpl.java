package com.definex.definex.impl;

import com.definex.definex.dto.ApplicationDto;
import com.definex.definex.model.Application;
import com.definex.definex.repository.ApplicationRepository;
import com.definex.definex.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationImpl implements ApplicationService {
    private ApplicationRepository applicationRepository;

    public ApplicationImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<ApplicationDto> findAllApplicants() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream().map((application) -> mapToApplicationDto(application))
                .collect(Collectors.toList());
    }

    public ApplicationDto mapToApplicationDto(Application application) {
        ApplicationDto applicationDto = ApplicationDto.builder()
                .id(application.getId())
                .name(application.getName())
                .surName(application.getSurName())
                .income(application.getIncome())
                .birthDate(application.getBirthDate())
                .mobileNumber(application.getMobileNumber())
                .build();
        return applicationDto;
    }
}
