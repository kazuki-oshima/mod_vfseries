package macross.vfseries.common;

import java.util.List;

import org.lwjgl.Sys;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Entity_VF extends Entity{

	private boolean isVFEmpty;
	private double speedMultiplier;
	private int VFPosRotationIncrements;
	private double VFposX;
	private double VFposY;
	private double VFposZ;
	private double VFYaw;
	private double VFPitch;
	
	private double velocityX;
	private double velocityY;
	private double velocityZ;
	
	public Entity_VF(World world){
		super(world);
		this.isVFEmpty = true;
		this.speedMultiplier = 0.07D;
		this.preventEntitySpawning = true;
		this.setSize(1.5F, 0.6F);
		this.yOffset = this.height / 2.0F;
		
//		this.yOffset = 4.0f / 2.0f;
		
		System.out.println("this.height = "+this.height);
		
	}


	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Float(0.0f));
	}


	public AxisAlignedBB getCollisionBox(Entity entity) {
		return entity.boundingBox;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	public boolean canBePushed() {
		return true;
	}
	
	public Entity_VF(World world,double posx,double posy,double posz){

		this(world);
		
		this.setPosition(posx,posy+(double)this.yOffset,posz);
		this.motionX = 0.0d;
		this.motionY = 0.0d;
		this.motionZ = 0.0d;
		this.prevPosX = posx;
		this.prevPosY = posy;
		this.prevPosZ = posz;
	}
//
//	
	public double getMountedYOffset() {
		
		System.out.println("getMountedYOffset");
		return (double)this.height * 0.0D - 0.30000001192092896d;
	}
//
	public boolean attackEntityFrom(DamageSource damageSource, float f2) {
		System.out.println("attackEntityFrom");
		
		if(this.isEntityInvulnerable()) {
			return false;
		}else if(!this.worldObj.isRemote && !this.isDead) {
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + f2 * 10.0F);
			this.setBeenAttacked();
			
			boolean var3 = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode;
			if(var3 || this.getDamageTaken() > 40.0F){
				if(this.riddenByEntity != null) {
					this.riddenByEntity.mountEntity(this);
				}

				if(!var3) {
					this.func_145778_a(Items.boat, 1, 0.0F);
				}

				this.setDead();
			}

			return true;
		} else {
			return true;
		}
	}
//
	public void performHurtAnimation() {
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}
//
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}
//

	@Override
	public void setPositionAndRotation2(double x, double y,double z,float yaw,float pitch,int p9) {
		System.out.println("setPositionAndRotation2");
		
		
		if(this.isVFEmpty){
			this.VFPosRotationIncrements = p9 + 5;
			System.out.println("p9 = "+9);
		}else{
			double sphere_x = x - this.posX;
			double sphere_y = y - this.posY;
			double sphere_z = z - this.posZ;
			
			//double var16 = var10 * var10 + var12 * var12 + var14 * var14;
			
			//https://ja.wikipedia.org/wiki/%E7%90%83%E9%9D%A2
			//球面 r^2 = (x-x0)^2 + (y-y0)^2 + (z-z0)^2
//			double pow2_r = Math.pow(sphere_x,2) + Math.pow(sphere_y,2) + Math.pow(sphere_z,2);
			
			double pow2_r = Math.pow((x-this.posX),2) + Math.pow((y-this.posY),2) + Math.pow((z-this.posZ),2);
			
			if(pow2_r <= 1.0D) {
				return;
			}

			this.VFPosRotationIncrements = 3;
		}
		
		this.VFposX = x;
		this.VFposY = y;
		this.VFposZ = z;
		this.VFYaw = (double)yaw;
		this.VFPitch = (double)pitch;

		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}
	
//
	@Override
	public void setVelocity(double x,double y,double z){
		System.out.println("call setVelocity x,y,z= "+x+","+y+","+z);
		
		this.velocityX = this.motionX = x;
		this.velocityY = this.motionY = y;
		this.velocityZ = this.motionZ = z;
	}
//



