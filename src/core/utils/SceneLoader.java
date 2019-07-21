package core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.kernel.CoreEngine;
import core.math.Transform;
import core.scene.GameObject;

public class SceneLoader {
	private static List<GameObject>	loadedObjects	= new ArrayList<>();
	private static boolean			finished;

	public static void loadScene(String string) {
		Thread thread = new Thread(() -> {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File("./res/scenes/" + string)));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] data = line.split("/");
					GameObject object = (GameObject) (Class.forName(data[0]).newInstance());
					object.setWorldTransform(Transform.fromString(data[1]));
					object.setLocalTransform(Transform.fromString(data[2]));
					object.setName(data[3]);
					loadedObjects.add(object);
				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			finished = true;
		});
		thread.start();
	}

	public static void loadObject(GameObject o) {
		Thread thread = new Thread(() -> {

			loadedObjects.add(o);
			finished = true;
		});
		thread.start();
	}

	public static void loadScene(File file) {
		Thread thread = new Thread(() -> {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] data = line.split("/");
					GameObject object = (GameObject) (Class.forName(data[0]).newInstance());
					object.setWorldTransform(Transform.fromString(data[1]));
					object.setLocalTransform(Transform.fromString(data[2]));
					object.setName(data[3]);
					loadedObjects.add(object);
				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			finished = true;
		});
		thread.start();
	}

	public static void update(CoreEngine game) {
		if (finished) {
			game.clearObjects();
			for (GameObject o : loadedObjects) {
				game.addObject(o);
			}
			finished = false;
		}
	}

}
