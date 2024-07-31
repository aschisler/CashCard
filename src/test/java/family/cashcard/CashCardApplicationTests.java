package family.cashcard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashCardApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	@Test
	void shouldResturnACashCardWhenDataIsSaved() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/cashcards/99", String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		//converts the response String into a JSON-aware object with lots of helper methods
		DocumentContext documentContext = JsonPath.parse(responseEntity.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isNotNull();
		//test amount as well
		Double amount = documentContext.read("$.amount");
		assertThat(amount).isEqualTo(123.45);
	}
}