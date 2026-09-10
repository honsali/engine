package dev.cruding.engine.component;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import dev.cruding.engine.component.container.Block;
import dev.cruding.engine.component.container.Container;
import dev.cruding.engine.component.container.ExtendedPanel;
import dev.cruding.engine.component.container.FilterPanel;
import dev.cruding.engine.component.container.InColumn;
import dev.cruding.engine.component.container.InlineBlock;
import dev.cruding.engine.component.container.Panel;
import dev.cruding.engine.component.container.PrimaryPanel;
import dev.cruding.engine.component.container.SecondaryPanel;
import dev.cruding.engine.component.container.Section;
import dev.cruding.engine.component.container.SimplePanel;
import dev.cruding.engine.component.container.Span;
import dev.cruding.engine.component.container.Tab;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.BaseComposer;

class ContainerContentTest {

    private final BaseComposer composer = new BaseComposer("ViewTest", "/") {};
    private final Element element = composer.element;

    @Test
    void keepsConcreteContainerTypesThroughoutFluentCalls() {
        Span child = new Span(element, "A");
        Block block = composer.block().name("bloc").title("bloc").width("600px")
                .margin("20px").background("blanc").content(child);
        InlineBlock inline = composer.inlineBlock().name("inline").content(child);
        PrimaryPanel primary = composer.primaryPanel().title("principal").width("500px").content(child);
        SecondaryPanel secondary = composer.secondaryPanel().title("secondaire").content(child);
        SimplePanel simple = composer.simplePanel().title("simple").content(child);
        ExtendedPanel extended = composer.extendedPanel().title("employe").content(child).open();
        FilterPanel filter = composer.filterPanel((Entity) null).title("filtre").content(child);
        Panel panel = composer.panel().title("panneau").content(child).actionBlock(child).statePanel();
        Section section = composer.section().margin("0").content(child).actionBlock(child).statePanel();
        Tab tab = new Tab(element).name("onglet").title("onglet").content(child);

        for (Container<?> container : new Container<?>[] {
                block, inline, primary, secondary, simple, extended, filter, panel, section, tab}) {
            assertArrayEquals(new Component[] {child}, container.componentList);
            assertSame(container, container.content(child));
        }
        assertTrue(extended.open);
        assertTrue(panel.statePanel);
        assertTrue(section.statePanel);
        assertSame(child, section.actionBlock);
        assertTrue(composer.extendedPanel().open().title("employe").content(child).open);
    }

    @Test
    void rendersContentSyntaxLikeConstructorChildren() {
        Component before = new Block(element,
                new Section(element, new Span(element, "A"))
                        .actionBlock(new Span(element, "Action")).margin("0"),
                new Span(element, "B"))
                .width("600px").margin("20px").background("blanc");
        Component after = composer.block()
                .width("600px").margin("20px").background("blanc")
                .content(
                        composer.section().margin("0")
                                .content(new Span(element, "A"))
                                .actionBlock(new Span(element, "Action")),
                        new Span(element, "B"));

        assertEquals(render(before), render(after));
    }

    @Test
    void replacesChildrenAndFiltersNullsWithoutChangingOptionsOrInput() {
        Span first = new Span(element, "A");
        Span second = new Span(element, "B");
        Component[] children = {null, first, null, second, null};
        Block block = composer.block(new Span(element, "Ancien"))
                .name("bloc").width("600px").content(children);

        assertArrayEquals(new Component[] {first, second}, block.componentList);
        assertArrayEquals(new Component[] {null, first, null, second, null}, children);
        children[1] = null;
        assertArrayEquals(new Component[] {first, second}, block.componentList);
        assertEquals("bloc", block.name);
        assertEquals("600px", block.width);

        block.content(second);
        assertArrayEquals(new Component[] {second}, block.componentList);
        assertEquals(0, block.content().componentList.length);
        assertEquals(0, block.content((Component[]) null).componentList.length);
        assertEquals(0, block.content((Component) null).componentList.length);
        assertArrayEquals(new Component[] {first},
                composer.section((Entity) null).content(null, first).componentList);
    }

    @Test
    void keepsColumnWidthsWhenColumnsComeLast() {
        Span first = new Span(element, "A");
        Span second = new Span(element, "B");
        InColumn defaults = composer.inColumn().name("colonnes").column(first).column(second);
        InColumn spans = composer.inColumn().spans(16, 8).column(first).column(second);
        InColumn flex = composer.inColumn().flex("400px", "auto").column(first).column(second);

        assertEquals(render(new InColumn(element, first, second)), render(defaults));
        assertEquals(render(new InColumn(element, first, second).spans(16, 8)), render(spans));
        assertEquals(render(new InColumn(element, first, second).flex("400px", "auto")), render(flex));
        assertEquals("colonnes", defaults.name);
    }

    @Test
    void appendsOneColumnWithoutReplacingExistingChildren() {
        Span first = new Span(element, "A");
        Component grouped = composer.block(new Span(element, "B"), new Span(element, "C"));
        InColumn columns = composer.inColumn(new Span[] {first}).spans(16, 8);
        Component[] previousChildren = columns.componentList;

        assertSame(columns, columns.column(grouped));
        assertArrayEquals(new Component[] {first, grouped}, columns.componentList);
        assertArrayEquals(new Component[] {first}, previousChildren);
        assertEquals(render(new InColumn(element, first, grouped).spans(16, 8)), render(columns));
    }

    @Test
    void rejectsNullColumnsWithoutDiscardingExistingChildren() {
        Span child = new Span(element, "A");
        InColumn columns = composer.inColumn().columnNumber(2).column(child);
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> columns.column(null));
        assertTrue(error.getMessage().contains("column positions"));
        assertArrayEquals(new Component[] {child}, columns.componentList);
    }

    private String render(Component component) {
        ViewFlow flow = new ViewFlow(element);
        component.addContent(null, flow, 1);
        return flow.totalUi().toString();
    }
}
