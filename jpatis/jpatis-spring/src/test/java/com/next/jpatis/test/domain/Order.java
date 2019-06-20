package com.next.jpatis.test.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="[Order]")
public class Order {
	@Id @Column Integer id;
	@Column String bpCode;
	@Column Timestamp docDate;
	@Column BigDecimal docTotal;
}
