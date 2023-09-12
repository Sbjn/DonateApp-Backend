package com.donate.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
		/**
	 * 
	 */

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		private String name;
		@Column(nullable = false,unique = true)
		private String username;
		@Column(nullable = false, unique = true)
		private String email;
		@Column(length = 64, nullable = false)
		private String password;
		
		
		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(name ="users_roles",
			joinColumns = @JoinColumn(name ="user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name ="role_id", referencedColumnName = "id")
				)
		private Set<Role> roles = new HashSet<>();
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		List<Post> posts = new ArrayList<>();
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		List<Messenger> messengers = new ArrayList<>();
		
		


}
