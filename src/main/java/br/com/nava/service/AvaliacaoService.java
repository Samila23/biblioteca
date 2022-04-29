package br.com.nava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;
import br.com.nava.repository.AvaliacaoRepository;


@Service
public class AvaliacaoService {
	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	//_________________________________________ Retorna a lista de ALUNOS ______________________________________
	public List<Avaliacao> finAll(){
		return avaliacaoRepository.findAll();
	}
	//_________________________________________ Salvar, busca por ID____________________________________________
	public Avaliacao save(Avaliacao avaliacao) {
		return avaliacaoRepository.save(avaliacao);
	}
	//_________________________________________ Salvar, busca por ID____________________________________________
	public Avaliacao buscarNotaAlunoPorDisciplina(AlunoDisciplina alunoDisciplina) {
		return avaliacaoRepository.findByAlunoDisciplina(alunoDisciplina);
	}
}
