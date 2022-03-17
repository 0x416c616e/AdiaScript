import java.util.Objects;

public class FontLambdaHelper {
    public enum FontType {
        REGULAR,
        ESOTERIC
    }

    private FontType fontType;

    public FontLambdaHelper() {
        this.fontType = FontType.REGULAR;
    }

    public FontLambdaHelper(FontType fontType) {
        this.fontType = fontType;
    }

    public FontType getFontType() {
        return fontType;
    }

    public void setFontType(FontType fontType) {
        this.fontType = fontType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FontLambdaHelper that = (FontLambdaHelper) o;
        return fontType == that.fontType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fontType);
    }

    @Override
    public String toString() {
        return "FontLambdaHelper{" +
                "fontType=" + fontType +
                '}';
    }
}
