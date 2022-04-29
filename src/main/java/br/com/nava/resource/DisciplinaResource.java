package br.com.nava.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava.constantes.Messages;
import br.com.nava.entity.Aluno;
import br.com.nava.entity.Disciplina;
import br.com.nava.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name= Messages.SWAGGER_TAG_DISCIPLINA_ENDPONIT) //Customizar o nome dessa classa na API - WEB
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {
	@Autowired
	private DisciplinaService disciplinaService;
	
	//_________________________________________ Retorna a lista de ALUNOS _________________________________________
	@Operation(description = Messages.SWAGGER_GET_ALL) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Disciplina>> listarDisciplinas(){
		List<Disciplina> disciplinas = disciplinaService.listaTodasDisciplinas();
		return ResponseEntity.ok().body(disciplinas);
	}
	
	
	//_________________________________________ Retorna 01 ALUNOS, busca por ID_________________________________________
	@Operation(description = Messages.SWAGGER_GET) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Disciplina> buscarPorId(@PathVariable Integer id)throws ObjectNotFoundException{
		Disciplina disciplina = disciplinaService.buscaPorId(id);
		return ResponseEntity.ok().body(disciplina);
	}
	
	
	//_________________________________________ Insere 01 novo ALUNO____________________________________________________
	@Operation(description = Messages.SWAGGER_GET_INSERT) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Disciplina disciplina){
		Disciplina disc = disciplinaService.salvar(disciplina);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(disc.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
		
		
	//_________________________________________ Exclui 01 ALUNO, busca por ID____________________________________________
	@Operation(description = Messages.SWAGGER_DELETE) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		disciplinaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	//_________________________________________ Altera dados do ALUNO, busca por ID____________________________________________
	@Operation(description = Messages.SWAGGER_UPDATE) //Customizar o nome desse metodo na API - WEB
	@RequestMapping (value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Disciplina objDisciplina, @PathVariable Integer id){
		objDisciplina.setId(id);
		disciplinaService.alterar(objDisciplina);
		return ResponseEntity.noContent().build();
	}
}
