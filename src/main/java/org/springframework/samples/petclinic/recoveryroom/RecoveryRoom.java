package org.springframework.samples.petclinic.recoveryroom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recovery_rooms")
public class RecoveryRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    Integer id;
    
	@Size(min = 3, max = 50)
	@NotBlank
	@Column(name = "name")
    String name;
    
	@PositiveOrZero
	@NotBlank
	@Column(name = "size")
    double size;
    
	@NotEmpty
	@Column(name = "secure")
    boolean secure;
    
    @Transient
	@JoinColumn(name = "type")
    RecoveryRoomType roomType;
}
