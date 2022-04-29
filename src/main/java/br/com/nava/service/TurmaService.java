package br.com.nava.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nava.entity.Turma;
import br.com.nava.repository.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	TurmaRepository turmaRepository;
	
	//_________________________________________ Retorna a lista de TURMA _________________________________________
	public List<Turma> listaTodasTurma(){
		return turmaRepository.findAll();
	}
	//_________________________________________ PAGINACAO _________________________________________
	public Page<Turma> buscaPorPaginacao(int pagina , int linhasPorPagina , String direction , String orderBy){
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direction), orderBy);
		return new PageImpl<>(turmaRepository.findAll() , pageRequest , linhasPorPagina);
	}
	
	
	//_________________________________________ Retorna 01 TURMA, busca por ID_________________________________________
	public Turma buscaPorId(Integer Id) throws ObjectNotFoundException {
		Optional<Turma> turma = turmaRepository.findById(Id);
		return turma.orElseThrow(() -> new ObjectNotFoundException(null, "Turma não encontrada"));
	}
	
	
	//_________________________________________ Insere 01 nova TURMA ____________________________________________________
	public Turma salvar(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	
	//_________________________________________ Exclui 01 TURMA, busca por ID____________________________________________
	public Turma alterar(Turma objturma) {
		Turma turma = buscaPorId(objturma.getId());
		turma.setNome(objturma.getNome());
		return salvar(turma);
	}
	
	
	//_________________________________________ Altera dados da TURMA, busca por ID____________________________________________
	public void excluir(Integer id) {
		turmaRepository.deleteById(id);
	}
}
