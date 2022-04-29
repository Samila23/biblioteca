package br.com.nava.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data 																			//Inclui todos os Gte e Set
@AllArgsConstructor 															//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																//Incluir o contrutores padrão
//_____________________________________________ Chave Composta ________________________________________________________________
@Embeddable 																	
public class AlunoDisciplina implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4208694518789299013L;
	//_________________________________________ Relacionamento ALUNODISCIPLINA E ALUNO _________________________________________
	@ManyToOne
	private Aluno aluno;
	//_________________________________________ Relacionamento ALUNODISCIPLINA E DISCIPLINA ____________________________________
	@ManyToOne
	private Disciplina disciplina;
}
