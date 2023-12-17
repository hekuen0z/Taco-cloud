package edu.taco_cloud;

import edu.taco_cloud.models.Ingredient;
import edu.taco_cloud.models.Taco;
import edu.taco_cloud.repositories.IngredientRepository;
import edu.taco_cloud.repositories.TacoRepository;
import edu.taco_cloud.repositories.UserRepository;
import edu.taco_cloud.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import java.util.Arrays;

import static edu.taco_cloud.models.Ingredient.*;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

//	/**
//	 * Метод инициализации таблиц базы данных H2
//	 * @param dataSource
//	 * @return - создает и заполняет значениями In-memory H2
//	 */
//	@Bean
//	public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
//		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//		resourceDatabasePopulator.addScript(new ClassPathResource("/schema.sql"));
//		resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
//
//		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//		dataSourceInitializer.setDataSource(dataSource);
//		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//
//		return dataSourceInitializer;
//	}
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("schema.sql")
				//.addScript("data.sql")
				.build();
	}

	@Bean
	public CommandLineRunner dataLoader(
			IngredientRepository repo,
			UserRepository userRepo,
			PasswordEncoder encoder,
			TacoRepository tacoRepo) {
		return args -> {
			Ingredient flourTortilla = new Ingredient(
					"FLTO", "Flour Tortilla", Type.WRAP);
			Ingredient cornTortilla = new Ingredient(
					"COTO", "Corn Tortilla", Type.WRAP);
			Ingredient groundBeef = new Ingredient(
					"GRBF", "Ground Beef", Type.PROTEIN);
			Ingredient carnitas = new Ingredient(
					"CARN", "Carnitas", Type.PROTEIN);
			Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
			Ingredient lettuce = new Ingredient(
					"LETC", "Lettuce", Type.VEGGIES);
			Ingredient cheddar = new Ingredient(
					"CHED", "Cheddar", Type.CHEESE);
			Ingredient jack = new Ingredient(
					"JACK", "Monterrey Jack", Type.CHEESE);
			Ingredient salsa = new Ingredient(
					"SLSA", "Salsa", Type.SAUCE);
			Ingredient sourCream = new Ingredient(
					"SRCR", "Sour Cream", Type.SAUCE);
			repo.save(flourTortilla);
			repo.save(cornTortilla);
			repo.save(groundBeef);
			repo.save(carnitas);
			repo.save(tomatoes);
			repo.save(lettuce);
			repo.save(cheddar);
			repo.save(jack);
			repo.save(salsa);
			repo.save(sourCream);
			Taco taco1 = new Taco();
			taco1.setName("Carnivore");
			taco1.setIngredients(Arrays.asList(
					flourTortilla, groundBeef, carnitas,
					sourCream, salsa, cheddar));
			tacoRepo.save(taco1);
			Taco taco2 = new Taco();
			taco2.setName("Bovine Bounty");
			taco2.setIngredients(Arrays.asList(
					cornTortilla, groundBeef, cheddar,
					jack, sourCream));
			tacoRepo.save(taco2);
			Taco taco3 = new Taco();
			taco3.setName("Veg-Out");
			taco3.setIngredients(Arrays.asList(
					flourTortilla, cornTortilla, tomatoes,
					lettuce, salsa));
			tacoRepo.save(taco3);
		};
	}

}
