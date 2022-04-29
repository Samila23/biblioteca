package br.com.nava.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava.entity.Aluno;
//JpaRepository tras o DRUD pronto
@Repository // Camada que  Aponta para o banco de dados, trazendo as informações desejadas, CRUD
//Parametro é a Classe principal(Entity) + o Tipo da chave primaria
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
