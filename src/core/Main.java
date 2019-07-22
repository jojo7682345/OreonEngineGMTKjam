package core;

import core.kernel.Game;
import core.kernel.ModelLoader;
import core.utils.SceneLoader;

public class Main {
	
	public static void main(String[] args) {
		Game game = new Game();
		game.getEngine().createWindow(1270, 720, true);
		game.init();
		ModelLoader.load();
		SceneLoader.loadScene("mainScene.scene");
		game.launch();
	}

}
