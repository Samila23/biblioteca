package br.com.nava.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 																	//Inclui todos os Gte e Set
@AllArgsConstructor 													//Inclui os construtores com todos os paramentros
@NoArgsConstructor														//Incluir o contrutores padrão
@Entity 																// Aluno é uma classe de dominio, e vai ser representado por uma tabela na base de dados
public class Aluno {
	
	@Id																	//Apontar que a variavel ID é a chave primária da tabela
	@GeneratedValue(strategy = GenerationType.AUTO) 				// Informa que o Banco de dados irá gerar os dados do ID
	private Integer id;
	private String nome;
	
	//_________________________________________ Relacionamento ALUNO E TURMA _________________________________________
	@ManyToOne // Muitos Alunos para 01 turma
	@JoinColumn (name = "id_turma")
	private Turma turma;
	
	//_________________________________________ Relacionamento ALUNO E DISCIPLINA ____________________________________
	@ManyToMany // Varios alunos tem Varias Disciplinas
	@JoinTable (
			name = "matricula",
			joinColumns = {@JoinColumn (name = "id_aluno")},
			inverseJoinColumns = {@JoinColumn(name = "id_disciplina")}
			)
	private List<Disciplina> Disciplina;
	//_________________________________________ Relacionamento ALUNO E DISCIPLINA (AVALIAÇÃO) __________________________
}
