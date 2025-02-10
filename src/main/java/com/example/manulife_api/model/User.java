package com.example.manulife_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.time.ZoneId;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nama;
    private String alamat;
	
	@Column(name = "jenis_user")
	private String jenisUser;
	
	public String getJenisUser(){
		return jenisUser;
	}
	
	public void setJenisUser(String jenisUser) {
        this.jenisUser = jenisUser;
    }

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;
	
	public Date getCreateDateAsDate() {
        return (createDate != null) ? Date.from(createDate.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }
}
