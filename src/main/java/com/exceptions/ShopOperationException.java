package com.exceptions;

public class ShopOperationException extends RuntimeException {
	/**
	 * 店铺操作的异常类
	 */
	private static final long serialVersionUID=2361446884822298905L;
	public ShopOperationException(String msg) {
		super(msg);
	}
}
