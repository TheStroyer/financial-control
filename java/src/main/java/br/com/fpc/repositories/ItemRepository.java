package br.com.fpc.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.fpc.entities.Item;

/**
 * @author fernando
 *
 */
public interface ItemRepository extends CrudRepository<Item, Long> {

}
