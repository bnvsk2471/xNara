package com.xNARA.Service;

import com.xNARA.Binding.ResponseData;

public interface NaraService {
	
	public ResponseData getCustomerRequirements(Integer customerId) throws Exception;

}
