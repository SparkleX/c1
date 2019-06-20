package com.next.jpatis.core;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.Data;

@Data
public class A {
	@Column Integer a;
	@Column String b;
	@Column Timestamp c;
	@Column BigDecimal d;
}
