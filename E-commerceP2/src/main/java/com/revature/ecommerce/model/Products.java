package com.revature.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Products {

	@Id
	@GeneratedValue
	private int id;
	private String pDescription;
	private String pCategory;
	@NotEmpty
	@Column(name="manufacturer")
	private String pManufacturer;
	private String pName;
	@NotNull
	@Column(name="price")
	private String pPrice;
	private String stock;
	private String pImage;
	
	
	
	public Products(String pDescription, String pCategory, @NotEmpty String pManufacturer, String pName,
			@NotNull String pPrice, String stock, String pImage) {
		super();
		this.pDescription = pDescription;
		this.pCategory = pCategory;
		this.pManufacturer = pManufacturer;
		this.pName = pName;
		this.pPrice = pPrice;
		this.stock = stock;
		this.pImage = pImage;
	}
	
	
	
	

}
