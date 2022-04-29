package br.com.nava.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entity.Disciplina;
import br.com.nava.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	//Mostrar todos os itens
	public List<Disciplina> listaTodasDisciplinas(){
		return disciplinaRepository.findAll();
	}
	//Buscar por ID
	public Disciplina buscaPorId(Integer id) throws ObjectNotFoundException{
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		return disciplina.orElseThrow(() -> new ObjectNotFoundException(null, "Objeto não encontrato"));
	}
	//Salvar nova disciplina
	public Disciplina salvar(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
	//Exclui 
	public void excluir(Integer id) {
		disciplinaRepository.deleteById(id);
	}
	//Alterar informações
	public Disciplina alterar(Disciplina objDisciplina) {
		Disciplina disciplina = buscaPorId(objDisciplina.getId());
		disciplina.setNome(objDisciplina.getNome());
		return salvar(disciplina);
	}
}
