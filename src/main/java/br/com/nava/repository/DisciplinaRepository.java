package br.com.nava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava.entity.Disciplina;
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

}
