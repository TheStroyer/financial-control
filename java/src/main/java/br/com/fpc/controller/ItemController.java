package br.com.fpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fpc.entities.Item;
import br.com.fpc.repositories.ItemRepository;

/**
 * @author fernando
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	/**
	 * Lista todos os itens
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public @ResponseBody Iterable<Item> getAllItens() {
		return itemRepository.findAll();
	}

	/**
	 * Adiciona um novo item
	 * 
	 * @param item
	 * @return
	 */
	@PostMapping("/add")
	public @ResponseBody Item addNewItem(@RequestBody Item item) {
		return itemRepository.save(item);
	}

	/**
	 * 
	 * Deleta um item pelo id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/del/{id}")
	public @ResponseBody ResponseEntity<String> deleteItem(@PathVariable("id") long id) {

		Item item = new Item();
		item.setId(id);

		itemRepository.delete(item);

		return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
	}
}
