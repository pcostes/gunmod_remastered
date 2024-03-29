package gunmod.entity.render;

import gunmod.entity.models.ModelLaser;
import gunmod.objects.entities.EntityLaser;
import gunmod.util.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLaser extends Render<EntityLaser>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":textures/entities/laser.png");

    private final ModelLaser laserModel = new ModelLaser();

    public RenderLaser(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    private float getRenderYaw(float p_82400_1_, float p_82400_2_, float p_82400_3_)
    {
        float f;

        for (f = p_82400_2_ - p_82400_1_; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return p_82400_1_ + p_82400_3_ * f;
    }

    public void doRender(EntityLaser entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        float yaw = this.getRenderYaw(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
        float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        // TODO Somehow access the laser's shooter from here. Its not working for some reason.
        GlStateManager.translate((float)x, (float)y + 1.62F, (float)z);
        float f2 = 0.0625F;
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.enableAlpha();
        this.bindEntityTexture(entity);
        
        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        //this.laserModel.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.0625F);
        this.laserModel.render(entity, 0.0F, 0.0F, 0.0F, yaw, pitch, f2);
        
        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
        
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntityLaser entity)
    {
        return TEXTURE;
    }
}
