package mitmit.atmospheric.client;

import mitmit.atmospheric.ParticleGenerator.ParticleGeneratorMenu;
import mitmit.atmospheric.client.Widgets.CustomSelectionList;
import mitmit.atmospheric.client.Widgets.CustomSlider;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class ParticleGeneratorScreen extends AbstractContainerScreen<ParticleGeneratorMenu> {
    private static final Identifier CONTAINER_TEXTURE = Identifier.fromNamespaceAndPath("atmospheric","textures/gui/demo_background.png");

    String textEntered = "default";
    Boolean checked = true;
    double sliderValue = 0.5;

    public ParticleGeneratorScreen(ParticleGeneratorMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        this.titleLabelY = 100000;
        this.inventoryLabelY = 100000;

        CustomSelectionList list = new CustomSelectionList(this.minecraft, 120, 45, 0, 14);
        list.setPosition(20, 100);
        list.addEntry("option 1");
        list.addEntry("option 2");
        list.addEntry("option 3");
        list.addEntry("option 4");
        list.addEntry("option 5");
        list.addEntry("option 6");
        list.addEntry("option 7");
        this.addRenderableWidget(list);

        Button buttonWidget = Button.builder(Component.literal("Selection List"), (btn) -> {
            this.minecraft.getToastManager().addToast(
                    SystemToast.multiline(this.minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.nullToEmpty("Selection:"), Component.nullToEmpty(list.getSelectedEntry()))
            );
        }).bounds(20, 20, 80, 20).build();
        this.addRenderableWidget(buttonWidget);

//        EditBox textField = new EditBox(this.getFont(), 20, 50, 80, 20, Component.empty());
//        textField.setValue(textEntered);
//        textField.setResponder(text -> {
//            this.textEntered = text;
//        });
//        this.addRenderableWidget(textField);
//
//        Checkbox check = Checkbox.builder(Component.literal("Checkbox"), this.getFont()).onValueChange((checkbox, value) -> {
//            checked = value;
//            this.minecraft.getToastManager().addToast(
//                    SystemToast.multiline(this.minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.nullToEmpty("Checkbox State"), Component.nullToEmpty(checked.toString()))
//            );
//        }).pos(20, 80).selected(checked).build();
//        this.addRenderableWidget(check);
//
//        CustomSlider slider = new CustomSlider(20, 110, 120, 20, Component.literal("Slider"), sliderValue, 10, 15, false);
//        this.addRenderableWidget(slider);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(graphics, mouseX, mouseY, delta);
        graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, 0, 0, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);

        graphics.text(this.font, "Special Button", 20, 20 - this.font.lineHeight, 0xFFFFFFFF, true);
    }
}