package com.xNARA.Binding;

import java.util.List;

import lombok.Data;


@Data
public class PackTwo {
	private Integer customer_id;
	private String id;
    private List<PackData> pack_data;
}
