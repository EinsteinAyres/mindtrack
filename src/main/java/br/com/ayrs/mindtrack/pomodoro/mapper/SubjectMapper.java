package br.com.ayrs.mindtrack.pomodoro.mapper;


import br.com.ayrs.mindtrack.pomodoro.domain.Subject;
import br.com.ayrs.mindtrack.pomodoro.dto.SessionRequestDto;
import br.com.ayrs.mindtrack.pomodoro.dto.SessionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public Subject toEntity(SessionRequestDto subjectDto) {
        if (subjectDto == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setName(subjectDto.getMateriaName());
        subject.setDescription(subjectDto.getMateriaDescription());
        return subject;
    }

    public SessionResponseDto toDto(Subject subject) {

        if (subject == null) {
            return null;
        }
        return new SessionResponseDto(subject.getName(), subject.getDescription());
    }

}
