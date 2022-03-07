package kodlamaio.northwind.Entities.dtos;


import kodlamaio.northwind.Entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithProductDto {
	int categoryid;
	private String categoryName;
	private Product products; 
}
