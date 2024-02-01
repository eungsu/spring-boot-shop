package com.example.entity;

import java.time.LocalDateTime;

import com.example.constant.ItemSellStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter @Setter
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;
	
	@Column(name = "item_name", nullable = false)
	private String name;
	
	@Column(name = "item_price", nullable = false)
	private int price;
	
	@Column(name = "item_stock", nullable = false)
	private int stock;
	
	@Lob
	@Column(name = "item_description", nullable =  false)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "item_sell_status")
	private ItemSellStatus itemSellStatus;
	
	@Column(name = "item_created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "item_update_date")
	private LocalDateTime updatedDate;
}
