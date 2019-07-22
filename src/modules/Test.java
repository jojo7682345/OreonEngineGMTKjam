package modules;

import core.buffers.MeshVBO;
import core.configs.Default;
import core.kernel.ModelLoader;
import core.model.Model;
import core.renderer.ModelRenderer;
import core.renderer.RenderInfo;
import core.renderer.Renderer;
import core.scene.GameObject;
import core.utils.Constants;
import modules.shaders.DefaultModelShader;

public class Test extends GameObject {

	// Can be on another thread;
	public void onCreate() {
		Model model = ModelLoader.get("quad", 0);
		Renderer renderer = new ModelRenderer(model);
		addComponent(Constants.MODEL_COMPONENT, renderer);
	}

	public synchronized void allocate() {
		((Renderer) getComponent(Constants.MODEL_COMPONENT)).setRenderInfo(new RenderInfo(new Default(),
		        DefaultModelShader.getInstance()));
		Model model = ((ModelRenderer) getComponent(Constants.MODEL_COMPONENT)).getModel();
		MeshVBO vbo = new MeshVBO();
		vbo.allocate(model.getMesh());
		((Renderer) getComponent(Constants.MODEL_COMPONENT)).setVbo(vbo);
	}

}
