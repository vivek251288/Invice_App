package com.javaCoder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public String invoiceNumber;
	private String customerName;
	private Double tax;
	private Double totalAmount;
	private String transactionId;
	private String timestamp;

	@ManyToOne
	private Dealer dealer;

	@ManyToOne
	private Vehicle vehicle;

	private String pdfUrl; // 🔗 store uploaded file URL or path
}
