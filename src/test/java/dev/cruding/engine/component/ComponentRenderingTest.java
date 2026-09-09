package dev.cruding.engine.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import dev.cruding.engine.component.container.Block;
import dev.cruding.engine.component.container.Condition;
import dev.cruding.engine.component.container.InColumn;
import dev.cruding.engine.component.container.Span;
import dev.cruding.engine.element.Element;
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

        assertEquals(expected.formatted("span={12}", "span={12}"), render(columns().width(2)));
        assertEquals(expected.formatted("span={16}", "span={8}"), render(columns().width(16, 8)));
        assertEquals(expected.formatted("flex=\"400px\"", "flex=\"auto\""),
                render(columns().width("400px", "auto")));
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
        assertRenderMetadata(child -> new InColumn(element, child).width(2), false);
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
