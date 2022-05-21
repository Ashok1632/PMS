package com.yash.pms.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
	private Integer productId;
private String productName;
private String serialName;
private String description;
private String imageName;
private Date addedDate;
private CategoryDto category;
private UserDto user;


}
