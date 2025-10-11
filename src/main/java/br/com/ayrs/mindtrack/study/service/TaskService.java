package br.com.ayrs.mindtrack.study.service;

import br.com.ayrs.mindtrack.study.domain.Subject;
import br.com.ayrs.mindtrack.study.domain.Task;
import br.com.ayrs.mindtrack.study.dto.TaskRequestDto;
import br.com.ayrs.mindtrack.study.dto.TaskResponseDto;
import br.com.ayrs.mindtrack.study.mapper.TaskMapper;
import br.com.ayrs.mindtrack.study.repository.SubjectRepository;
import br.com.ayrs.mindtrack.study.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final SubjectRepository subjectRepository;

    // Injeção via Construtor
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, SubjectRepository subjectRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.subjectRepository = subjectRepository;
    }

    public TaskResponseDto criaTask(TaskRequestDto taskDto) {
        // Busca o Subject pelo ID informado no DTO
        Subject subject = subjectRepository.findById(taskDto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Matéria com ID " + taskDto.getSubjectId() + " não encontrada!"));

        // Converte o DTO para entidade e atribui o Subject
        Task task = taskMapper.toEntity(taskDto);
        task.setSubject(subject);

        // Salva e retorna o DTO de resposta
        Task taskSalva = taskRepository.save(task);
        return taskMapper.toDto(taskSalva);
    }

    public Page<TaskResponseDto> buscaTodasTasks(Pageable pageable) {
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return taskPage.map(taskMapper::toDto);
    }

    public TaskResponseDto buscaTaskPorId(Long id) {
        Task taskEncontrada = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task com ID " + id + " não encontrada!"));
        return taskMapper.toDto(taskEncontrada);
    }

    public TaskResponseDto atualizaTask(Long id, TaskRequestDto taskDto) {
        Task taskExistente = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task com ID " + id + " não pode ser atualizada: não encontrada!"));
        taskExistente.setTitle(taskDto.getTitle());
        taskExistente.setDescription(taskDto.getDescription());
        if (!taskExistente.getSubject().getId().equals(taskDto.getSubjectId())) {
            Subject novoSubject = subjectRepository.findById(taskDto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Matéria com ID " + taskDto.getSubjectId() + " não encontrada!"));
            taskExistente.setSubject(novoSubject);
        }

        Task taskAtualizada = taskRepository.save(taskExistente);
        return taskMapper.toDto(taskAtualizada);
    }

    /**
     * Deleta uma task pelo ID.
     * Inclui a lógica de checagem de existência antes de deletar.
     */
    public void deletaTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task com ID " + id + " não pode ser deletada: não encontrada!");
        }
        taskRepository.deleteById(id);
    }
}