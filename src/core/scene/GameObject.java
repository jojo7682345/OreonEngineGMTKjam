 package core.scene;

import java.util.HashMap;

public class GameObject extends Node{

	private HashMap<String, Component> components;
	private String name = "-";
	
	public GameObject()
	{
		components = new HashMap<String, Component>();
	}
	
	public synchronized void allocate() {
		
	}
	
	public void addComponent(String string, Component component)
	{
		component.setParent(this);
		components.put(string, component);
	}
	
	public void update()
	{	
		for (String key : components.keySet()) {
			components.get(key).update();
		}
		
		super.update();
	}
	
	public void input()
	{
		for (String key : components.keySet()) {
			components.get(key).input();
		}
		
		super.input();
	}
	
	public void render()
	{
		for (String key : components.keySet()) {
			components.get(key).render();
		}
		
		super.render();
	}

	public HashMap<String, Component> getComponents() {
		return components;
	}
	
	public Component getComponent(String component)
	{
		return this.components.get(component);
	}

	public boolean hasComponent(String modelComponent) {
		return components.containsKey(modelComponent);
	}

	public void destroy() {
		for (String key : components.keySet()) {
			components.get(key).destroy();
		}
		
		super.destroy();
		onDestroy();
	}
	
	public void onDestroy() {};
	public void onCreate() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};


	
}
