package itkhamar.com.scheduler.service;

import itkhamar.com.scheduler.repo.TaskInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskInfoService {

    @Autowired
    private TaskInfoRepo taskInfoRepo;
}
