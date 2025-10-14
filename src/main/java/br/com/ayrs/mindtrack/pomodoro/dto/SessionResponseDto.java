package br.com.ayrs.mindtrack.pomodoro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SessionResponseDto {

    private Long id;
    private Long userId;
    private Long taskId;
    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private Integer durationMinutes;

}
