package project.space;

import java.util.ArrayList;



public class ObjectManager {
	ArrayList<Atom> objectList;
	public ObjectManager() {
		objectList = new ArrayList();
	}
	
	//��ü ���
	public void addObject(Atom obj) {
		objectList.add(obj);
	}
	//��ü ����
	public void removeObject(Atom obj) {
		objectList.remove(obj);
	}
}
