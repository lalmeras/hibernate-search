package org.hibernate.search.test.embedded.fieldoncollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Indexed
@Entity
public class Root {

	@Id
	@GeneratedValue
	@DocumentId
	private Integer id;

	@Field
	private String name;

	@OneToOne
	@IndexedEmbedded
	private Leaf leaf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Leaf getLeaf() {
		return leaf;
	}

	public void setLeaf(Leaf leaf) {
		this.leaf = leaf;
	}

}
