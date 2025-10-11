package br.com.ayrs.mindtrack.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponseDto {

    private Long id;
    private String name;
    private String description;
    private String professor;

    public SubjectResponseDto(String name, String description) {
    }
}
