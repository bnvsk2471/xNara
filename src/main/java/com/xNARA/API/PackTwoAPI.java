package com.xNARA.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.xNARA.Binding.PackTwo;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PackTwoAPI {
	
	//CALLING PACK2

	@Autowired
	private RestTemplate restTemplate;

	private static final String packTwoURL = "https://6466e9a7ba7110b663ab51f2.mockapi.io/api/v1/pack2";
	
	@Bean
	public PackTwo[] callpackTwo() throws Exception {
		try {
			return restTemplate.getForObject(packTwoURL, PackTwo[].class);
		}
		catch (HttpClientErrorException e) {
			HttpStatus statusCode = (HttpStatus) e.getStatusCode();
			log.info("HttpClientErrorException" + statusCode);
			throw new HttpClientErrorException(statusCode);
		} catch (ResourceAccessException e) {
			log.error("Resource access error: " + e.getMessage());
			throw new ResourceAccessException(packTwoURL);
		} catch (Exception e) {
			throw new Exception();
		}
	
	}

}
