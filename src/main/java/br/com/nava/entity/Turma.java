package br.com.nava.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 																			//Inclui todos os Gte e Set
@AllArgsConstructor 															//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																//Incluir o contrutores padrão
@Entity 																		// Aluno é uma classe de dominio, e vai ser representado por uma tabela na base de dados
public class Turma {
	@Id 																		//Apontar que a variavel ID é a chave primária da tabela
	@GeneratedValue(strategy = GenerationType.AUTO) 						// Informa que o Banco de dados irá gerar os dados do ID
	private Integer id;
	private String nome;
}
