package kodlamaio.northwind.API.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import kodlamaio.northwind.Business.abstracts.ProductService;
import kodlamaio.northwind.Core.utilities.results.DataResult;
import kodlamaio.northwind.Core.utilities.results.ErrorDataResult;
import kodlamaio.northwind.Entities.concretes.Product;
import kodlamaio.northwind.Entities.dtos.ProductWithCategoryDto;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	
	private ProductService productService;
	
	@Autowired
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/getall")
	public DataResult<List<Product>> getAll(){
		return this.productService.getAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Product product) {
		return ResponseEntity.ok(this.productService.add(product));
	}
	
	@PostMapping("/delete")
	public  ResponseEntity<?> delete(@Valid @RequestBody Product product) {
		return ResponseEntity.ok(this.productService.delete(product));
	}
	
	@PostMapping("/update")
	public  ResponseEntity<?> update(@Valid @RequestBody Product product) {
		return ResponseEntity.ok(this.productService.update(product));
	}
	
	@GetMapping("/getByProductName")
	public DataResult<Product> getByProductName(@Valid @RequestParam String productName){
		return this.productService.getByProductName(productName);
	}
	
	@GetMapping("/getByProductNameOrCategoryId")
	public DataResult<List<Product>> getByProductNameOrCategoryId(@Valid @RequestParam("productName") String productName,@Valid  @RequestParam("categoryId") int categoryId){
		return this.productService.getByProductNameOrCategoryId(productName,categoryId);
	}
	  
	@GetMapping("/getByProductNameAndCategoryId")
	public DataResult<Product> getByProductNameAndCategoryId(@Valid @RequestParam("productName") String productName,@Valid  @RequestParam("categoryId") int categoryId){
		return this.productService.getByProductNameAndCategoryId(productName,categoryId);
	}
	
	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@Valid @RequestParam("productName") String productName){
		return this.productService.getByProductNameContains(productName);
	}
	
	@GetMapping("/getAllByPage")
	public DataResult<List<Product>> getAll(@Valid @RequestParam int pageNo,@Valid @RequestParam int pageSize){
		return this.productService.getAll(pageNo,pageSize);
	}
	
	@GetMapping("/getAllDesc")
	public DataResult<List<Product>> getAllSorted(){
		return this.productService.getAllSorted();
	}

	
	@GetMapping("/getProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails(){
		return this.productService.getProductWithCategoryDetails();
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
