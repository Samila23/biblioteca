package br.com.nava.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
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
import br.com.nava.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name= Messages.SWAGGER_TAG_ALUNO_ENDPONIT) //Customizar o nome dessa classa na API - WEB
@RestController 
@RequestMapping("/aluno")
public class AlunoResource {
	
	@Autowired
	private AlunoService alunoService;
	
	//_________________________________________ Retorna a lista de ALUNOS _________________________________________
	@Operation(description = Messages.SWAGGER_GET_ALL) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(method=RequestMethod.GET)							 // SE A REQUISIÇÃO FOR DO TIPO GET é para redirecionar e esse metodo
	public ResponseEntity<List<Aluno>> listarAluno(){
		List<Aluno> alunos = alunoService.listaTodosAlunos();
		return ResponseEntity.ok().body(alunos); 						// Se houver a requisição, retorna no corpo da requisição a Lista Alunos
	}
	
	
	//_________________________________________ Retorna 01 ALUNOS, busca por ID_________________________________________
	@Operation(description = Messages.SWAGGER_GET) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(value="/{id}", method = RequestMethod.GET )
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Integer id) throws ObjectNotFoundException{
		Aluno aluno = alunoService.buscaPorId(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	//_________________________________________ Insere 01 novo ALUNO____________________________________________________
	@Operation(description = Messages.SWAGGER_GET_INSERT) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Aluno objAluno){
		Aluno aluno = alunoService.salvar(objAluno);
																		//Apos salvar, retorne que foi salvo com sucesso e qual é URL
																		//Dúvida do que faz esse URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//_________________________________________ Exclui 01 ALUNO, busca por ID____________________________________________
	@Operation(description = Messages.SWAGGER_DELETE) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		alunoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	//_________________________________________ Altera dados do ALUNO, busca por ID____________________________________________
	@Operation(description = Messages.SWAGGER_UPDATE) //Customizar o nome desse metodo na API - WEB
	@RequestMapping (value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Aluno objAluno , @PathVariable Integer id){
		objAluno.setId(id);
		alunoService.alterar(objAluno);
		return ResponseEntity.noContent().build();
	}
}
