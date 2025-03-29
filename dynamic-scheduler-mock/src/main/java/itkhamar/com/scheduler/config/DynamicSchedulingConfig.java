package itkhamar.com.scheduler.config;

import itkhamar.com.scheduler.enums.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicSchedulingConfig {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private TaskScheduler taskScheduler;

    private Map<String, ScheduledFuture<?>> taskLive = new HashMap<>();

    public void scheduleCronTask(String taskId, String cronExp, String methodName, String className) throws Exception {
        Runnable task = () -> {
            try {
                Object instance = context.getBean(className);
                Method method = instance.getClass().getDeclaredMethod(methodName);
                method.invoke(instance);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };

        ScheduledFuture<?> future = taskScheduler.schedule(task, new CronTrigger(cronExp));
        taskLive.put(taskId, future);
    }

    public void scheduleFixedRateTask(String taskId, long milliSeconds, String methodName, String className) throws Exception {
        Runnable task = () -> {
            try {
                Object instance = context.getBean(className);
                Method method = instance.getClass().getDeclaredMethod(methodName);
                method.invoke(instance);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };

        ScheduledFuture<?> future = taskScheduler.scheduleAtFixedRate(task, milliSeconds);
        taskLive.put(taskId, future);
    }

    public void scheduleFixedDelayTask(String taskId, long milliSeconds, String methodName, String className) throws Exception {
        Runnable task = () -> {
            try {
                Object instance = context.getBean(className);
                Method method = instance.getClass().getDeclaredMethod(methodName);
                method.invoke(instance);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };

        ScheduledFuture<?> future = taskScheduler.scheduleWithFixedDelay(task, milliSeconds);
        taskLive.put(taskId, future);
    }

    public void cancelTask(String taskId) throws Exception {
        ScheduledFuture<?> future = taskLive.get(taskId);

        if (future != null) {
            future.cancel(true);
            taskLive.remove(taskId);
        }
    }

    public void enableTask(TaskConfig taskConfig) throws Exception {
        if (taskConfig != null) {
            if (taskConfig.getTaskType().equals(TaskType.CRON)) {
                scheduleCronTask(taskConfig.getTaskId(), taskConfig.getCronExp(), taskConfig.getMethodName(), taskConfig.getClassName());
            } else if (taskConfig.getTaskType().equals(TaskType.FIXED_RATE)) {
                scheduleFixedRateTask(taskConfig.getTaskId(), taskConfig.getMilliSeconds(), taskConfig.getMethodName(), taskConfig.getClassName());
            } else if (taskConfig.getTaskType().equals(TaskType.FIXED_DELAY)) {
                scheduleFixedDelayTask(taskConfig.getTaskId(), taskConfig.getMilliSeconds(), taskConfig.getMethodName(), taskConfig.getClassName());
            }
        }
    }

    public void cancelAllTasks() {
        for (ScheduledFuture<?> future : taskLive.values()) {
            future.cancel(true);
        }

        taskLive.clear();
    }
}
