package wenxin.billyspring;

class ClassA {
	IClassB instanceOfClassB;
	Name name;
	String alias;

	public void setFinder(IClassB instanceOfClassB) {
		this.instanceOfClassB = instanceOfClassB;
	}
	public void setName(Name name) {
		this.name = name;
	}

	public void doExecute() {
		System.out.print(name.toString());
	}

	public void addAlias(String alias) {
		System.out.println("-------addAlias-------");
		this.alias = alias;
	}

	public void delAlias() {
		System.out.println("-------delAlias-------");
		this.alias = null;
	}
}