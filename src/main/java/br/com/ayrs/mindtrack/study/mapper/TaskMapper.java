package br.com.ayrs.mindtrack.study.mapper;

import br.com.ayrs.mindtrack.study.domain.Subject;
import br.com.ayrs.mindtrack.study.domain.Task;
import br.com.ayrs.mindtrack.study.dto.SubjectRequestDto;
import br.com.ayrs.mindtrack.study.dto.SubjectResponseDto;
import br.com.ayrs.mindtrack.study.dto.TaskRequestDto;
import br.com.ayrs.mindtrack.study.dto.TaskResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDto taskDto) {
        if (taskDto == null) {
            return null;
        }
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        return task;
    }

    public TaskResponseDto toDto(Task task) {

        if (task == null) {
            return null;
        }
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription());
    }

}
