package br.com.nava.resource;
import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava.constantes.Messages;
import br.com.nava.entity.Turma;
import br.com.nava.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name= Messages.SWAGGER_TAG_TURMA_ENDPONIT) //Customizar o nome dessa classa na API - WEB
@RestController
@RequestMapping("/turmas")
public class TurmaResource {
	
	
	@Autowired
	private TurmaService turmaService;
	//_________________________________________ Retorna a lista de TURMA _________________________________________
	@Operation(description = Messages.SWAGGER_GET_ALL) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Turma>> listarTurma(){
		List<Turma> turmas = turmaService.listaTodasTurma();
		return ResponseEntity.ok().body(turmas);
	}
	
	
	
	
	//_________________________________________ PAGINACAO _________________________________________
	//_________________________________________ VERSIONAMENTO DE PAGINAS __________________________
	//VERSAO 01
	//http://localhost:8080/api-sistema/page?pagina=0&linhaPorPagina=10&direcao=ASC&ordenacao=nome
	@GetMapping(value="/v1/page")
	public ResponseEntity< Page<Turma>> listarTurmasPorPaginacaoV1
		(	@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value="linhasPorPagina" ,defaultValue = "24" ) int linhasPorPagina,
			@RequestParam(value="direcao" ,defaultValue = "ASC" ) String direcao,
			@RequestParam(value="orderBy" ,defaultValue = "nome" ) String orderBy
		){
		Page<Turma> turmas = turmaService.buscaPorPaginacao(pagina, linhasPorPagina, direcao, orderBy);
		return ResponseEntity.ok().body(turmas);
	}
	//VERSAO 01
	@GetMapping(value="/v2/page")
	public ResponseEntity< Page<Turma>> listarTurmasPorPaginacaoV2
		(	@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value="direcao" ,defaultValue = "ASC" ) String direcao,
			@RequestParam(value="orderBy" ,defaultValue = "nome" ) String orderBy
		){													//MOSTRA 10 lINHAS
		Page<Turma> turmas = turmaService.buscaPorPaginacao(pagina, 10 , direcao, orderBy);
		return ResponseEntity.ok().body(turmas);
	}
	
	
	
	
	//_________________________________________ Retorna 01 TURMA, busca por ID_________________________________________
	@Operation(description = Messages.SWAGGER_GET) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Turma> buscaPorId(@PathVariable Integer id) throws ObjectNotFoundException{
		Turma turma = turmaService.buscaPorId(id);
		return ResponseEntity.ok().body(turma);
	}
	
	//_________________________________________ Insere 01 nova TURMA ____________________________________________________
	@Operation(description = Messages.SWAGGER_GET_INSERT) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Turma turma){
		Turma objTurma = turmaService.salvar(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objTurma.getId()).toUri();
		return ResponseEntity.created(uri).build();	
	}
	
	//_________________________________________ Exclui 01 TURMA, busca por ID____________________________________________
	@Operation(description = Messages.SWAGGER_DELETE) //Customizar o nome desse metodo na API - WEB
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		turmaService.excluir(id);
		return ResponseEntity.noContent().build();
	} 
	
	//_________________________________________ Altera dados TURMA , busca por ID____________________________________________
	@Operation(description = Messages.SWAGGER_UPDATE) //Customizar o nome desse metodo na API - WEB
	@RequestMapping (value="/{id}", method = RequestMethod.PUT)
	private ResponseEntity<Void> alterar (@RequestBody Turma objTurma,@PathVariable Integer id){
		objTurma.setId(id);
		turmaService.alterar(objTurma);
		return ResponseEntity.noContent().build();
	}
	
}
