package br.com.fpc.clientTest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.fpc.entities.Item;

public class RestClientUtil {

	public void getAllItensDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/financial-control/item/";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Item[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Item[].class);
		Item[] itens = responseEntity.getBody();
		for (Item item : itens) {
			System.out.println(
					"Id:" + item.getId() + ", Title:" + item.getDescription() + ", Date: " + item.getItemDate());
		}
	}

	public void addItemDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Item objItem = new Item();
		objItem.setDescription("Teste criação de item");
		objItem.setItemDate("2017-01-01");

		HttpEntity<Item> requestEntity = new HttpEntity<Item>(objItem, headers);
		String url = "http://localhost:8081/financial-control/item/";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForLocation(url, requestEntity);

	}

	public void deleteItem() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/financial-control/item/{id}";
		HttpEntity<Item> requestEntity = new HttpEntity<Item>(headers);
		HttpEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class,
				12);

		System.out.println(responseEntity);
	}

	public static void main(String args[]) {
		RestClientUtil util = new RestClientUtil();
		// util.getArticleByIdDemo();
		// util.addItemDemo();
		// util.updateArticleDemo();
		// util.deleteItem();
		util.getAllItensDemo();
	}
}
