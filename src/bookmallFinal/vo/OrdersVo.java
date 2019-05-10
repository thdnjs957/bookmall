package bookmallFinal.vo;

public class OrdersVo {
	
	private Long no;
	private String order_no;
	private String name;
	private int price;
	private String receive_address;
	private Long member_no;
	private String email;
	
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
		return receive_address;
	}
	public void setReceive_addr(String receive_address) {
		this.receive_address = receive_address;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", order_no=" + order_no + ", name=" + name + ", price=" + price
				+ ", receive_addr=" + receive_address + ", member_no=" + member_no + ", email=" + email + "]";
	}
	
}
