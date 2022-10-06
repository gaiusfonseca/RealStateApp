package com.realestateapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ApartmentRaterTest {

	@ParameterizedTest(name = "area={0}, price={1}, result={2}")
	@CsvSource({
		"25, 149975.5, 0",
		"50, 399950.5, 1",
		"75, 900000, 2"
	})
	void should_ReturnCorrectRating_When_CorrectApartment(double area, BigDecimal price, int result) {
		//given
		Apartment apartment = new Apartment(area, price);
		int expected = result;
		
		//when
		int actual = ApartmentRater.rateApartment(apartment);
		
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	void should_ReturnErrorValue_When_IncorrectApartment() {
		//given
		Apartment apartment = new Apartment(0.0, new BigDecimal(10000));
		int expected = -1;
				
		//when
		int actual = ApartmentRater.rateApartment(apartment);
		
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	void should_CalculateAverageRating_When_CorrectApartmentList() {
		//given
		List<Apartment> apartments = new ArrayList<>();
		apartments.add(new Apartment(25, new BigDecimal(149975)));
		apartments.add(new Apartment(50, new BigDecimal(399950)));
		apartments.add(new Apartment(75, new BigDecimal(900000)));
		double expected = 1; 
		
		//when
		double actual = ApartmentRater.calculateAverageRating(apartments);
		
		//then
		assertEquals(expected, actual);
	}

	@Test
	void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
		//given
		List<Apartment> apartments = new ArrayList<>();
		
		//when
		Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);
		
		//then
		assertThrows(RuntimeException.class, executable);
	}
}
