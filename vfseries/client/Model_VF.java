package macross.vfseries.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class Model_VF extends ModelBase{
	private IModelCustom modelVF_Custom;
	
	ResourceLocation res = new ResourceLocation("vfseries","textures/models/vf1a.obj");
//	ResourceLocation res = new ResourceLocation("vfseries","textures/models/v-15e.mqo");
//	ResourceLocation res = new ResourceLocation("vfseries","textures/models/a-10.obj");
	
	public Model_VF(){
		modelVF_Custom = AdvancedModelLoader.loadModel(res);
	}
	

	public void render(Entity entity,float posX,float posY,float posZ,float posYaw,float posPitch,float p9){
		modelVF_Custom.renderAll();
	}
	
}