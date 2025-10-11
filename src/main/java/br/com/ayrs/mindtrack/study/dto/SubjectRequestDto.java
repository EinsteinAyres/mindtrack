package br.com.ayrs.mindtrack.study.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NotBlank
@Size
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDto {

    private String materiaName;
    private String materiaDescription;

}
