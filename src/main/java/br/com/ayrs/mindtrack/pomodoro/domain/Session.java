package br.com.ayrs.mindtrack.pomodoro.domain;

import br.com.ayrs.mindtrack.study.domain.Task;
import br.com.ayrs.mindtrack.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Task task;

    private LocalDateTime startSession;

    private LocalDateTime endSession;

    private Integer durationMinutes;

}
