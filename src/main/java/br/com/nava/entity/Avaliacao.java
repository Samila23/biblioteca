package br.com.nava.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data 																			//Inclui todos os Gte e Set
@AllArgsConstructor 															//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																//Incluir o contrutores padrão

@Entity																			// Aluno é uma classe de dominio, e vai ser representado por uma tabela na base de dados
@Table(name = "avaliacoes")
public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 8140411953755289815L;
	private String conceito;
	//_____________________________________________ Incluindo Chave Composta(AlunoDisciplina) na entidade ________________________________________________________________
	@EmbeddedId
	private AlunoDisciplina alunoDisciplina;
	
}
