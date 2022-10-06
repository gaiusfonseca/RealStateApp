package com.realestateapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.RepeatedTest;

class RandomApartmentGeneratorTest {

	@RepeatedTest(value = 100)
	void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
		//given
		Apartment apartment = new RandomApartmentGenerator().generate();
		double minArea = 30;
		double maxArea = 120;
		BigDecimal minPriceSqrMeter = new BigDecimal(3000);
		BigDecimal maxPriceSqrMeter = new BigDecimal(12000);
		
		//when
		double actualArea = apartment.getArea();
		BigDecimal actualPriceSqrMeter = apartment.getPrice().divide(new BigDecimal(apartment.getArea()), 2, RoundingMode.FLOOR);
		
		//then
		assertAll(
				() -> assertTrue(actualArea >= minArea && actualArea <= maxArea),
				() -> assertTrue(actualPriceSqrMeter.compareTo(minPriceSqrMeter) > -1 && actualPriceSqrMeter.compareTo(maxPriceSqrMeter) < 1)
		);
	}
	
	@RepeatedTest(value = 100)
	void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
		//given
		double minArea = 50;
		double maxArea = minArea * 4;
		BigDecimal minPriceSqrMeter = new BigDecimal(4000);
		BigDecimal maxPriceSqrMeter = minPriceSqrMeter.multiply(new BigDecimal(4));
		Apartment apartment = new RandomApartmentGenerator(minArea, minPriceSqrMeter).generate();
		
		//when
		double actualArea = apartment.getArea();
		BigDecimal actualPriceSqrMeter = apartment.getPrice().divide(new BigDecimal(apartment.getArea()), 2, RoundingMode.FLOOR);
		
		//then
		System.out.printf("Area: %.2f PriceSqrMeter: %.2f%n", actualArea, actualPriceSqrMeter);
		assertAll(
				() -> assertTrue(actualArea >= minArea && actualArea <= maxArea),
				() -> assertTrue(actualPriceSqrMeter.compareTo(minPriceSqrMeter) > -1 && actualPriceSqrMeter.compareTo(maxPriceSqrMeter) < 1)
		);
		
	}
}
