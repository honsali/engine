package dev.cruding.engine.gen;

public abstract class ElementComposer extends BaseComposer {


    public ElementComposer() {
        this.element.relativePath = "/element";
        if (page != null) {
            this.element.page(page);
        }
    }

}
