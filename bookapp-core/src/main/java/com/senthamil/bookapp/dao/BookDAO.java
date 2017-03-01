package com.senthamil.bookapp.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.senthamil.bookapp.model.Book;
import com.senthamil.bookapp.util.ConnectionUtil;

public class BookDAO {

	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(Book book) {

		String sql = "insert into books(name,price) values(?,?)";
		Object[] args = { book.getName(), book.getPrice() };
		int rows = jdbcTemplate.update(sql, args);
		System.out.println("No. of rows inserted: " + rows);
	}

	public void update(Book book) {

		String sql = "update books set price=? where id=?";
		Object[] args = { book.getPrice(), book.getId() };
		int rows = jdbcTemplate.update(sql, args);
		System.out.println("No. of rows updated: " + rows);
	}

	public void delete(Integer id) {

		String sql = "delete from books where id=?";
		Object[] args = { id };
		int rows = jdbcTemplate.update(sql, args);
		System.out.println("No. of rows deleted: " + rows);
	}

	public Book findById(Integer id) {

		String sql = "select id,name,price from books where id=?";
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setName(rs.getString("name"));
			book.setPrice(rs.getFloat("price"));
			return book;
		});

	}

	public List<Book> findAll() {

		String sql = "select id,name,price from books";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setName(rs.getString("name"));
			book.setPrice(rs.getFloat("price"));
			return book;
		});

	}

}
