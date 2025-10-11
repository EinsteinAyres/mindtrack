package br.com.ayrs.mindtrack.study.service;


import br.com.ayrs.mindtrack.study.domain.Subject; // <-- CORRETO!
import br.com.ayrs.mindtrack.study.dto.SubjectRequestDto;
import br.com.ayrs.mindtrack.study.dto.SubjectResponseDto;
import br.com.ayrs.mindtrack.study.mapper.SubjectMapper;
import br.com.ayrs.mindtrack.study.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    // Injeção via Construtor
    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public SubjectResponseDto criaMateria(SubjectRequestDto subjectDto) {
        // Usa o Mapper para converter DTO para Entidade
        Subject subject = subjectMapper.toEntity(subjectDto);

        // Salva no banco de dados (Repository)
        Subject subjectSalvo = subjectRepository.save(subject);

        // Retorna o DTO de Resposta (Mapeado da Entidade salva)
        return subjectMapper.toDto(subjectSalvo);
    }

    public Page<SubjectResponseDto> buscaTodasMaterias(Pageable pageable) {
        // Busca a Entidade e lança exceção se não encontrar
        Page<Subject> subjectPage = subjectRepository.findAll(pageable);
        // Mapeia Entidade para DTO
        return subjectPage.map(subjectMapper::toDto);
    }

    public SubjectResponseDto buscaMateriaPorId(Long id) {
        // Busca a Entidade e lança exceção se não encontrar
        Subject subjectEncontrado = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matéria com ID " + id + " não encontrado!"));
        // Mapeia Entidade para DTO
        return subjectMapper.toDto(subjectEncontrado);
    }

    public SubjectResponseDto atualizaMateria(Long id, SubjectRequestDto subjectDto) {
        // 1. Busca a matéria existente
        Subject subjectExistente = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matéria com ID " + id + " não pode ser atualizado: não encontrado!"));

        subjectExistente.setName(subjectDto.getMateriaName());
        subjectExistente.setDescription(subjectDto.getMateriaDescription());

        Subject subjectAtualizado = subjectRepository.save(subjectExistente);

        // 4. Mapeia e retorna o DTO de Resposta
        return subjectMapper.toDto(subjectAtualizado);
    }

    /**
     * Deleta um usuário pelo ID.
     * Inclui a lógica de checagem de existência antes de deletar.
     */
    public void deletaMateria(Long id) {
        // 1. Verifica se o usuário existe. Se não existir, lança exceção para o Controller
        // retornar uma resposta de erro (Ex: 404 Not Found, futuramente)
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Matéria com ID " + id + " não pode ser deletada: não encontrada!");
        }

        // 2. Deleta o usuário.
        subjectRepository.deleteById(id);
    }

}
