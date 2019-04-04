package com.eventoapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "evento")
public class Evento implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codigo;
  @NotEmpty (message = "Campo não estar vazio")
  private String nome;
  @NotEmpty (message = "Campo não estar vazio")
  private String local;
  @NotEmpty (message = "Campo não estar vazio")
  private String data;
  @NotEmpty (message = "Campo não estar vazio")
  private String horario;

  @OneToMany
  private List<Convidado> convidados;

  /**
   * @return the codigo
   */
  public Long getCodigo() {
    return codigo;
  }

  /**
   * @param codigo the codigo to set
   */
  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  /**
   * @return the nome
   */
  public String getNome() {
    return nome;
  }

  /**
   * @param nome the nome to set
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * @return the local
   */
  public String getLocal() {
    return local;
  }

  /**
   * @param local the local to set
   */
  public void setLocal(String local) {
    this.local = local;
  }

  /**
   * @return the data
   */
  public String getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(String data) {
    this.data = data;
  }

  /**
   * @return the horario
   */
  public String getHorario() {
    return horario;
  }

  /**
   * @param horario the horario to set
   */
  public void setHorario(String horario) {
    this.horario = horario;
  }

  /**
   * @param codigo the codigo to set
   */
  public void setCodigo(long codigo) {
    this.codigo = codigo;
  }
}