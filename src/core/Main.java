package core;

import core.kernel.Game;
import core.utils.SceneLoader;

public class Main {
	public static void main(String[] args) {
		Game game = new Game();
		game.getEngine().createWindow(1270, 720);
		game.init();
		SceneLoader.loadScene("mainScene.scene");
		game.launch();
	}

}
