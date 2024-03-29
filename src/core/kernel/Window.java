package core.kernel;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

/**
 * 
 * @author oreon3D
 * GLFW Window implementation
 *
 */
public class Window {

	private static Window instance = null;

	public static boolean is3D = true;
	
	private long window;
	private int width;
	private int height;
	
	public static Window getInstance() 
	{
	    if(instance == null) 
	    {
	    	instance = new Window();
	    }
	      return instance;
	}
	
	public void init(){}
	
	public void create(int width, int height, boolean is3D)
	{
		setWidth(width);
		setHeight(height);
		glfwWindowHint(GLFW_RESIZABLE, GLFW.GLFW_FALSE);	
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);	
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);	
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);	
		
		window = glfwCreateWindow(width, height, "OREON ENGINE", 0, 0);
		
		if(window == 0) {
		    throw new RuntimeException("Failed to create window");
		}
		Window.is3D = is3D;
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glfwShowWindow(window);
	}
	
	public void render()
	{
		glfwSwapBuffers(window);
	}
	
	public void dispose()
	{
		glfwDestroyWindow(window);
	}
	
	public boolean isCloseRequested()
	{
		return glfwWindowShouldClose(window);
	}
	
	public void setWindowSize(int x, int y){
		GLFW.glfwSetWindowSize(window, x, y);
		
		setHeight(y);
		setWidth(x);
		Camera.getInstance().setProjection(70, x, y);
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public long getWindow() {
		return window;
	}

	public void setWindow(long window) {
		this.window = window;
	}
}
