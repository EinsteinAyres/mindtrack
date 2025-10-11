package br.com.ayrs.mindtrack.study.controller;

import br.com.ayrs.mindtrack.study.dto.TaskRequestDto;
import br.com.ayrs.mindtrack.study.dto.TaskResponseDto;
import br.com.ayrs.mindtrack.study.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> criaTask(@Valid @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto taskResponseDto = taskService.criaTask(taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<Page<TaskResponseDto>> buscaTodasTasks(Pageable pageable) {
        Page<TaskResponseDto> page = taskService.buscaTodasTasks(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> buscaTaskPorId(@PathVariable Long id) {
        TaskResponseDto taskResponseDto = taskService.buscaTaskPorId(id);
        return ResponseEntity.ok(taskResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> atualizaMateria(
            @PathVariable Long id,
            @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto taskResponseDto = taskService.atualizaTask(id, taskRequestDto);
        return ResponseEntity.ok(taskResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaTask(@PathVariable Long id) {
        taskService.deletaTask(id);
        return ResponseEntity.noContent().build();
    }

}
