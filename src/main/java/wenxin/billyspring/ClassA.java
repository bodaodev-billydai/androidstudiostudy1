package wenxin.billyspring;

class ClassA {
	IClassB instanceOfClassB;
	Name name;

	public void setFinder(IClassB instanceOfClassB) {
		this.instanceOfClassB = instanceOfClassB;
	}
	public void setName(Name name) {
		this.name = name;
	}

	public void doExecute() {
		System.out.print(name.toString());
	}
}