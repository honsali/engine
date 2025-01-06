package dev.cruding.engine.flow;

public class Flow {

    private StringBuilder sb = new StringBuilder();

    public Flow appendTab(int level) {
        sb.append("    ".repeat(level));
        return this;
    }

    public Flow append(String s) {
        sb.append(s);
        return this;
    }


    public Flow append(StringBuilder sb_) {
        sb.append(sb_);
        return this;
    }

    public Flow __(StringBuilder sb_) {
        sb.append(sb_);
        return this;
    }

    public Flow __(String s) {
        sb.append(s);
        return this;
    }

    public Flow __(String s1, int i, String s2) {
        sb.append(s1).append(i).append(s2);
        return this;
    }

    public Flow __(String s, int i) {
        sb.append(s).append(i);
        return this;
    }

    public Flow __(String s, StringBuilder sb_) {
        sb.append(s).append(sb_);
        return this;
    }

    public Flow __(String s1, StringBuilder sb_, String s2) {
        sb.append(s1).append(sb_).append(s2);
        return this;
    }

    public Flow __(String... ss) {
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow removeLastComma() {
        int idx = sb.lastIndexOf(",");
        if (idx != -1) {
            sb.delete(idx, sb.length());
        }
        return this;
    }

    public Flow L____(String s) {
        sb.append("\n").append("    ").append(s);
        return this;
    }

    public Flow L____(String... ss) {
        sb.append("\n").append("    ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L________(String... ss) {
        sb.append("\n").append("        ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L____________(String... ss) {
        sb.append("\n").append("            ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L________________(String... ss) {
        sb.append("\n").append("                ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L____________________(String... ss) {
        sb.append("\n").append("                    ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L________________________(String... ss) {
        sb.append("\n").append("                        ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L____________________________(String... ss) {
        sb.append("\n").append("                            ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L________________________________(String... ss) {
        sb.append("\n").append("                                ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L____________________________________(String... ss) {
        sb.append("\n").append("                                    ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L________________________________________(String... ss) {
        sb.append("\n").append("                                        ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L____________________________________________(String... ss) {
        sb.append("\n").append("                                            ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L________________________________________________(String... ss) {
        sb.append("\n").append("                                                ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }


    public Flow ______(String... ss) {
        sb.append("    ");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public Flow L(String s) {
        sb.append("\n").append(s);
        return this;
    }

    public Flow L(String s, StringBuilder sb_) {
        sb.append("\n").append(s).append(sb_);
        return this;
    }

    public Flow L(String s1, StringBuilder sb_, String s2) {
        sb.append("\n").append(s1).append(sb_).append(s2);
        return this;
    }

    public Flow L(String... ss) {
        sb.append("\n");
        for (String s : ss) {
            sb.append(s);
        }
        return this;
    }

    public String toString() {
        return sb.toString();
    }

    public Flow ap(StringBuilder s) {
        sb.append(s);
        return this;
    }

    public void clean() {
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void cleanComma() {
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
