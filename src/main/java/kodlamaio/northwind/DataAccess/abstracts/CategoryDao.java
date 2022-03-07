package kodlamaio.northwind.DataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import kodlamaio.northwind.Entities.concretes.Category;
import kodlamaio.northwind.Entities.dtos.CategoryWithProductDto;

public interface CategoryDao extends JpaRepository<Category,Integer>{
	
	
	

	Category getByCategoryName(String categoryName);
	
	Category getByCategoryNameAndDescription(String categoryName, String description);
	
	List<Category> getByCategoryNameOrDescription(String categoryName, String description);
	
	List<Category> getByCategoryNameContains(String categoryName);
	
	List<Category> getByCategoryNameStartsWith(String categoryName);
	
	List<Category> getByDescriptionContains(String description);
	
	@Query("Select new kodlamaio.northwind.Entities.dtos.CategoryWithProductDto(c.id, c.categoryName, p) From Product p Inner Join p.category c where c.categoryName=:categoryName")
	List<CategoryWithProductDto> getByCategoryNameAndProducts(String categoryName);
	
	
	
	
}
