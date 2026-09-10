package dev.cruding.engine.component;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import dev.cruding.engine.component.container.Block;
import dev.cruding.engine.component.container.Condition;
import dev.cruding.engine.component.container.InColumn;
import dev.cruding.engine.component.container.Section;
import dev.cruding.engine.component.container.Span;
import dev.cruding.engine.component.container.Tab;
import dev.cruding.engine.component.container.TabMenu;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;

class ComponentRenderingTest {

    private final Element element = new Element("ViewTest", "/");

    @Test
    void runsTheCommonLifecycleBeforeACustomBody() {
        List<String> calls = new ArrayList<>();
        Block parent = new Block(element);
        parent.inElement = true;
        Component child = new Component(element) {
            @Override
            public void addImport(ViewFlow flow) {
                assertSame(parent, fatherComponent);
                assertTrue(inElement);
                assertFalse(inline);
                calls.add("import");
            }

            @Override
            public void addScript(ViewFlow flow) {
                calls.add("script");
            }

            @Override
            protected void addBody(ViewFlow flow, int level) {
                assertEquals(2, level);
                calls.add("body");
                indent(flow, level).append("<Custom />");
            }
        };
        parent.componentList = new Component[] {child};

        assertEquals("""
                (
                        <Bloc>
                            <Custom />
                        </Bloc>
                    );""", render(parent));
        assertEquals(List.of("import", "script", "body"), calls);
    }

    @Test
    void rendersTheDefaultBodyInChildOrder() {
        assertEquals("""
                (
                        <Bloc>
                            <span>A</span>
                            <span>B</span>
                        </Bloc>
                    );""", render(new Block(element, new Span(element, "A"), new Span(element, "B"))));
    }

    @Test
    void ignoresNullChildrenWithoutChangingTheirOrder() {
        Span first = new Span(element, "A");
        Span second = new Span(element, "B");
        Component[] children = {null, first, null, second, null};
        Block block = new Block(element, children);
        Section section = new Section(element, (Entity) null, children);

        assertArrayEquals(new Component[] {first, second}, block.componentList);
        assertArrayEquals(new Component[] {first, second}, section.componentList);
        assertArrayEquals(new Component[] {null, first, null, second, null}, children);
        assertEquals(render(new Block(element, first, second)), render(block));
        assertEquals(render(new Section(element, (Entity) null, first, second)), render(section));
    }

    @Test
    void rendersOrdinaryContainersWithOnlyNullChildrenAsEmpty() {
        String emptyBlock = render(new Block(element));
        assertEquals(emptyBlock, render(new Block(element, (Component) null)));
        assertEquals(emptyBlock, render(new Block(element, null, null)));
        assertEquals(emptyBlock, render(new Block(element, (Component[]) null)));

        String emptySection = render(new Section(element, (Entity) null, new Component[0]));
        assertEquals(emptySection, render(new Section(element, (Entity) null, (Component[]) null)));
    }

    @Test
    void ignoresNullChildrenBeforeWrappingTabs() {
        Component first = new Span(element, "A").name("premier");
        Component second = new Span(element, "B").name("second");
        TabMenu menu = new TabMenu(element, null, first, null, second, null);

        assertEquals(2, menu.componentList.length);
        assertEquals("premier", ((Tab) menu.componentList[0]).title);
        assertEquals("second", ((Tab) menu.componentList[1]).title);
        assertArrayEquals(new Component[] {first}, menu.componentList[0].componentList);
        assertArrayEquals(new Component[] {second}, menu.componentList[1].componentList);
        assertEquals(0, new TabMenu(element, (Component) null).componentList.length);
        assertEquals(0, new TabMenu(element, (Component[]) null).componentList.length);
    }

