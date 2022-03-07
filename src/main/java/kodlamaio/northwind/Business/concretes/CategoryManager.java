package kodlamaio.northwind.Business.concretes;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import kodlamaio.northwind.Business.abstracts.CategoryService;
import kodlamaio.northwind.Core.utilities.results.DataResult;
import kodlamaio.northwind.Core.utilities.results.Result;
import kodlamaio.northwind.Core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.Core.utilities.results.SuccessResult;
import kodlamaio.northwind.DataAccess.abstracts.CategoryDao;
import kodlamaio.northwind.Entities.concretes.Category;
import kodlamaio.northwind.Entities.dtos.CategoryWithProductDto;

@Service
public class CategoryManager implements CategoryService {
	
	private CategoryDao categoryDao;

	public CategoryManager(CategoryDao categoryDao) {
		super();
		this.categoryDao = categoryDao;
	}

	@Override
	public DataResult<List<Category>> getAll() {
		return new SuccessDataResult<List<Category>>(this.categoryDao.findAll());
	}

	@Override
	public DataResult<List<Category>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.ASC,"categoryName");
		return new SuccessDataResult<List<Category>>(this.categoryDao.findAll(sort),"Successful");
	}

	@Override
	public DataResult<List<Category>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Category>>(this.categoryDao.findAll(pageable).getContent(),"Successful");
	}

	@Override
	public Result add(Category category) {
		this.categoryDao.save(category);
		return new SuccessResult("Category Added");
	}

	@Override
	public Result delete(Category category) {
		this.categoryDao.delete(category);
		return new SuccessResult("Category Deleted");
	}

	@Override
	public Result update(Category category) {
		this.categoryDao.save(category);
		return new SuccessResult("Category Updated");
	}

	@Override
	public DataResult<Category> getByCategoryName(String categoryName) {
		return new SuccessDataResult<Category>(this.categoryDao.getByCategoryName(categoryName));
	}

	@Override
	public DataResult<Category> getByCategoryNameAndDescription(String categoryName, String description) {
		return new SuccessDataResult<Category>(this.categoryDao.getByCategoryNameAndDescription(categoryName, description));
	}

	@Override
	public DataResult<List<Category>> getByCategoryNameOrDescription(String categoryName, String description) {
		return new SuccessDataResult<List<Category>>(this.categoryDao.getByCategoryNameOrDescription(categoryName, description));
	}

	@Override
	public DataResult<List<Category>> getByCategoryNameContains(String categoryName) {
		return new SuccessDataResult<List<Category>>(this.categoryDao.getByCategoryNameContains(categoryName));
	}

	@Override
	public DataResult<List<Category>> getByCategoryNameStartsWith(String categoryName) {
		return new SuccessDataResult<List<Category>>(this.categoryDao.getByCategoryNameStartsWith(categoryName));
	}

	@Override
	public DataResult<List<Category>> getByDescriptionContains(String description) {
		return new SuccessDataResult<List<Category>>(this.categoryDao.getByDescriptionContains(description));
	}

	@Override
	
	public DataResult<List<CategoryWithProductDto>> getByCategoryNameAndProduct(String categoryName) {
		return new SuccessDataResult<List<CategoryWithProductDto>>(this.categoryDao.getByCategoryNameAndProducts(categoryName));

	}

	

}
