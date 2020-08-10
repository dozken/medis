
package io.mersys.medis.documents.patient;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.TypeAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@TypeAlias("Address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address{
	
	@NotBlank
	private String region;
	
	@NotBlank
	private String city;
	
	private String addressType;
	
	private String address;
	
	private String phoneNumber;

}
