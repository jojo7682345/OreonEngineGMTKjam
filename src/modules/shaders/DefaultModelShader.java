package modules.shaders;

import core.model.Material;
import core.renderer.ModelRenderer;
import core.scene.GameObject;
import core.shaders.Shader;
import core.utils.Constants;
import core.utils.ResourceLoader;

public class DefaultModelShader extends Shader {

	private static DefaultModelShader instance;

	public static DefaultModelShader getInstance() {
		if (instance == null) {
			instance = new DefaultModelShader();
		}
		return instance;
	}

	public DefaultModelShader() {
		addVertexShader(ResourceLoader.loadShader("shaders/defaultVS.glsl"));
		addFragmentShader(ResourceLoader.loadShader("shaders/defaultFS.glsl"));
		compileShader();

		addUniform("modelViewProjectionMatrix");
		addUniform("alpha");
		addUniform("color");
		addUniform("specularFactor");
		// addUniform("tex");
	}

	@Override public void updateUniforms(GameObject object) {
		setUniform("modelViewProjectionMatrix", object.getWorldTransform().getModelViewProjectionMatrix());
		if (object.hasComponent(Constants.MODEL_COMPONENT)) {
			// glActiveTexture(GL_TEXTURE0);
			// ((ModelRenderer)object.getComponent(Constants.MODEL_COMPONENT)).getModel().getMaterial().getDiffusemap().bind();
			ModelRenderer renderer = ((ModelRenderer) object.getComponent(Constants.MODEL_COMPONENT));
			if (renderer.getModel().getMaterial() != null) {
				setUniformf("alpha", renderer.getModel().getMaterial().getAlpha());
				setUniform("color", renderer.getModel().getMaterial().getColor());
				setUniformf("specularFactor", renderer.getModel().getMaterial().getShininess());
			}else {
				Material m = new Material();
				setUniformf("alpha", m.getAlpha());
				setUniform("color", m.getColor());
				setUniformf("specularFactor", m.getShininess());
			}
		}
		// setUniformi("tex", 0);
	}

}
