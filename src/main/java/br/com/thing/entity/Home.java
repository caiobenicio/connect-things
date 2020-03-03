package br.com.thing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Home extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name="user_id")
//    private User user;
    
    public Home() {
    }

	public Home(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