    @Test
    void rejectsNullConditionalBranchesAtConstruction() {
        Span child = new Span(element, "A");
        Component[][] invalidChildren = {{null}, {null, child}, {child, null}, {null, null}, null};
        for (Component[] children : invalidChildren) {
            IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                    () -> new Condition(element, "visible", "siVraiFaux", true, children));
            assertTrue(error.getMessage().contains("Condition"));
            assertTrue(error.getMessage().contains("branch positions"));

            IllegalArgumentException namedError = assertThrows(IllegalArgumentException.class,
                    () -> new Condition("visible", element, "visible", "siVraiFaux", true, children));
            assertEquals(error.getMessage(), namedError.getMessage());
        }
        assertThrows(IllegalArgumentException.class,
                () -> new Condition(element, "visible", "siVrai", false, (Component) null));
    }

    @Test
    void rejectsNullColumnChildrenAtConstruction() {
        Span first = new Span(element, "A");
        Span second = new Span(element, "B");
        Component[][] invalidChildren = {{null}, {null, first}, {first, null}, {first, null, second}, null};
        for (Component[] children : invalidChildren) {
            IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                    () -> new InColumn(element, children));
            assertTrue(error.getMessage().contains("InColumn"));
            assertTrue(error.getMessage().contains("column positions"));
        }
    }

    @Test
    void preservesColumnWidthsAndTheAdditionalIndentationLevel() {
        String expected = """
                (
                        <Row gutter={20}>
                            <Col %s>
                                <span>A</span>
                            </Col>
                            <Col %s>
                                <span>B</span>
                            </Col>
                        </Row>
                    );""";

        assertEquals(expected.formatted("span={12}", "span={12}"), render(columns()));
        assertEquals(expected.formatted("span={12}", "span={12}"), render(columns().columnNumber(2)));
        assertEquals(expected.formatted("span={16}", "span={8}"), render(columns().spans(16, 8)));
        assertEquals(expected.formatted("flex=\"400px\"", "flex=\"auto\""),
                render(columns().flex("400px", "auto")));
    }

    @Test
    void distinguishesSingleSpanFromColumnCount() {
        InColumn column = new InColumn(element, new Span(element, "A"));
        String expected = """
                (
                        <Row gutter={20}>
                            <Col span={%d}>
                                <span>A</span>
                            </Col>
                        </Row>
                    );""";

        assertEquals(expected.formatted(16), render(column.spans(16)));
        assertEquals(expected.formatted(2), render(column.spans(2)));
        assertEquals(expected.formatted(12), render(column.columnNumber(2)));
        assertEquals(expected.formatted(24), render(column.columnNumber(1)));
    }

    @Test
    void usesTheLastColumnLayoutConfiguration() {
        InColumn layout = columns().flex("400px", "auto");

        assertEquals(render(columns().spans(16, 8)), render(layout.spans(16, 8)));
        assertEquals(render(columns().columnNumber(2)), render(layout.columnNumber(2)));
        assertEquals(render(columns().flex("400px", "auto")), render(layout.flex("400px", "auto")));
    }

    @Test
    void preservesInlineConditionalBranches() {
        Condition oneBranch = new Condition(element, "visible", "siVrai", true, new Span(element, "A"));
        Condition twoBranches = new Condition(element, "visible", "siVraiFaux", true,
                new Span(element, "A"), new Span(element, "B"));
        String expected = """
                (
                        <Bloc>
                            %s
                        </Bloc>
                    );""";

        assertEquals(expected.formatted("{visible && <span>A</span>}"), render(new Block(element, oneBranch)));
        assertEquals(expected.formatted("{visible ? <span>A</span> : <span>B</span>}"),
                render(new Block(element, twoBranches)));
    }

    @Test
    void requiresOneBranchForSimpleConditions() {
        Span first = new Span(element, "A");
        Span second = new Span(element, "B");
        Span third = new Span(element, "C");
        Component[][] invalidBranches = {{}, {first, second}, {first, second, third}};
        for (String type : new String[] {"siVrai", "siFaux", "nonVide", "estVide"}) {
            Condition valid = new Condition(element, "visible", type, true, first);
            assertTrue(render(new Block(element, valid)).contains("<span>A</span>"));
            for (Component[] branches : invalidBranches) {
                IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                        () -> new Condition(element, "visible", type, true, branches));
                assertTrue(error.getMessage().contains("Condition '" + type + "'"));
                assertTrue(error.getMessage().contains("exactly 1"));
                assertTrue(error.getMessage().contains("got " + branches.length));
            }
        }
    }

    @Test
    void requiresTwoBranchesForTernaryConditions() {
        Span first = new Span(element, "A");
        Span second = new Span(element, "B");
        Span third = new Span(element, "C");
        Component[][] invalidBranches = {{}, {first}, {first, second, third}};
        for (Component[] branches : invalidBranches) {
            IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                    () -> new Condition("visible", element, "visible", "siVraiFaux", false, branches));
            assertTrue(error.getMessage().contains("Condition 'siVraiFaux'"));
            assertTrue(error.getMessage().contains("exactly 2"));
            assertTrue(error.getMessage().contains("got " + branches.length));
        }
    }

    @Test
    void preservesMultilineConditionalDelimiters() {
        Condition condition = new Condition(element, "visible", "siVraiFaux", false,
                new Span(element, "A"), new Span(element, "B"));
        String expected = String.join("\n",
                "(",
                "        <Bloc>",
                "            {visible ? (",
                "                <span>A</span>",
                "            ",
                ")",
                ":",
                "(",
                "                <span>B</span>",
                "            )}",
                "        </Bloc>",
                "    );");

        assertEquals(expected, render(new Block(element, condition)));
    }

    @Test
    void propagatesRenderMetadataThroughDefaultAndSpecializedBodies() {
        assertRenderMetadata(child -> new Block(element, child), false);
        assertRenderMetadata(child -> new InColumn(element, child).columnNumber(2), false);
        assertRenderMetadata(child -> new Condition(element, "visible", "siVrai", true, child), true);
    }

    private void assertRenderMetadata(Function<Component, Component> createRoot, boolean childInline) {
        Span child = new Span(element, "A");
        Component root = createRoot.apply(child);
        root.inElement = true;
        ViewFlow flow = new ViewFlow(element);

        root.addContent(null, flow, true, 1);

        assertNull(root.fatherComponent);
        assertTrue(root.inline);
        assertSame(root, child.fatherComponent);
        assertTrue(child.inElement);
        assertEquals(childInline, child.inline);
        assertTrue(flow.totalUi().toString().startsWith("("));
        assertTrue(flow.totalUi().toString().endsWith(");"));
    }

    private InColumn columns() {
        return new InColumn(element, new Span(element, "A"), new Span(element, "B"));
    }

    private String render(Component component) {
        ViewFlow flow = new ViewFlow(element);
        component.addContent(null, flow, 1);
        return flow.totalUi().toString();
    }
}
