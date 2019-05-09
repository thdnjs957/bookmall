package bookmallFinal.vo;

public class OrderBookVo {
	private Long no;
	private int count;
	private Long book_no;
	private Long order_no;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	
	@Override
	public String toString() {
		return "BookOrderVo [no=" + no + ", count=" + count + ", book_no=" + book_no + ", order_no=" + order_no + "]";
	}
	
}
