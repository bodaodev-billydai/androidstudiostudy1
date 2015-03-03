package wenxin.billyspring;

class Name {
	String name;
	String alias;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public void addAlias(String alias) {
		this.alias = alias;
	}

	public void delAlias() {
		this.alias = null;
	}
}