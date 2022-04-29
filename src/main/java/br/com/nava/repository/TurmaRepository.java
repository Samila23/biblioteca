package br.com.nava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event.ID;

import br.com.nava.entity.Turma;
//JpaRepository tras o DRUD pronto
@Repository // Camada que  Aponta para o banco de dados, trazendo as informações desejadas, CRUD                    
//Parametro é a Classe principal(Entity) + o Tipo da chave primaria
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

}
