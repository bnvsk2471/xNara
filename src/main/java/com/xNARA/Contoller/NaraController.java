package com.xNARA.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xNARA.Binding.ResponseData;
import com.xNARA.Exception.XNaraException;
import com.xNARA.Service.NaraService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/xNara")
@Slf4j
public class NaraController {

	@Autowired
	private NaraService naraService;

	@GetMapping("/get/{customerId}")
	public ResponseEntity<String> getCustomerRequirements(@PathVariable Integer customerId) throws Exception {
		ResponseData responseData;
		try {
			log.info("Before service call {}",customerId);
			responseData = naraService.getCustomerRequirements(customerId);
			log.info("After service call {}",customerId);
			return ResponseEntity.ok(responseData.toString());
		} catch (XNaraException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
