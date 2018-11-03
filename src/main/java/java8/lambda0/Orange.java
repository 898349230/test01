package java8.lambda0;

public class Orange implements Fruit{
	private String color;
	private int weight;
	private int price;
	public Orange() {
		super();
	}
	public Orange(String color) {
		super();
		this.color = color;
	}
	public Orange(String color, int weight) {
		super();
		this.color = color;
		this.weight = weight;
	}
	
	public Orange(String color, int weight, int price) {
		super();
		this.color = color;
		this.weight = weight;
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Orange [color=" + color + ", weight=" + weight + ", price=" + price + "]";
	}
	
}
