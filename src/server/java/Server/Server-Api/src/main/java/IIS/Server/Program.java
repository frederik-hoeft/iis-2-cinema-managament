package IIS.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import baseTypes.Rational;
import generated.cinemaService.PriceCategoryBox;
import generated.cinemaService.PriceCategoryServiceBox;
import generated.cinemaService.PriceCategoryStalls;

// Annotation
@SpringBootApplication
public class Program {

	public static void main(String[] args) {
		// ignore this :)
		if (!PriceCategoryStalls.getInstance().getPrice().isPresent())
		{
			PriceCategoryStalls.getInstance().setPrice(new Rational(10));
			PriceCategoryBox.getInstance().setPrice(new Rational(12));
			PriceCategoryServiceBox.getInstance().setPrice(new Rational(16));
		}
		SpringApplication.run(
			Program.class, args);
	}
}
