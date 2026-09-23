package dev.cruding.engine.flow.helper;

import java.util.Set;
import java.util.TreeSet;
import dev.cruding.engine.action.Action;

/** Contributions consumed by a component from one action hook. */
public class HookBinding {
    public final Action action;
    public final Set<String> members = new TreeSet<>();
    public final Set<String> parameters = new TreeSet<>();
    public boolean initialize;

    public HookBinding(Action action) {
        this.action = action;
    }

    public String name() {
        return "use" + action.unameWithEntity;
    }

    public void merge(HookBinding other) {
        members.addAll(other.members);
        parameters.addAll(other.parameters);
        initialize |= other.initialize;
    }
}
