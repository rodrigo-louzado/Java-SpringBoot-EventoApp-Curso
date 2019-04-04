package com.eventoapp.repository;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;

import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, Long>{
  Iterable<Convidado> findByEvento(Evento evento);
  Convidado findByRg(String rg);
}