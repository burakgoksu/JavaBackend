package kodlamaio.northwind.API.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.Business.abstracts.CategoryService;
import kodlamaio.northwind.Core.utilities.results.DataResult;
import kodlamaio.northwind.Core.utilities.results.ErrorDataResult;
import kodlamaio.northwind.Entities.concretes.Category;
import kodlamaio.northwind.Entities.dtos.CategoryWithProductDto;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

	private CategoryService categoryService;

	@Autowired
	public CategoriesController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Category category) {
		return ResponseEntity.ok(this.categoryService.add(category));
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@Valid @RequestBody Category category) {
		return ResponseEntity.ok(this.categoryService.delete(category));
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Category category) {
		return ResponseEntity.ok(this.categoryService.update(category));
	}
	
	@GetMapping("/getAllAsc")
	public DataResult<List<Category>> getAllSorted(){
		return this.categoryService.getAllSorted();
	}
	
	@GetMapping("/getAllByPage")
	public DataResult<List<Category>> getAll(@Valid @RequestParam int pageNo,@Valid @RequestParam int pageSize){
		return this.categoryService.getAll(pageNo,pageSize);
	}
	
	
	@GetMapping("/getByCategoryName")
	public DataResult<Category> getByCategoryName(@Valid @RequestParam String categoryName){
		return this.categoryService.getByCategoryName(categoryName);
	}
	
	@GetMapping("/getByCategoryNameAndDescription")
	public DataResult<Category> getByCategoryNameAndDescription(@Valid @RequestParam String categoryName ,@Valid @RequestParam String description){
		return this.categoryService.getByCategoryNameAndDescription(categoryName,description);
	}
	
	@GetMapping("/getByCategoryNameOrDescription")
	public DataResult<List<Category>> getByCategoryNameOrDescription(@Valid @RequestParam String categoryName ,@Valid @RequestParam String description){
		return this.categoryService.getByCategoryNameOrDescription(categoryName,description);
	}
	
	@GetMapping("/getByCategoryNameContains")
	public DataResult<List<Category>> getByCategoryNameContains(@Valid @RequestParam String categoryName){
		return this.categoryService.getByCategoryNameContains(categoryName);
	}
	
	@GetMapping("/getByCategoryNameStartsWith")
	public DataResult<List<Category>> getByCategoryNameStartsWith(@Valid @RequestParam String categoryName){
		return this.categoryService.getByCategoryNameStartsWith(categoryName);
	}
	
	@GetMapping("/getByDescriptionContains")
	public DataResult<List<Category>> getByDescriptionContains(@Valid @RequestParam String description){
		return this.categoryService.getByDescriptionContains(description);
	}
	
	@GetMapping("/getByCategoryNameAndProduct")
	public DataResult<List<CategoryWithProductDto>> getByCategoryNameAndProduct(String categoryName){
		return this.categoryService.getByCategoryNameAndProduct(categoryName);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception){
		Map<String,String> valitadionErrors = new HashMap<String, String>();
		for(FieldError fieldError :  exception.getBindingResult().getFieldErrors()) {
			valitadionErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(valitadionErrors,"validation errors ");
		return errors;
	}
	
	
	
	
	
	
	
	
	
}
