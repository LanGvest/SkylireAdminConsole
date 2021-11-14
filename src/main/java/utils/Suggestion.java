package utils;

public class Suggestion {

	private String email;
	private String icon;
	private String color;
	private String hash;

	public Suggestion() {}

	public Suggestion(String email, String icon, String color, String hash) {
		this.email = email;
		this.icon = icon;
		this.color = color;
		this.hash = hash;
	}

	public String getEmail() {
		return email;
	}

	public String getIcon() {
		return icon;
	}

	public String getColor() {
		return color;
	}

	public String getHash() {
		return hash;
	}

	@Override
	public String toString() {
		return "Suggestion" + (hash.equals("") ? "" : "#" + hash) + "{email=" + email + ", color=" + color + ", icon=" + icon + "}";
	}
}