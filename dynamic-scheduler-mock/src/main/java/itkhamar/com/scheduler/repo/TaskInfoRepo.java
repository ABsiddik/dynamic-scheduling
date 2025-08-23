package itkhamar.com.scheduler.repo;

import itkhamar.com.scheduler.entity.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskInfoRepo extends JpaRepository<TaskInfo, Integer> {
}
