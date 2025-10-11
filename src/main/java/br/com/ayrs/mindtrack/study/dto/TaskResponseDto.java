package br.com.ayrs.mindtrack.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {

    private Long id;
    private Long subjectId;
    private String title;
    private String description;

    public TaskResponseDto(Long id, String title, String description) {
    }
}