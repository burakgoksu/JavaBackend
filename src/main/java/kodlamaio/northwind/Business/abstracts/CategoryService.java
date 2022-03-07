package kodlamaio.northwind.Business.abstracts;

import java.util.List;

import kodlamaio.northwind.Core.utilities.results.DataResult;
import kodlamaio.northwind.Core.utilities.results.Result;
import kodlamaio.northwind.Entities.concretes.Category;
import kodlamaio.northwind.Entities.dtos.CategoryWithProductDto;

public interface CategoryService {
	
	DataResult<List<Category>> getAll();
	
	DataResult<List<Category>> getAllSorted();
	
	DataResult<List<Category>> getAll(int pageNo, int pageSize);
	
	Result add(Category category);
	
	Result delete(Category category);
	
	Result update(Category category);
	
	DataResult<Category> getByCategoryName(String categoryName);
	
    DataResult<Category> getByCategoryNameAndDescription(String categoryName, String description);
	
	DataResult<List<Category>> getByCategoryNameOrDescription(String categoryName, String description);
	
	DataResult<List<Category>> getByCategoryNameContains(String categoryName);
	
	DataResult<List<Category>> getByCategoryNameStartsWith(String categoryName);
	
	DataResult<List<Category>> getByDescriptionContains(String description);
	
	DataResult<List<CategoryWithProductDto>> getByCategoryNameAndProduct(String categoryName);
		

}
