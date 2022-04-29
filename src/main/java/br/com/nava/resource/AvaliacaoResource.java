package br.com.nava.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava.constantes.Messages;
import br.com.nava.entity.Aluno;
import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;
import br.com.nava.entity.Disciplina;
import br.com.nava.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name= Messages.SWAGGER_TAG_AVALIACAO_ENDPONIT) //Customizar o nome dessa classa na API - WEB
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoResource {
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	
	//_________________________________________ Retorna 01 Avaliacao por ID________________________________________
	@Operation(description = Messages.SWAGGER_GET) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(value="/{idAluno}/{idDisciplina}", method=RequestMethod.GET)
	public ResponseEntity<Avaliacao> buscarAvaliacaoAlunoPorDisciplina (@PathVariable Integer idAluno, @PathVariable Integer idDisciplina){
		Aluno aluno = new Aluno();
		aluno.setId(idAluno);
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(idDisciplina);
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disciplina);
		
		Avaliacao avaliacao = avaliacaoService.buscarNotaAlunoPorDisciplina(alunoDisciplina);
		return ResponseEntity.ok().body(avaliacao);
	}
	
	//_________________________________________ Retorna a lista de ALUNOS _________________________________________
	@Operation(description =  Messages.SWAGGER_GET_ALL) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> ListarAvaliacao(){
		List<Avaliacao> listaAvaliacao = avaliacaoService.finAll();
		return ResponseEntity.ok().body(listaAvaliacao);
	}
	
	
	//_________________________________________ Insere 01 nova AVALIACAO _________________________________________
	@Operation(description = Messages.SWAGGER_GET_INSERT) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Avaliacao objAvaliacao){
		objAvaliacao = avaliacaoService.save(objAvaliacao);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	

}
