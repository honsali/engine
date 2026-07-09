package dev.cruding.engine.flow;

import java.util.regex.Pattern;

public final class TsLiteral {

    private static final Pattern IDENTIFIER = Pattern.compile("[A-Za-z_$][A-Za-z0-9_$]*");

    private TsLiteral() {}

    public static String string(String value) {
        if (value == null) {
            return "null";
        }

        StringBuilder out = new StringBuilder("'");
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '\'' -> out.append("\\'");
                case '\\' -> out.append("\\\\");
                case '\b' -> out.append("\\b");
                case '\f' -> out.append("\\f");
                case '\n' -> out.append("\\n");
                case '\r' -> out.append("\\r");
                case '\t' -> out.append("\\t");
                default -> {
                    if (c < 0x20 || c == '\u2028' || c == '\u2029') {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
                }
            }
        }
        return out.append('\'').toString();
    }

    public static String objectKey(String key) {
        if (key != null && IDENTIFIER.matcher(key).matches()) {
            return key;
        }
        return string(key);
    }
}
