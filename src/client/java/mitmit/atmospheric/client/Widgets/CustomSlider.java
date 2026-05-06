package mitmit.atmospheric.client.Widgets;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;

import javax.lang.model.type.DeclaredType;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CustomSlider extends AbstractSliderButton {
    private final double minValue, maxValue;
    private final boolean allowDecimals;
    private final String title;

    public CustomSlider(int x, int y, int width, int height, Component message, double initialValue, double minValue, double maxValue, boolean allowDecimals) {
        super(x, y, width, height, message, initialValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.allowDecimals = allowDecimals;
        this.title = message.getString();
        updateMessage();
    }

    @Override
    protected void updateMessage() {
        NumberFormat numberFormat = allowDecimals ? new DecimalFormat("#0.00") : new DecimalFormat("#0");
        setMessage(Component.literal(title + ": " + numberFormat.format(getMappedValue())));
    }

    @Override
    protected void applyValue() {
        //do work
    }

    public double getMappedValue() {
        double actual = minValue + (maxValue - minValue) * value;
        if(!allowDecimals) { actual = (int)actual; }
        return actual;
    }
}
