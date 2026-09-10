package dev.cruding.engine.component;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        Block block = composer.block().name("bloc").width("600px")
                .margin("20px").background("blanc").content(child);
        InlineBlock inline = composer.inlineBlock().name("inline").content(child);
        PrimaryPanel primary = composer.primaryPanel().title("principal").width("500px").content(child);
        SecondaryPanel secondary = composer.secondaryPanel().title("secondaire").content(child);
        SimplePanel simple = composer.simplePanel().title("simple").content(child);
        ExtendedPanel extended = composer.extendedPanel().title("employe").content(child).open();
        FilterPanel filter = composer.filterPanel((Entity) null).title("filtre").content(child);
        Panel panel = composer.panel().title("panneau").content(child).actionBlock(child).statePanel();
        Section section = composer.section().title("section").margin("0").content(child).actionBlock(child);
        Tab tab = composer.tab("onglet").content(child);

        for (Container<?> container : new Container<?>[] {
                block, inline, primary, secondary, simple, extended, filter, panel, section, tab}) {
            assertArrayEquals(new Component[] {child}, container.componentList);
            assertSame(container, container.content(child));
        }
        assertTrue(extended.open);
        assertTrue(panel.statePanel);
        assertFalse(section.statePanel);
        assertTrue(composer.section().statePanel().title("section").content(child).statePanel);
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
    void supportsDefaultSpanAndFlexColumns() {
        Span first = new Span(element, "A");
        Span second = new Span(element, "B");
        InColumn defaults = composer.inColumn().name("colonnes").column(first).column(second);
        InColumn spans = composer.inColumn().column(16, first).column(8, second);
        InColumn flex = composer.inColumn().column("400px", first).column("auto", second);

        assertEquals(render(new InColumn(element, first, second)), render(defaults));
        assertTrue(render(spans).contains("<Col span={16}>"));
        assertTrue(render(spans).contains("<Col span={8}>"));
        assertTrue(render(flex).contains("<Col flex=\"400px\">"));
        assertTrue(render(flex).contains("<Col flex=\"auto\">"));
        assertEquals("colonnes", defaults.name);
    }

    @Test
    void appendsOneColumnWithoutReplacingExistingChildren() {
        Span first = new Span(element, "A");
        Component grouped = composer.block(new Span(element, "B"), new Span(element, "C"));
        InColumn columns = composer.inColumn(new Span[] {first});
        Component[] previousChildren = columns.componentList;

        assertSame(columns, columns.column(grouped));
        assertEquals(2, columns.componentList.length);
        assertSame(previousChildren[0], columns.componentList[0]);
        assertEquals(1, previousChildren.length);
        assertArrayEquals(new Component[] {first}, columns.componentList[0].componentList);
        assertArrayEquals(new Component[] {grouped}, columns.componentList[1].componentList);
        assertEquals(render(new InColumn(element, first, grouped)), render(columns));
    }

    @Test
    void rejectsNullColumnsWithoutDiscardingExistingChildren() {
        Span child = new Span(element, "A");
        InColumn columns = composer.inColumn().columnNumber(2).column(child);
        Component[] previousChildren = columns.componentList;
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> columns.column(null));
        assertTrue(error.getMessage().contains("column positions"));
        assertThrows(IllegalArgumentException.class, () -> columns.column(16, null));
        assertThrows(IllegalArgumentException.class, () -> columns.column("auto", null));
        assertSame(previousChildren, columns.componentList);
        assertArrayEquals(new Component[] {child}, columns.componentList[0].componentList);
    }

    @Test
    void exposesPresentationOptionsOnlyOnComponentsThatRenderThem() {
        for (Class<?> type : new Class<?>[] {Container.class, InlineBlock.class, Tab.class,
                ExtendedPanel.class, FilterPanel.class, Panel.class, Section.class}) {
            assertThrows(NoSuchMethodException.class, () -> type.getMethod("width", String.class));
        }
        for (Class<?> type : new Class<?>[] {Container.class, Block.class, InlineBlock.class}) {
            assertThrows(NoSuchMethodException.class, () -> type.getMethod("title", String.class));
        }
        for (String attribute : new String[] {"title", "width", "margin", "background"}) {
            assertThrows(NoSuchFieldException.class, () -> Container.class.getField(attribute));
        }
        for (Class<?> type : new Class<?>[] {PrimaryPanel.class, SecondaryPanel.class, SimplePanel.class,
                Section.class, Tab.class, InlineBlock.class, Panel.class, ExtendedPanel.class, FilterPanel.class}) {
            assertThrows(NoSuchMethodException.class, () -> type.getMethod("background", String.class));
        }
        assertTrue(render(composer.block().width("600px").margin("20px").background("blanc"))
                .contains("<Bloc largeur=\"600px\" marge=\"20px\" fond=\"blanc\">"));
    }

    @Test
    void rejectsExclusiveSectionSlotsInEitherOrderWithoutChangingTheSection() {
        Component actions = composer.span("Action");
        Section withState = composer.section(new Entity()).statePanel();
        Section withActions = composer.section().actionBlock(actions);

        IllegalArgumentException stateFirst = assertThrows(IllegalArgumentException.class,
                () -> withState.actionBlock(actions));
        IllegalArgumentException actionsFirst = assertThrows(IllegalArgumentException.class,
                withActions::statePanel);

        assertEquals(stateFirst.getMessage(), actionsFirst.getMessage());
        assertTrue(stateFirst.getMessage().contains("mutually exclusive"));
        assertTrue(withState.statePanel);
        assertNull(withState.actionBlock);
        assertFalse(withActions.statePanel);
        assertSame(actions, withActions.actionBlock);
        assertEquals(1, render(withState).lines().filter(line -> line.contains("blocAction=")).count());
        assertEquals(1, render(withActions).lines().filter(line -> line.contains("blocAction=")).count());
    }

    private String render(Component component) {
        ViewFlow flow = new ViewFlow(element);
        component.addContent(null, flow, 1);
        return flow.totalUi().toString();
    }
}
