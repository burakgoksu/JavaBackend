package kodlamaio.northwind.Business.concretes;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import kodlamaio.northwind.Business.abstracts.UserService;
import kodlamaio.northwind.Core.dataAccess.UserDao;
import kodlamaio.northwind.Core.utilities.entities.User;
import kodlamaio.northwind.Core.utilities.results.DataResult;
import kodlamaio.northwind.Core.utilities.results.Result;
import kodlamaio.northwind.Core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.Core.utilities.results.SuccessResult;


@Service
public class UserManager implements UserService{ 
	
	private UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	
	

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("User Added");
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.getByEmail(email),"The User is found");
	}



	@Override
	public DataResult<List<User>> getall() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll());
	}



	@Override
	public DataResult<User> getByUserEmailAndUserId(String email, int id) {
		//Business Codes
		return new SuccessDataResult<User>(this.userDao.getByEmailAndId(email,id),"User Listed");
	}



	@Override
	public DataResult<List<User>> getByUserEmailOrUserId(String email, int id) {
		return new SuccessDataResult<List<User>>(this.userDao.getByEmailOrId(email,id),"User Listed");
	}



	@Override
	public DataResult<List<User>> getByEmailStartsWith(String email) {
		return new SuccessDataResult<List<User>>(this.userDao.getByEmailStartsWith(email),"User Listed");
	}



	@Override
	public DataResult<List<User>> getByEmailContains(String email) {
		return new SuccessDataResult<List<User>>(this.userDao.getByEmailContains(email),"User Listed");
	}



	@Override
	public Result delete(User user) {
		this.userDao.delete(user);
		return new SuccessResult("User Deleted");
	}



	@Override
	public Result update(User user) {
		this.userDao.save(user);
		return new SuccessResult("User Updated");
	}



	@Override
	public DataResult<List<User>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.ASC, "email");
		return new SuccessDataResult<List<User>>(this.userDao.findAll(sort),"Successful");
	}



	@Override
	public DataResult<List<User>> getAll(int pageNo, int pageSize) {
		Pageable pageable =  PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<User>>(this.userDao.findAll(pageable).getContent(),"Successful");
	}


	

}
