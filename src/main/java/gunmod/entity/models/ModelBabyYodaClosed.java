package gunmod.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class ModelBabyYodaClosed extends ModelBase {
	private final ModelRenderer carrier;
	private final ModelRenderer carrier_r1;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;

	public ModelBabyYodaClosed() {
		textureWidth = 64;
		textureHeight = 64;

		carrier = new ModelRenderer(this);
		carrier.setRotationPoint(0.0F, 24.0F, 0.0F);
		carrier.cubeList.add(new ModelBox(carrier, 58, 8, 3.5F, -7.0F, 0.0F, 1, 2, 2, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 0, 13, -3.0F, -2.0F, -5.0F, 6, 1, 10, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 0, 0, -4.0F, -3.0F, -6.0F, 8, 1, 12, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 0, 24, -3.0F, -6.0F, 5.0F, 6, 3, 1, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 13, 27, -3.0F, -8.0F, 4.0F, 6, 2, 1, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 22, 21, -3.0F, -9.0F, 3.0F, 6, 1, 1, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 27, 26, -3.0F, -4.0F, -6.0F, 6, 1, 1, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 14, 24, -3.0F, -6.0F, -6.5F, 6, 2, 1, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 0, 10, -1.5F, -5.5F, -6.75F, 3, 1, 1, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 0, 56, -3.25F, -9.0F, -4.0F, 1, 1, 7, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 0, 13, 2.25F, -9.0F, -4.0F, 1, 1, 7, 0.0F, false));
		carrier.cubeList.add(new ModelBox(carrier, 58, 0, -4.5F, -7.0F, 0.0F, 1, 2, 2, 0.0F, false));

		carrier_r1 = new ModelRenderer(this);
		carrier_r1.setRotationPoint(0.0F, -7.75F, -6.0F);
		carrier.addChild(carrier_r1);
		setRotationAngle(carrier_r1, 0.0F, 3.1416F, 0.0F);
		carrier_r1.cubeList.add(new ModelBox(carrier_r1, 13, 30, -3.0F, -0.25F, -1.0F, 6, 2, 1, 0.0F, false));
		carrier_r1.cubeList.add(new ModelBox(carrier_r1, 37, 21, -3.0F, -1.25F, -2.0F, 6, 1, 1, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		carrier.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 22, 13, -5.0F, -6.0F, -4.0F, 11, 3, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 22, 17, -5.0F, -6.0F, 3.0F, 11, 3, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, -8.5F, 3.5F);
		carrier.addChild(cube_r2);
		setRotationAngle(cube_r2, 1.5708F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 33, -2.5F, -7.5F, 0.5F, 5, 7, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, -7.5F, 0.5F);
		carrier.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -1.5708F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 4, -5.5F, -0.5F, -3.5F, 9, 2, 1, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 33, 0, -5.5F, -0.5F, 2.5F, 9, 2, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		carrier.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}