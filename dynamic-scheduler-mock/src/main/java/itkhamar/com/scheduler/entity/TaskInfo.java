package itkhamar.com.scheduler.entity;

import itkhamar.com.scheduler.enums.TaskType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "task_info")
public class TaskInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_id", unique = true)
    private String taskId;

    @Column(name = "task_details")
    private String taskDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskType taskType;

    @Column(name = "type_value")
    private String typeValue;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "task_status")
    private boolean taskStatus;
}
