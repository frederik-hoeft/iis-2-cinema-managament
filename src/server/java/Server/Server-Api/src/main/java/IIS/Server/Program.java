package IIS.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import baseTypes.Rational;
import generated.cinemaService.PriceCategoryBox;
import generated.cinemaService.PriceCategoryServiceBox;
import generated.cinemaService.PriceCategoryStalls;

// Annotation
@SpringBootApplication
public class Program 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(
			Program.class, args);
	}
}
