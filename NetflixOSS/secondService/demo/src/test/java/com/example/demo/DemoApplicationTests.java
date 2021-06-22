package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	RestTemplate template;
	
	@Test
	void testDecrypt() {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HttpHeaderInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));
		interceptors.add(new HttpHeaderInterceptor("Content-Type", MediaType.APPLICATION_JSON_VALUE));
		
		interceptors.add(new HttpHeaderInterceptor("Accept-Language", "en-US"));
		interceptors.add(new HttpHeaderInterceptor("tenant", "ford"));
		interceptors.add(new HttpHeaderInterceptor("channel", "web"));
		interceptors.add(new HttpHeaderInterceptor("country", "MX"));
		template.setInterceptors(interceptors);
		URI uri = URI.create("https://apollo-a.att.com/Thingworx/Things/Subscriptions-1/Services/DecryptAccount");

		var request = new DecryptRequest();
		request.setToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJlcmljc3NvbiIsImlhdCI6MTYyMzQ0ODU2OCwiZXhwIjoxNjIzNDUyMTY4LCJpbmZvIjp7Impzb25QYXlsb2FkIjoiXCJ7XFxcIm1ldGFzXFxcIjpbe1xcXCJwYXJhbWV0ZXJcXFwiOlxcXCIyMjIyXFxcIn0se1xcXCJBc3NlcnRpb25JRFxcXCI6XFxcIklEXzMwNGFhOWFhLWU1MWYtNGMxNi05NzEwLWJkODVhZDY2NTMyYVxcXCJ9XX1cIiJ9fQ.YhDFZiW_qJ6zjHzCRNeA8bSskj5-moKkMzyJQXoDyD8");

		var response = this.template.postForObject(uri, request, DecryptResponse.class);
        
		assertEquals(2, response.metas.size());
		
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class DecryptRequest {
		String token;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class DecryptResponse {
		List<DecryptItem> metas;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class DecryptItem {
		String parameter;

		@JsonProperty("AssertionID")
		String assertionID;
	}
}
