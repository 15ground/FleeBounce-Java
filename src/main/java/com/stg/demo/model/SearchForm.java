package com.stg.demo.model;

public class SearchForm {
	private String name;
	private int page;
	private String sortBy;
	private boolean index;

	public SearchForm() {
		super();
		this.name = "";
		this.page = 0;
		this.sortBy = "id";
		this.index = true;
	}

	public SearchForm(String name, int page, String sortBy, boolean index) {
		super();
		this.name = name;
		this.page = page;
		this.sortBy = sortBy;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isIndex() {
		return index;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

}
