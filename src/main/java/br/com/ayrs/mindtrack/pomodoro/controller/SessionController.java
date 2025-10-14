package br.com.ayrs.mindtrack.pomodoro.controller;

import br.com.ayrs.mindtrack.pomodoro.dto.SessionRequestDto;
import br.com.ayrs.mindtrack.pomodoro.dto.SessionResponseDto;
import br.com.ayrs.mindtrack.pomodoro.service.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<SessionResponseDto> iniciaSession(@RequestBody SessionRequestDto sessionDto) {
        SessionResponseDto sessionResponseDto = sessionService.iniciaSession(sessionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionResponseDto);
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<SessionResponseDto> endSession(@PathVariable Long id) {
         SessionResponseDto sessionResponseDto = sessionService.endSession(id);
         return ResponseEntity.ok(sessionResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<Page<SessionResponseDto>> buscaTodasSessions(Pageable pageable) {
        Page<SessionResponseDto> page = sessionService.buscaTodasSessions(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDto> buscaSessionPorId(@PathVariable Long id) {
        SessionResponseDto sessionResponseDto = sessionService.buscaSessionPorId(id);
        return ResponseEntity.ok(sessionResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaSessio(@PathVariable Long id) {
        sessionService.deletaSession(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
