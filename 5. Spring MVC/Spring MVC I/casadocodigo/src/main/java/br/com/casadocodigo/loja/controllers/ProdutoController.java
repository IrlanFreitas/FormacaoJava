package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos") // Todos os métodos irão pegar o prefixo produtos
public class ProdutoController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
		//Foi criado nosso próprio validador
		//Depois disso é necessário indicar ao Spring
		//Qual validador deve ser usado
		//Quando for colocado o @Valid no produto
		binder.addValidators(new ProdutoValidation());
	};
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		
		// O objeto ModelAndView serve para além de redirecionar para o página
		// solicitada, permite o envio de objetos para serem capturados
		// na página a ser redirecionada
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView lista() {
		
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		List<Produto> produtos = produtoDAO.listar();
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}
	
	@RequestMapping( method = RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result, 
			RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return form(produto);
		}
		
		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		produtoDAO.gravar(produto);
		
		// Flash é um escopo que dura duas requisições
		redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
		
		return new ModelAndView("redirect:produtos");
	}
	
}