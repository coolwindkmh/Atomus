package project.space;

import java.util.ArrayList;



public class ObjectManager {
	ArrayList<Atom> objectList;
	public ObjectManager() {
		objectList = new ArrayList();
	}
	
	//°´Ã¼ µî·Ï
	public void addObject(Atom obj) {
		objectList.add(obj);
	}
	//°´Ã¼ Á¦°Å
	public void removeObject(Atom obj) {
		objectList.remove(obj);
	}
}
