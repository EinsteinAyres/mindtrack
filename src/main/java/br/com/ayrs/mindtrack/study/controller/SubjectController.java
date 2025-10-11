package br.com.ayrs.mindtrack.study.controller;

import br.com.ayrs.mindtrack.study.domain.Subject;
import br.com.ayrs.mindtrack.study.dto.SubjectRequestDto;
import br.com.ayrs.mindtrack.study.dto.SubjectResponseDto;
import br.com.ayrs.mindtrack.study.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDto> criaMateria(@Valid @RequestBody SubjectRequestDto subjectRequestDto) {
        SubjectResponseDto subjectResponseDto = subjectService.criaMateria(subjectRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<Page<SubjectResponseDto>> buscaTodasMaterias(Pageable pageable) {
        Page<SubjectResponseDto> page = subjectService.buscaTodasMaterias(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> buscaMateriaPorId(@PathVariable Long id) {
        SubjectResponseDto subjectResponseDto = subjectService.buscaMateriaPorId(id);
        return ResponseEntity.ok(subjectResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> atualizaMateria(
            @PathVariable Long id,
            @RequestBody SubjectRequestDto subjectRequestDto) {
        SubjectResponseDto subjectResponseDto = subjectService.atualizaMateria(id, subjectRequestDto);
        return ResponseEntity.ok(subjectResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaMateria(@PathVariable Long id) {
        subjectService.deletaMateria(id);
        return ResponseEntity.noContent().build();
    }

}
