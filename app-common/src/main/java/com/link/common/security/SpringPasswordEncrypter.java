package com.link.common.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpringPasswordEncrypter
{

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String passwd = "abc123456";

		String ecpasswd = encoder.encode(passwd);

		System.out.println(ecpasswd);

		boolean aa = encoder.matches(passwd, ecpasswd);

		System.out.println(aa);

		// System.out.println( encoder.matches("abc1234564", ecpasswd) );

		System.out.println(encoder.matches("Dev@9012", encoder.encode("Dev@90121")));

	}
}
