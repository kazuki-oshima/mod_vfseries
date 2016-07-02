package macross.vfseries.client;

import org.lwjgl.opengl.GL11;

import example.boat.common.EntitySample;
import macross.vfseries.common.Entity_VF;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class Render_VF extends Render{
	
	private static final ResourceLocation VF_textures = new ResourceLocation("vfseries","textures/items/vf1a.png");
	protected ModelBase model_VF;
	
	public Render_VF(){
		this.shadowSize = 0.5f;
		this.model_VF = new Model_VF();
	}
	
	public void doRender(Entity_VF entity_VF,double posx,double posy,double posz,float posYaw,float posPitch){
		
//		System.out.println("posPitch = "+posPitch);
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)posx,(float)posy,(float)posz);
		GL11.glRotatef(180.0F - posYaw, 0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(180.0F - posYaw, 0.0F, 0.0F, 1.0F);
		float var10 = (float)entity_VF.getTimeSinceHit() - posPitch;
		float var11 = entity_VF.getDamageTaken() - posPitch;
		
		
		if(var11 < 0.0F) {
			var11 = 0.0F;
		
//			System.out.println("var10 = "+var10);
//			System.out.println("var11 = "+var11);
//			System.out.println("Pitch = "+posPitch);
			
		}

		if(var10 > 0.0F) {
			GL11.glRotatef(MathHelper.sin(var10) * var10 * var11 / 10.0F * (float)entity_VF.getForwardDirection(), 1.0F, 0.0F, 0.0F);
			
//			System.out.println("RenderSample");
//			System.out.println("var10 > 0.0f");
		}

		float var12 = 0.75F;
//		GL11.glScalef(var12, var12, var12);
//		GL11.glScalef(1.0F / var12, 1.0F / var12, 1.0F / var12);
		
		
		this.bindEntityTexture(entity_VF);
//		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		
		
		GL11.glScalef(1.0f,1.0f,1.0f);
		this.model_VF.render(entity_VF, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
	
	
	
	

	@Override
	public void doRender(Entity entity,double posx,double posy,double posz,float posYaw,float posPitch){
		this.doRender((Entity_VF)entity,posx,posy,posz,posYaw,posPitch);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity){
		
		return this.getEntityTexture((Entity_VF)entity);
		
	}
	
	protected ResourceLocation getEntityTexture(Entity_VF entity_VF) {
		return VF_textures;
	}

}
