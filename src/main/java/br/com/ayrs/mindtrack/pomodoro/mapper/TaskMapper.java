package br.com.ayrs.mindtrack.pomodoro.mapper;

import br.com.ayrs.mindtrack.pomodoro.domain.Session;
import br.com.ayrs.mindtrack.pomodoro.dto.TaskRequestDto;
import br.com.ayrs.mindtrack.pomodoro.dto.TaskResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Session toEntity(TaskRequestDto taskDto) {
        if (taskDto == null) {
            return null;
        }
        Session task = new Session();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        return task;
    }

    public TaskResponseDto toDto(Session task) {

        if (task == null) {
            return null;
        }
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription());
    }

}
