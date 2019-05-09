package bookmallFinal.vo;

public class BookVo {
	
	private Long no;
	private int price;
	private String title;
	private Long category_no;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", price=" + price + ", title=" + title + ", category_no=" + category_no + "]";
	}
	
	
}
