package br.com.ayrs.mindtrack.study.controller;

import br.com.ayrs.mindtrack.study.dto.SubjectRequestDto;
import br.com.ayrs.mindtrack.study.dto.SubjectResponseDto;
import br.com.ayrs.mindtrack.study.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDto> criaMateria(@Valid @RequestBody SubjectRequestDto subjectRequestDto) {
        // Delega a criação para o Serviço e usa o status 201 CREATED
        SubjectResponseDto subjectResponseDto = subjectService.criaMateria(subjectRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectResponseDto);
    }

}