//I made this Code 06/25 

	public void onUpdate_new(){
		
		
		for(int i=0;i<5;i++){
//			double new_boundingBox_minY1 = this.boundingBox.minY + 
		}
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		
//		this.setPosition(this.posX,this.posY,this.posZ);
//		this.setRotation(this.rotationYaw,this.rotationPitch);
//		
//		this.entityPlayer_test.mountEntity(this);
		
//		System.out.println("this.getTimeSinceHit() = "+this.getTimeSinceHit());
		
		if(this.getTimeSinceHit() > 0){
			System.out.println("this.getTimeSinceHit() = "+this.getTimeSinceHit());
		}
		
		if(this.getDamageTaken() > 0.0f){
			System.out.println("this.getDamageTaken() = "+this.getDamageTaken());
		}
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		double var2 = 0.0d;
		
		for(int i=0;i<5;i++){
			double new_boundingBox_minY_1 = this.boundingBox.minY +(this.boundingBox.maxY - this.boundingBox.minY) * (double)(i+0)/(double)5-0.125d;
			double new_boundingBox_minY_2 = this.boundingBox.minY +(this.boundingBox.maxY - this.boundingBox.minY) * (double)(i+1)/(double)5-0.125d;
			
//			System.out.println("new_boundingBox_minY_1 = "+new_boundingBox_minY_1);
//			System.out.println("new_boundingBox_minY_2 = "+new_boundingBox_minY_2);
			
			AxisAlignedBB boundingbox_pos = AxisAlignedBB.getBoundingBox(this.boundingBox.minX,new_boundingBox_minY_1,this.boundingBox.minZ,this.boundingBox.maxX,new_boundingBox_minY_2,this.boundingBox.maxZ);
//			System.out.println("var9 = "+boundingbox_pos);
			
			//水の中にいたら、以下の処理を実行する?
			if(this.worldObj.isAABBInMaterial(boundingbox_pos,Material.water)){
				var2 += 1.0d/(double)5;
				System.out.println("var2 = "+var2);
			}
		}
		
		double x2_z2 = Math.sqrt(Math.pow(this.motionX,2) + Math.pow(this.motionZ,2));
		double circle_x;
		double circle_y;
		
		if(x2_z2 > 0.26249999999999996D){
			//degree(角度) convert → radian
			circle_x = Math.cos((double)this.rotationYaw*Math.PI/180.0d);
			circle_y = Math.sin((double)this.rotationYaw*Math.PI/180.0d);
		
			for(int i=0;(double)i< (1.0d+x2_z2*60.0d);i++){
				//float型とint型の乱数を生成
				double random_numF = (double)(this.rand.nextFloat() * 2.0f - 1.0f);
				double random_numI = (double)(this.rand.nextInt(2) * 2-1) * 0.7d;
				
				double particle_posx;
				double particle_posy;
				
				//ランダムでtrueかfalseを選択
				if(this.rand.nextBoolean()){
					particle_posx = this.posX - (circle_x * random_numF * 0.8d) + (circle_y * random_numI);
					particle_posy = this.posZ - (circle_y * random_numF * 0.8d) - (circle_x * random_numI);
					this.worldObj.spawnParticle("splash",particle_posx,this.posY-0.125d,particle_posy,this.motionX,this.motionY,this.motionZ);
					System.out.println("particle_posx = "+particle_posx+":particle_posy = "+particle_posy);
				}else{
					particle_posx = this.posX + (circle_x) + (circle_y * random_numF * 0.7d);
					particle_posy = this.posZ + (circle_y) - (circle_x * random_numF * 0.7d);
					this.worldObj.spawnParticle("splash",particle_posx,this.posY-0.125d,particle_posy,this.motionX,this.motionY,this.motionZ);
					System.out.println("particle_posx = "+particle_posx+":particle_posy = "+particle_posy);
				}
			}		
		}
		
		double circle_z;
		double circle_yaw;
		if(!this.worldObj.isRemote && this.isVFEmpty){
			if(this.VFPosRotationIncrements > 0){
				circle_x = this.posX + (this.VFposX - this.posX) / (double)this.VFPosRotationIncrements;
				circle_y = this.posY + (this.VFposY - this.posY) / (double)this.VFPosRotationIncrements;
				circle_z = this.posZ + (this.VFposZ - this.posZ) / (double)this.VFPosRotationIncrements;
				circle_yaw = MathHelper.wrapAngleTo180_double(this.VFYaw - (double)this.rotationYaw);
				this.rotationYaw = (float)((double)this.rotationYaw + circle_yaw / (double)this.VFPosRotationIncrements);
				this.rotationPitch = (float)((double)this.rotationPitch + (this.VFPitch - (double)this.rotationPitch) / (double)this.VFPosRotationIncrements);

				--this.VFPosRotationIncrements;
				
				this.setPosition(circle_x,circle_y,circle_z);
				this.setRotation(this.rotationYaw,this.rotationPitch);
				
				
				System.out.println("IF x,y,z "+circle_x+","+circle_y+","+circle_z);
			}else{
				circle_x = this.posX + this.motionX;
				circle_y = this.posY + this.motionY;
				circle_z = this.posZ + this.motionZ;
				this.setPosition(circle_x,circle_y,circle_z);
				
				if(this.onGround){
					this.motionX *= 0.5d;
					this.motionY *= 0.5d;
					this.motionZ *= 0.5d;
				}
				
				this.motionX *= 0.9900000095367432D;
				this.motionY *= 0.949999988079071D;
				this.motionZ *= 0.9900000095367432D;
				
				System.out.println("ELSE x,y,z "+circle_x+","+circle_y+","+circle_z);
			}
		}else{
			if(var2 < 1.0d){
				circle_x = var2 * 2.0d - 1.0d;
				this.motionY += 0.03999999910593033D * circle_x;
			}else{
				if(this.motionY < 0.0d){
					this.motionY /= 2.0d;
				}
				
				this.motionY += 0.007000000216066837d;
			}
			
			if(this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase){
				EntityLivingBase entityLivingBase = (EntityLivingBase)this.riddenByEntity;
				
				System.out.println("entityLivingBase = "+entityLivingBase);
				float degree = this.riddenByEntity.rotationYaw + -entityLivingBase.moveStrafing * 90.0f;
				this.motionX += -Math.sin((double)(degree * (float)Math.PI / 180.0F)) * this.speedMultiplier * (double)entityLivingBase.moveForward * 0.05000000074505806d;
				this.motionZ +=  Math.cos((double)(degree * (float)Math.PI / 180.0F)) * this.speedMultiplier * (double)entityLivingBase.moveForward * 0.05000000074505806d;			
			}
			
			circle_x = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			if(circle_x > 0.35d){
				circle_y = 0.35d/circle_x;
				this.motionX *= circle_y;
				this.motionZ *= circle_y;
				circle_x = 0.35d;
				
			}
			
			if(circle_x > x2_z2 && this.speedMultiplier < 0.35d){
				this.speedMultiplier += (0.35d - this.speedMultiplier) / 35.0d;
				
				if(this.speedMultiplier > 0.35d){
					this.speedMultiplier = 0.35d;
				}
			}else{
				this.speedMultiplier -= (this.speedMultiplier - 0.07d) / 35.0d;
				if(this.speedMultiplier < 0.07d){
					this.speedMultiplier = 0.07d;
				}
			}
			
			int j,d,floor_double_Z;
			for(j=0;j<4;j++){
				int floor_double_X = MathHelper.floor_double(this.posX + ((double)(j % 2) - 0.5D) * 0.8d);
				floor_double_Z = MathHelper.floor_double(this.posZ + ((double)(j / 2) - 0.5d) * 0.8d);
			
				for(d=0;d<2;d++){
					int floor_double_Y = MathHelper.floor_double(this.posY) + d;
					Block block_pos = this.worldObj.getBlock(floor_double_X,floor_double_Y,floor_double_Z);
					if(block_pos == Blocks.snow_layer){
						this.worldObj.setBlockToAir(floor_double_X,floor_double_Y,floor_double_Z);
						this.isCollidedHorizontally = false;
					}else if(block_pos == Blocks.waterlily){
						this.worldObj.func_147480_a(floor_double_X,floor_double_Y,floor_double_Z,true);
						this.isCollidedHorizontally = false;
					}
				}
			}
			
			
			if(this.onGround){
				this.motionX *= 0.5d;
				this.motionY *= 0.5d;
				this.motionZ *= 0.5d;
			}
			
			
			this.moveEntity(this.motionX,this.motionY,this.motionZ);
			if(this.isCollidedHorizontally && x2_z2 > 0.2d){
				if(!this.worldObj.isRemote && !this.isDead){
					this.setDead();
					
					for(j=0;j<3;j++){
						this.func_145778_a(Item.getItemFromBlock(Blocks.planks),1,0.0f);
					}
					
					for(j=0;j<2;j++){
						this.func_145778_a(Items.stick,1,0.0f);
					}
				}
			}else{
				this.motionX *= 0.9900000095367432d;
				this.motionY *= 0.949999988079071d;
				this.motionZ *= 0.9900000095367432d;
			}
			
			this.rotationPitch = 0.0f;
			circle_y = (double)this.rotationYaw;
			circle_z = this.prevPosX - this.posX;
			circle_yaw = this.prevPosZ - this.posZ;
			if(circle_z * circle_z + circle_yaw * circle_yaw > 0.001d){
				circle_y = (double)((float)(Math.atan2(circle_yaw,circle_z) * 180.0d / Math.PI));
			}
			
			double add_Yaw = MathHelper.wrapAngleTo180_double(circle_x - (double)this.rotationYaw);
			if(add_Yaw > 20.0d){
				add_Yaw = 20.0d;
			}
			
			if(add_Yaw < -20.0d){
				add_Yaw = -20.0d;
			}
			
			this.rotationYaw = (float)((double)this.rotationYaw + add_Yaw);
			this.setRotation(this.rotationYaw,this.rotationPitch);
			if(this.worldObj.isRemote){
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,this.boundingBox.expand(0.20000000298023224d,0.0d,0.20000000298023224d));
				if(list != null && !list.isEmpty()){
					for(int l=0;l<list.size();l++){
						Entity entity = (Entity)list.get(l);
						if(entity != this.riddenByEntity && entity.canBePushed() && entity instanceof Entity_VF){
							entity.applyEntityCollision(this);
						}
					}
				}
				
				if(this.riddenByEntity != null && this.riddenByEntity.isDead){
					this.riddenByEntity = null;
				}
			}
			
			
		}
	}
	
	@Override
	public void updateRiderPosition(){
		System.out.println("updateRiderPosition()");
		if(this.riddenByEntity != null){
			
			//真上から見た場合、z軸方向が前。x軸が横になるから。
			double offset_x = Math.cos((double)this.rotationYaw * Math.PI / 180.0d) * 0.4d;
			double offset_z = Math.sin((double)this.rotationYaw * Math.PI / 180.0d) * 0.4d;
			
			System.out.println("rotationYaw = "+this.rotationYaw);
			this.riddenByEntity.setPosition(this.posX+offset_x,this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(),this.posZ+offset_z);
		}
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}
	
	public float getShadowSize() {
		return 0.0f;
	}
	
	
	
	@Override
	public boolean interactFirst(EntityPlayer entityPlayer){
		super.interactFirst(entityPlayer);
		
		if(this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != entityPlayer){
			return true;
		}else{
			if(this.worldObj.isRemote){
				entityPlayer.mountEntity(this);
			}
		}
		
		System.out.println("interactFirst");
		return true;
		
	}
	
	
