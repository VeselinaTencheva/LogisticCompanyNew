package com.example.demo.util;

import com.example.demo.entities.Office;
import com.example.demo.entities.Shipment;

import java.util.List;

public class CalculateShipmentUtil {

	public static double calculateShipmentPrice(Shipment shipment, List<Office> offices) {
		for(Office office:offices){
			if(office.getAddress().equals(shipment.getAddress())){
				return shipment.getPrice()+shipment.getWeight()*office.getShippingPrice();
			}
		}
		return shipment.getPrice()+shipment.getWeight()*Constants.SHIPMENT_PRICE;
	}
}
