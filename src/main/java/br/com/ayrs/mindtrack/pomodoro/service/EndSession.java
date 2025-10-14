package br.com.ayrs.mindtrack.pomodoro.service;

import br.com.ayrs.mindtrack.pomodoro.domain.Session;

import java.time.Duration;
import java.time.LocalDateTime;

public class EndSession {

    public long endSession(Long sessionId) {
        // 1. Buscar a sessão pelo ID
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        // 2. Definir o endSession como agora
        LocalDateTime now = LocalDateTime.now();
        session.setEndSession(now);

        // 3. Calcular a diferença em minutos
        long minutes = Duration.between(session.getStartSession(), now).toMinutes();

        // 4. Salvar a sessão atualizada
        sessionRepository.save(session);

        // 5. Retornar o tempo de foco em minutos
        return minutes;
    }
}
