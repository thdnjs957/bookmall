package bookmallFinal.vo;

public class OrdersVo {
	
	private Long no;
	private String order_no;
	private String name;
	private int price;
	private String receive_addr;
	private Long member_no;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getReceive_addr() {
		return receive_addr;
	}
	public void setReceive_addr(String receive_addr) {
		this.receive_addr = receive_addr;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", order_no=" + order_no + ", name=" + name + ", price=" + price
				+ ", receive_addr=" + receive_addr + ", member_no=" + member_no + "]";
	}
	
}
