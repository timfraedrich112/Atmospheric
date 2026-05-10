package mitmit.atmospheric.client.Widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

public class CustomSelectionList extends ObjectSelectionList<CustomSelectionList.Entry> {
    public CustomSelectionList(Minecraft minecraft, int width, int height, int y, int defaultEntryHeight) {
        super(minecraft, width, height, y, defaultEntryHeight);
        setScrollAmount(10);
    }

    public void addEntry(String name) {
        this.addEntry(new Entry(this, name));
    }

    @Nullable
    public String getSelectedEntry() {
        Entry selected = this.getSelected();
        return selected != null ? selected.name : null;
    }

    public static class Entry extends ObjectSelectionList.Entry<Entry> {
        private static final Identifier ENTRY_TEXTURE = Identifier.fromNamespaceAndPath("", "");
        private final CustomSelectionList list;
        public final String name;

        public Entry(CustomSelectionList list, String label) {
            this.list = list;
            this.name = label;
        }

        @Override
        public boolean mouseClicked(final MouseButtonEvent event, final boolean doubleClick) {
            this.list.setSelected(this);
            return true;
        }

        @Override
        public Component getNarration() {
            return Component.literal(this.name);
        }

        @Override
        public void extractContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float a) {
            graphics.blit(RenderPipelines.GUI_TEXTURED,
                    ENTRY_TEXTURE,
                    this.getContentX() + 4,
                    this.getContentY() + 2,
                    this.getContentWidth() - 8,
                    this.getContentHeight() - 4,
                    12,
                    12,
                    12,
                    12,
                    0xFFFFFFFF);

            int p = (list.width - this.name.length()) / 2;
            int q = this.getContentY() + (this.getContentHeight() - Minecraft.getInstance().font.lineHeight) / 2 + 1;
            graphics.text(Minecraft.getInstance().font, this.name, p, q, 0xFFFFFFFF, true);
        }
    }
}
