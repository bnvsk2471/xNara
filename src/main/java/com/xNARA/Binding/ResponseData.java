package com.xNARA.Binding;

import java.util.List;

import lombok.Data;


@Data
public class ResponseData {
	//Response data binding for setting final result
	private String id;
	private Integer customer_id;
	private List<String> pack1;
	private List<String> pack2;
	

}
