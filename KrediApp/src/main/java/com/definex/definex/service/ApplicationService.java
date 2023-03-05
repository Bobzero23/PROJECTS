package com.definex.definex.service;


import com.definex.definex.dto.ApplicationDto;

import java.util.List;
import java.util.Optional;



public interface ApplicationService {
    List<ApplicationDto> findAllApplicants();
}
