package br.com.ayrs.mindtrack.pomodoro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long taskId;
}
