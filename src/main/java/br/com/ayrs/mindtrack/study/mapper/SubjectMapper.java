package br.com.ayrs.mindtrack.study.mapper;


import br.com.ayrs.mindtrack.study.domain.Subject;
import br.com.ayrs.mindtrack.study.dto.SubjectRequestDto;
import br.com.ayrs.mindtrack.study.dto.SubjectResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {


    public Subject toEntity(SubjectRequestDto subjectDto) {
        if (subjectDto == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setName(subjectDto.getMateriaName());
        subject.setDescription(subjectDto.getMateriaDescription());
        return subject;
    }

    public SubjectResponseDto toDto(Subject subject) {

        if (subject == null) {
            return null;
        }
        return new SubjectResponseDto(subject.getName(), subject.getDescription());
    }

}
