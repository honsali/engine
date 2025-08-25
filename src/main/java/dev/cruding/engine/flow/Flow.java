package dev.cruding.engine.flow;

public class Flow {


    private StringBuilder sb = new StringBuilder();

    // core methods
    public Flow append(String s) {
        if (s != null) {
            sb.append(s);
        }
        return this;
    }

    public Flow append(StringBuilder sb_) {
        if (sb_ != null) {
            sb.append(sb_);
        }
        return this;
    }


    public Flow append(String... ss) {
        if (ss != null) {
            for (String s : ss) {
                append(s);
            }
        }
        return this;
    }

    public Flow removeAfterLastComma() {
        int idx = sb.lastIndexOf(",");
        if (idx != -1) {
            sb.delete(idx, sb.length());
        }
        return this;
    }

    public Flow newLine() {
        sb.append("\n");
        return this;
    }

    public Flow spaces(int spaces) {
        if (spaces > 0) {
            sb.append(" ".repeat(spaces));
        }
        return this;
    }

    public Flow newLineWithIndent(int spaces, String s) {
        return newLine().spaces(spaces).append(s);
    }

    public Flow newLineWithIndent(int spaces, String... ss) {
        return newLine().spaces(spaces).append(ss);
    }

    public String toString() {
        return sb.toString();
    }

    public void clean() {
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void cleanComma() {
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // alias methods for appending
    public Flow __(String s) {
        return append(s);
    }

    public Flow __(String... ss) {
        return append(ss);
    }


    public Flow L(String s) {
        return newLine().append(s);
    }

    public Flow L____(String s) {
        return newLineWithIndent(4, s);
    }

    public Flow L________(String s) {
        return newLineWithIndent(8, s);
    }

    public Flow L____________(String s) {
        return newLineWithIndent(12, s);
    }

    public Flow L________________(String s) {
        return newLineWithIndent(16, s);
    }

    public Flow L____________________(String s) {
        return newLineWithIndent(20, s);
    }

    public Flow L________________________(String s) {
        return newLineWithIndent(24, s);
    }

    public Flow L____________________________(String s) {
        return newLineWithIndent(28, s);
    }

    public Flow L________________________________(String s) {
        return newLineWithIndent(32, s);
    }

    public Flow L____________________________________(String s) {
        return newLineWithIndent(36, s);
    }

    public Flow L________________________________________(String s) {
        return newLineWithIndent(40, s);
    }

    public Flow L____________________________________________(String s) {
        return newLineWithIndent(44, s);
    }

    public Flow L________________________________________________(String s) {
        return newLineWithIndent(48, s);
    }

    public Flow L(String... ss) {
        return newLine().append(ss);
    }

    public Flow L____(String... ss) {
        return newLineWithIndent(4, ss);
    }

    public Flow L________(String... ss) {
        return newLineWithIndent(8, ss);
    }

    public Flow L____________(String... ss) {
        return newLineWithIndent(12, ss);
    }

    public Flow L________________(String... ss) {
        return newLineWithIndent(16, ss);
    }


    public Flow L____________________(String... ss) {
        return newLineWithIndent(20, ss);
    }

    public Flow L________________________(String... ss) {
        return newLineWithIndent(24, ss);
    }

    public Flow L____________________________(String... ss) {
        return newLineWithIndent(28, ss);
    }
}