//	@Override
	public boolean interact(EntityPlayer entityPlayer){
		
		System.out.println("interact !!");
		
		if(this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != entityPlayer){
//			System.out.println("this.riddenByEntity = "+this.riddenByEntity);
			return true;
		}else{
			if(!this.worldObj.isRemote){
//				System.out.println("this.riddenByEntity = "+this.riddenByEntity);
				entityPlayer.mountEntity(this);
			}
			
			return true;
		}
	}

	
	protected void updateFallState(double d1,boolean d2){
		
//		System.out.println("updateFallState(d1, d2)");
//		System.out.println("updateFallState d1 = "+d1);
		
		int posx = MathHelper.floor_double(this.posX);
		int posy = MathHelper.floor_double(this.posY);
		int posz = MathHelper.floor_double(this.posZ);
		
		if(d2){
			if(this.fallDistance > 3.0f){
				this.fall(this.fallDistance);
				if(this.worldObj.isRemote && !this.isDead){
					this.setDead();
					
					int i;
					for(i=0;i<3;i++){
						this.func_145778_a(Item.getItemFromBlock(Blocks.planks),1,0.0f);
					}
					
					for(i=0;i<2;i++){
						this.func_145778_a(Items.stick,1,0.0f);
					}
				}
				
				this.fallDistance = 0.0f;				
			}
		}
		else if(this.worldObj.getBlock(posx,posy-1,posz).getMaterial() != Material.water && d1 < 0.0f){
			this.fallDistance = (float)((double)this.fallDistance -  d1);
//			System.out.println("updateFallState else if = "+d1);
		}
		
	}

	
	
	public void setDamageTaken(float f1){
		this.dataWatcher.updateObject(19,Float.valueOf(f1));
	}

	public float getDamageTaken() {
		return this.dataWatcher.getWatchableObjectFloat(19);
	}
	
	public void setTimeSinceHit(int i1){
		this.dataWatcher.updateObject(17,Integer.valueOf(i1));
	}

	public int getTimeSinceHit() {
		return this.dataWatcher.getWatchableObjectInt(17);
	}
	
	
	public void setForwardDirection(int i1){
		this.dataWatcher.updateObject(18,Integer.valueOf(i1));
	}
	
	public int getForwardDirection() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	public void setIsVFEmpty(boolean set_is_vfempty){
		this.isVFEmpty = set_is_vfempty;
	}
	
	
	
//	@Override
//	public void applyEntityCollision(Entity entity){
//		System.out.println("applyEntityCollision");
//		
//		if(worldObj.isRemote){
//			return;
//		}
//		if(entity == this.riddenByEntity){
//			return;
//		}
//		
//		if ((entity instanceof EntityLiving) && !(entity instanceof EntityPlayer) && riddenByEntity == null && entity.ridingEntity == null) {
//			entity.mountEntity(this);
//			
//			dataWatcher.updateObject(18, Integer.valueOf(riddenByEntity == null ? 0 : riddenByEntity.getEntityId()));
////			setRiddenByEntityID(riddenByEntity);
//		}
//		
//		super.applyEntityCollision(entity);
//	}
	

//	
	@Override
	public void updateRidden(){
		super.updateRidden();
//		System.out.println("updateRidden()");
	}
//	
	
}

