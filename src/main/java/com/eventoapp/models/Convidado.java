package com.eventoapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "convidado")
public class Convidado {

  @Id
  @NotEmpty (message = "Campo não estar vazio")
  private String rg;
  @NotEmpty (message = "Campo não estar vazio")
  private String nomeConvidado;

  @ManyToOne
  private Evento evento;

  /**
   * @return the rg
   */
  public String getRg() {
    return rg;
  }

  /**
   * @param rg the rg to set
   */
  public void setRg(String rg) {
    this.rg = rg;
  }

  /**
   * @return the nomeConvidado
   */
  public String getNomeConvidado() {
    return nomeConvidado;
  }

  /**
   * @param nomeConvidado the nomeConvidado to set
   */
  public void setNomeConvidado(String nomeConvidado) {
    this.nomeConvidado = nomeConvidado;
  }

  /**
   * @return the evento
   */
  public Evento getEvento() {
    return evento;
  }

  /**
   * @param evento the evento to set
   */
  public void setEvento(Evento evento) {
    this.evento = evento;
  }  
}