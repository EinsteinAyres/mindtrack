package br.com.ayrs.mindtrack.pomodoro.service;

import br.com.ayrs.mindtrack.pomodoro.domain.Session;
import br.com.ayrs.mindtrack.pomodoro.dto.SessionRequestDto;
import br.com.ayrs.mindtrack.pomodoro.dto.SessionResponseDto;
import br.com.ayrs.mindtrack.study.domain.Task;
import br.com.ayrs.mindtrack.user.domain.User;
import br.com.ayrs.mindtrack.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration; // Novo import necessário
import java.time.LocalDateTime;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public SessionService(
            SessionRepository sessionRepository,
            SessionMapper sessionMapper,
            UserRepository userRepository,
            TaskRepository taskRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public SessionResponseDto iniciaSession(SessionRequestDto sessionDto) {
        User user = userRepository.findById(sessionDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + sessionDto.getUserId() + " não encontrado!"));

        Task task = taskRepository.findById(sessionDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task com ID " + sessionDto.getTaskId() + " não encontrada!"));

        Session newSession = new Session();
        newSession.setUser(user);
        newSession.setTask(task);
        newSession.setStartSession(LocalDateTime.now());

        Session sessionSalva = sessionRepository.save(newSession);
        return sessionMapper.toDto(sessionSalva);
    }

    /**
     * Encerra uma sessão de foco, calcula a duração e salva as métricas.
     */
    public SessionResponseDto endSession(Long id) {
        // 1. Busca a sessão ativa
        Session sessionExistente = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão com ID " + id + " não encontrada para ser encerrada!"));

        // 2. Valida se a sessão já foi encerrada
        if (sessionExistente.getEndSession() != null) {
            throw new RuntimeException("Sessão com ID " + id + " já foi encerrada em: " + sessionExistente.getEndSession());
        }

        // 3. Define o tempo final (agora)
        LocalDateTime endTime = LocalDateTime.now();
        sessionExistente.setEndSession(endTime);

        // 4. Calcula a duração em minutos (Lógica principal!)
        Duration duration = Duration.between(sessionExistente.getStartSession(), endTime);
        int durationMinutes = (int) duration.toMinutes();

        sessionExistente.setDurationMinutes(durationMinutes);

        // 5. Salva e retorna
        Session sessionAtualizada = sessionRepository.save(sessionExistente);
        return sessionMapper.toDto(sessionAtualizada);
    }

    // Métodos de busca e deleção (que você já implementou)

    public Page<SessionResponseDto> buscaTodasSessions(Pageable pageable) {
        Page<Session> sessionPage = sessionRepository.findAll(pageable);
        return sessionPage.map(sessionMapper::toDto);
    }

    public SessionResponseDto buscaSessionPorId(Long id) {
        Session sessionEncontrada = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão com ID " + id + " não encontrada!"));
        return sessionMapper.toDto(sessionEncontrada);
    }

    public void deletaSession(Long id) {
        if (!sessionRepository.existsById(id)) {
            throw new RuntimeException("Sessão com ID " + id + " não pode ser deletada: não encontrada!");
        }
        sessionRepository.deleteById(id);
    }
}