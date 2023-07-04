package spring.model.bean;

public class Like {
	private int id_like;
	private Land land;
	private User user;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId_like() {
		return id_like;
	}

	public void setId_like(int id_like) {
		this.id_like = id_like;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Like() {
		super();
	}

	public Like(int id_like, Land land, User user) {
		super();
		this.id_like = id_like;
		this.land = land;
		this.user = user;
		
	}

	public Like(int id_like, Land land, User user, int status) {
		super();
		this.id_like = id_like;
		this.land = land;
		this.user = user;
		this.status = status;
	}

}
