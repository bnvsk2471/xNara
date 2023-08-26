package com.xNARA.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xNARA.API.PackOneAPI;
import com.xNARA.API.PackTwoAPI;
import com.xNARA.Binding.PackData;
import com.xNARA.Binding.PackOne;
import com.xNARA.Binding.PackTwo;
import com.xNARA.Binding.ResponseData;
import com.xNARA.Exception.XNaraException;
import com.xNARA.Service.NaraService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NaraServiceImpl implements NaraService {

	@Autowired
	private PackOneAPI packOneAPI;

	@Autowired
	private PackTwoAPI packTwoAPI;

	@Override
	public ResponseData getCustomerRequirements(Integer customerId) throws Exception {

		// Object creation for final result
		log.info("data initilization started for the customerId: {}", customerId);
		ResponseData data = new ResponseData();

		// Calling APIs to get data
		try {
			log.info("Before calling API", customerId);
			PackOne[] packOne = packOneAPI.callpackOne();
			PackTwo[] packTwo = packTwoAPI.callpackTwo();
			log.info("After calling API", customerId);

			// Getting data by passing customerId, below 2 line will search for data based
			// on customerId and save it to Packs
			Optional<PackOne> pacOne = Arrays.stream(packOne).filter(pack -> pack.getCustomer_id() == customerId)
					.findFirst();
			Optional<PackTwo> pacTwo = Arrays.stream(packTwo).filter(pack -> pack.getCustomer_id() == customerId)
					.findFirst();

			// If data is present then setting data to object
			if (pacOne.isPresent() && pacTwo.isPresent()) {

				data.setId(pacOne.get().getId());
				data.setCustomer_id(pacOne.get().getCustomer_id());

				List<String> pack1List = new ArrayList<>();
				// PACK1 Data setting as per requirement
				for (PackData packData : pacOne.get().getPack_data()) {
					String pack1Entry = packData.getIngredient() + " " + packData.getQuantity() + packData.getUnit();
					pack1List.add(pack1Entry);
				}
				data.setPack1(pack1List);

				// PACK2 Data setting as per requirement
				List<String> pack2List = new ArrayList<>();
				for (PackData packData : pacTwo.get().getPack_data()) {
					String pack2Entry = packData.getIngredient() + " " + packData.getQuantity() + packData.getUnit();
					pack2List.add(pack2Entry);
				}
				data.setPack2(pack2List);

				log.info("data is created with customer_ID: {}, data: {}", customerId, data);
			}else {
				throw new XNaraException("data is not available with provided CustomerId");
			}
		} catch (Exception e) {
			throw new XNaraException("data is not available with provided CustomerId");
		}
		return data;

	}

}
