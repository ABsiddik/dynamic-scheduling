package itkhamar.com.scheduler.config;

import itkhamar.com.scheduler.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskConfig {
    private String taskId;
    private String cronExp;
    private long milliSeconds;
    private String methodName;
    private String className;
    private TaskType taskType;
}
