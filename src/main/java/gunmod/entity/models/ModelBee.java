package gunmod.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class ModelBee extends ModelBase {
	private final ModelRenderer bb_main;
	private final ModelRenderer bb_main_r1;

	public ModelBee() {
		textureWidth = 16;
		textureHeight = 16;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		bb_main_r1 = new ModelRenderer(this);
		bb_main_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(bb_main_r1);
		setRotationAngle(bb_main_r1, 0.0F, 3.1416F, 0.0F);
		bb_main_r1.cubeList.add(new ModelBox(bb_main_r1, 0, 0, -0.5F, -1.0F, -1.0F, 1, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}