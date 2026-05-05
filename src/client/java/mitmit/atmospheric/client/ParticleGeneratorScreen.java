package mitmit.atmospheric.client;

import mitmit.atmospheric.ParticleGenerator.ParticleGeneratorMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class ParticleGeneratorScreen extends AbstractContainerScreen<ParticleGeneratorMenu> {
    private static final Identifier CONTAINER_TEXTURE = Identifier.fromNamespaceAndPath("atmospheric","textures/gui/demo_background.png");
    String textEntered = "default";

    public ParticleGeneratorScreen(ParticleGeneratorMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        this.titleLabelY = 100000;
        this.inventoryLabelY = 100000;

        Button buttonWidget = Button.builder(Component.literal("Hello World"), (btn) -> {
            this.minecraft.getToastManager().addToast(
                    SystemToast.multiline(this.minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.nullToEmpty("Relay Text"), Component.nullToEmpty(textEntered))
            );
        }).bounds(20, 20, 80, 20).build();
        // x, y, width, height
        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.

        EditBox textField = new EditBox(this.getFont(), 20, 40, 80, 20, Component.empty());
        textField.setValue(textEntered);
        textField.setResponder(text -> {
            this.textEntered = text;
        });

        // Register the button widget.
        this.addRenderableWidget(buttonWidget);
        this.addRenderableWidget(textField);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(graphics, mouseX, mouseY, delta);
        graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, 0, 0, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);

        graphics.text(this.font, "Special Button", 20, 20 - this.font.lineHeight, 0xFFFFFFFF, true);
    }
}