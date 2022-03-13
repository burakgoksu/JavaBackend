package kodlamaio.northwind.API.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.Business.abstracts.UserService;
import kodlamaio.northwind.Core.utilities.entities.User;
import kodlamaio.northwind.Core.utilities.results.DataResult;
import kodlamaio.northwind.Core.utilities.results.ErrorDataResult;

import org.springframework.validation.FieldError;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		 return ResponseEntity.ok(this.userService.add(user));
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@Valid @RequestBody User user) {
		 return ResponseEntity.ok(this.userService.delete(user));
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody User user) {
		 return ResponseEntity.ok(this.userService.update(user));
	}
	
	@GetMapping("/getall")
	public DataResult<List<User>> getAll(){
		return this.userService.getall();
	}
	
	@GetMapping("/getAllByPage")
	public DataResult<List<User>> getAllByPage(@Valid @RequestParam int pageNo,@Valid @RequestParam int pageSize){
		return this.userService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getAllAsc")
	public DataResult<List<User>> getAllSorted(){
		return this.userService.getAllSorted();
	}
	
	@GetMapping("/getByEmailAndId")
	public DataResult<User> getByEmailAndId(@Valid @RequestParam("email") String email,@Valid @RequestParam("id") int id){
		return this.userService.getByUserEmailAndUserId(email,id);
	}
	
	@GetMapping("/getByEmailOrId")
	public DataResult<List<User>> getByEmailOrId(@Valid @RequestParam("email") String email,@Valid  @RequestParam("id") int id){
		return this.userService.getByUserEmailOrUserId(email,id);
	}
	
	@GetMapping("/getByEmailContains")
	public DataResult<List<User>> getByEmailContains(@RequestParam("email") String email){
		return this.userService.getByEmailContains(email);
	}
	
	@GetMapping("/getByEmailStartsWith")
	public DataResult<List<User>> getByEmailStartsWith(@RequestParam("email") String email){
		return this.userService.getByEmailStartsWith(email);
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
