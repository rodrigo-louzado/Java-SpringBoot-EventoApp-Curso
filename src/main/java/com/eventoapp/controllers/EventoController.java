package com.eventoapp.controllers;

import javax.validation.Valid;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventoController {

  @Autowired
  private EventoRepository er;

  @Autowired
  private ConvidadoRepository cr;

  @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
  public String form() {
    return "evento/formEvento";
  }

  @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
  public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
    if(result.hasErrors()) {
      attributes.addFlashAttribute("mensagem", "Verifique se não há campos vazios");
      return "redirect:/cadastrarEvento";
    }
    er.save(evento);
    attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
    return "redirect:/cadastrarEvento";
  }

  @RequestMapping("/eventos")
  public ModelAndView listaEventos() {
    ModelAndView mv = new ModelAndView("listaEventos");
    Iterable<Evento> eventos = er.findAll();
    mv.addObject("eventos", eventos);
    return mv;
  }

  @RequestMapping(value = "/{codigo}", method=RequestMethod.GET)
  public ModelAndView detalhesEvento(@PathVariable long codigo) {
    Evento evento = er.findByCodigo(codigo);
    ModelAndView mv = new ModelAndView("evento/detalhesEvento");
    mv.addObject("evento", evento);

    Iterable<Convidado> convidados = cr.findByEvento(evento);
    mv.addObject("convidados", convidados);
    
    return mv;
  }

  @RequestMapping("/deletarEvento")
  public String deletarEvento(long codigo) {
    Evento evento = er.findByCodigo(codigo);
    if(cr.findByEvento(evento).iterator().hasNext()) {
      deletarConvidado(evento);
      er.delete(evento);
      return "redirect:/eventos";
    }
    er.delete(evento);
    return "redirect:/eventos";
  }

  @RequestMapping(value = "/{codigo}", method=RequestMethod.POST)
  public String detalhesEventoPost(@PathVariable long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
    if(result.hasErrors()) {
      attributes.addFlashAttribute("mensagem", "Verifique se não há campos vazios");
      return "redirect:/{codigo}";
    }
    Evento evento = er.findByCodigo(codigo);
    convidado.setEvento(evento);
    cr.save(convidado);
    attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
    return "redirect:/{codigo}";
  }

  @RequestMapping("/deletarConvidado")
  public String deletarConvidado(String rg) {
    Convidado convidado = cr.findByRg(rg);
    cr.delete(convidado);

    Evento evento = convidado.getEvento();
    long codigoLong = evento.getCodigo();
    String codigo = "" + codigoLong;
    return "redirect:/" + codigo;
  }

  public void deletarConvidado(Evento evento) {
    Iterable<Convidado> convidado = cr.findByEvento(evento);
    cr.delete(convidado.iterator().next());
  }
}