package dev.cruding.engine.service;

import dev.cruding.engine.flow.JavaFlow;

public abstract class Service {

    public abstract void addResourceMethod(JavaFlow flow);

    public abstract void addRepositoryMethod(JavaFlow flow);
}
