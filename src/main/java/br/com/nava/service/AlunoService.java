package br.com.nava.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entity.Aluno;
import br.com.nava.repository.AlunoRepository;

@Service // Camada de serviço , faz a passagem entre a repository e resource 
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	//_________________________________________ Retorna a lista de ALUNOS _________________________________________
	public List<Aluno> listaTodosAlunos(){
		return alunoRepository.findAll(); // == SELECT *FROM ALUNOS
		
	}
	
	//_________________________________________ Retorna 01 ALUNO, busca por ID_________________________________________
	public Aluno buscaPorId(Integer id)  throws ObjectNotFoundException{ 			//se não encontrar ele faz um evento
		Optional<Aluno> aluno = alunoRepository.findById(id);
		return aluno.orElseThrow(() -> new ObjectNotFoundException(null, "Objeto não encontrato"));
	}
	
	//_________________________________________ Insere 01 novo ALUNO____________________________________________________
	public Aluno salvar (Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	//_________________________________________ Exclui 01 ALUNO, busca por ID____________________________________________
	public void excluir(Integer id) {
		alunoRepository.deleteById(id);
	}
	//_________________________________________ Altera dados do ALUNO, busca por ID___________________________________________
																					// Irá substituir, caso não exista irá incluir um novo
	public Aluno alterar(Aluno objAluno) {
		Aluno aluno = buscaPorId(objAluno.getId());
		aluno.setNome(objAluno.getNome());
		aluno.setTurma(objAluno.getTurma());
		aluno.setDisciplina(objAluno.getDisciplina());
		return salvar(aluno);
	}
}
