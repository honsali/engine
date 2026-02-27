package dev.cruding.engine.gen;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.action.create.CreateAction;
import dev.cruding.engine.action.delete.DeleteAction;
import dev.cruding.engine.action.filter.FilterAction;
import dev.cruding.engine.action.find.FindAction;
import dev.cruding.engine.action.get.GetByFieldAction;
import dev.cruding.engine.action.get.GetFromModelAction;
import dev.cruding.engine.action.inViewOnly.ApplyFilterAction;
import dev.cruding.engine.action.inViewOnly.EmitEventAction;
import dev.cruding.engine.action.inViewOnly.EmptyAction;
import dev.cruding.engine.action.inViewOnly.GetFromSessionAction;
import dev.cruding.engine.action.inViewOnly.GoToModuleAction;
import dev.cruding.engine.action.inViewOnly.GoToPageAction;
import dev.cruding.engine.action.inViewOnly.InitFilterAction;
import dev.cruding.engine.action.inViewOnly.InitModelAction;
import dev.cruding.engine.action.inViewOnly.SelectFromModelAction;
import dev.cruding.engine.action.init.InitCreateAction;
import dev.cruding.engine.action.init.InitUpdateAction;
import dev.cruding.engine.action.list.ListAction;
import dev.cruding.engine.action.listPaginated.ListPaginatedAction;
import dev.cruding.engine.action.specifique.BasicAction;
import dev.cruding.engine.action.update.UpdateAction;
import dev.cruding.engine.action.update.UpdateByDialogueAction;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.button.Button;
import dev.cruding.engine.component.button.ButtonElementComposer;
import dev.cruding.engine.component.container.ActionBlock;
import dev.cruding.engine.component.container.Block;
import dev.cruding.engine.component.container.Breadcrumb;
import dev.cruding.engine.component.container.Condition;
import dev.cruding.engine.component.container.Container;
import dev.cruding.engine.component.container.Div;
import dev.cruding.engine.component.container.ExtendedPanel;
import dev.cruding.engine.component.container.FilterPanel;
import dev.cruding.engine.component.container.InColumn;
import dev.cruding.engine.component.container.InlineBlock;
import dev.cruding.engine.component.container.Panel;
import dev.cruding.engine.component.container.PrimaryPanel;
import dev.cruding.engine.component.container.SecondaryPanel;
import dev.cruding.engine.component.container.Section;
import dev.cruding.engine.component.container.Separator;
import dev.cruding.engine.component.container.SimplePanel;
import dev.cruding.engine.component.container.Space;
import dev.cruding.engine.component.container.Span;
import dev.cruding.engine.component.container.StatePanel;
import dev.cruding.engine.component.container.TabMenu;
import dev.cruding.engine.component.entity.ContextButtonProvider;
import dev.cruding.engine.component.entity.Detail;
import dev.cruding.engine.component.entity.DialogAction;
import dev.cruding.engine.component.entity.Form;
import dev.cruding.engine.component.entity.Table;
import dev.cruding.engine.element.ComponentWrappingElement;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.ActionColumn;
import dev.cruding.engine.field.impl.Code;
import dev.cruding.engine.field.impl.Custom;
import dev.cruding.engine.field.impl.DateRangeBegin;
import dev.cruding.engine.field.impl.DateRangeEnd;
import dev.cruding.engine.field.impl.Hidden;
import dev.cruding.engine.field.impl.Id;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.RefList;
import dev.cruding.engine.field.impl.Render;
import dev.cruding.engine.field.impl.StaticList;
import dev.cruding.engine.field.impl.Tag;

public abstract class ElementComposer {

    public Element element;
    public Page page;

    public ElementComposer(String name, String path) {
        this.element = new Element(name, path);
    }

    public void setPage(Page page) {
        this.page = page;
        element.page(page);
    }

    public Component rootComponent() {
        return null;
    }

    public Element addElement() {
        element.setRootComponent(rootComponent());
        page.addElement(element);
        return element;
    }

    public ComponentWrappingElement element(ElementComposer childElementComposer) {
        childElementComposer.setPage(page);
        Element childElement = childElementComposer.addElement();
        return new ComponentWrappingElement(element, childElement);
    }

    public ComponentWrappingElement element(Action action) {
        return element(new ButtonElementComposer(action));
    }

    public Button button(Action action) {
        return new Button(action.element(element));
    }

    public Entity getEntity(String uname) {
        return Context.getInstance().getEntity(uname);
    }

    public Action primaryAction(Entity entity, String ltype) {
        return new BasicAction(ActionType.PRIMARY, ltype, entity, element);
    }

    public Action normalAction(Entity entity, String ltype) {
        return new BasicAction(ActionType.NORMAL, ltype, entity, element);
    }

    public Action normalActionInViewOnly(Entity entity, String ltype) {
        return new EmptyAction(ActionType.NORMAL, ltype, entity, element).isEmpty(false);
    }


    public Action localAction(Entity entity, String ltype) {
        return new EmptyAction(ActionType.NOUI, ltype, entity, element).isEmpty(true);
    }

    public Action confirmAction(Entity entity, String ltype) {
        return new BasicAction(ActionType.CONFIRM, ltype, entity, element);
    }


    public Action ucaAction(Entity entity, String ltype) {
        return new BasicAction(ActionType.UCA, ltype, entity, element);
    }

    public Action noUiAction(Entity entity, String ltype) {
        return new BasicAction(ActionType.NOUI, ltype, entity, element);
    }

    public Action modalAction(Entity entity, String ltype) {
        return new BasicAction(ActionType.MODAL, ltype, entity, element);
    }

    public Action createAction(Entity entity) {
        return new CreateAction(entity, element);
    }

    public Action deleteAction(Entity entity) {
        return new DeleteAction(entity, element);
    }

    public Action updateAction(Entity entity) {
        return new UpdateAction(entity, element);
    }

    public Action addAction(Entity entity, String targePage) {
        return new EmptyAction(ActionType.UCA, "ajouter", entity, element).targetPage(targePage).inViewOnly();
    }

    public Action editAction(Entity entity, String targePage) {
        return new EmptyAction(ActionType.UCA, "modifier", entity, element).targetPage(targePage).inViewOnly();
    }

    public Action backToListAction(Entity entity, String targePage) {
        return new EmptyAction(ActionType.UCA, "retourListe", entity, element).targetPage(targePage).inViewOnly();
    }

    public Action backToDetailAction(Entity entity, String targePage) {
        return new EmptyAction(ActionType.UCA, "retourConsulter", entity, element).targetPage(targePage).inViewOnly();
    }

    public Action getByFieldAction(Entity entity, Field... fieldList) {
        return (new GetByFieldAction(entity, element)).byField(fieldList);
    }

    public Action initModel(Entity entity) {
        return new InitModelAction(entity, element);
    }

    public Action initCreation(Entity entity, Field... fieldList) {
        return new InitCreateAction(entity, element, fieldList);
    }

    public Action initUpdate(Entity entity, Action... actionList) {
        return (new InitUpdateAction(entity, element, actionList)).inInit();
    }

    public Action goToModule(Entity entity, String target) {
        return new GoToModuleAction(entity, element, target);
    }

    public Action goToPage(Entity entity, String target) {
        return new GoToPageAction(entity, element, target);
    }

    public Action emitEvent(Entity entity, String target) {
        return new EmitEventAction(entity, element, target);
    }

    public Action listAll(Entity entity) {
        return new ListAction(entity, element);
    }

    public Action listAllPaginated(Entity entity) {
        return new ListPaginatedAction(entity, element);
    }

    public Action updateByDialogue(Entity entity) {
        return new UpdateByDialogueAction(entity, element).inViewOnly();
    }

    public Action getFromSession(Entity entity) {
        return new GetFromSessionAction(entity, element, null);
    }

    public Action getFromSession(String variable) {
        return new GetFromSessionAction(null, element, variable);
    }

    public Action getFromSession(Entity entity, String variable) {
        return new GetFromSessionAction(entity, element, variable);
    }

    public Action selectFromModel(Entity entity) {
        return new SelectFromModelAction(entity, element);
    }

    public GetFromModelAction getFromModel(Entity entity, String mdlName) {
        return new GetFromModelAction(entity, element, mdlName, null);
    }

    public GetFromModelAction getFromModel(Entity entity, String mdlName, String variable) {
        return new GetFromModelAction(entity, element, mdlName, variable);
    }

    public Action find(Entity entity) {
        return new FindAction(entity, element);
    }

    public FilterAction filter(Entity entity) {
        return new FilterAction(entity, element, true);
    }

    public FilterAction filterWithoutPagination(Entity entity) {
        return new FilterAction(entity, element, false);
    }


    public ApplyFilterAction applyFilter(Entity entity, Action a) {
        return new ApplyFilterAction(entity, element, a);
    }


    public Action initFilter(Entity entity, Action action) {
        return new InitFilterAction(entity, element, action);
    }

    public Section section(Component... componentList) {
        return new Section(element, componentList);
    }

    public Section section(Entity entity, Component... componentList) {
        return new Section(element, entity, componentList);
    }

    public Block block(Component... componentList) {
        return new Block(element, componentList);
    }

    public InlineBlock inlineBlock(Component... componentList) {
        return new InlineBlock(element, componentList);
    }


    public InColumn inColumn(Component... componentList) {
        return new InColumn(element, componentList);
    }

    public Div div(Component... componentList) {
        return new Div(element, componentList);
    }

    public Div div(String text) {
        return new Div(element, text);
    }

    public Span span(String text) {
        return new Span(element, text);
    }

    public Space space(Component... componentList) {
        return new Space(element, componentList);
    }


    public PrimaryPanel primaryPanel(Component... componentList) {
        return new PrimaryPanel(element, componentList);
    }

    public Container secondaryPanel(Component... componentList) {
        return new SecondaryPanel(element, componentList);
    }

    public Container simplePanel(Component... componentList) {
        return new SimplePanel(element, componentList);
    }


    public Component statePanel(Entity e) {
        return new StatePanel(element, e);
    }

    public Panel panel(Component... componentList) {
        return new Panel(element, componentList);
    }

    public Panel panel(Entity entity, Component... componentList) {
        return new Panel(element, entity, componentList);
    }


    public FilterPanel filterPanel(Entity entity, Component... componentList) {
        return new FilterPanel(element, entity, false, componentList);
    }

    public FilterPanel filterPanel(Entity entity, boolean initFilter, Component... componentList) {
        return new FilterPanel(element, entity, initFilter, componentList);
    }

    public ExtendedPanel extendedPanel(Component... componentList) {
        return new ExtendedPanel(element, componentList);
    }

    public TabMenu tabMenu(Component... componentList) {
        return new TabMenu(element, componentList);
    }

    public Detail detail(Entity e, Field... fieldList) {
        return new Detail(e, element, fieldList);
    }

    public Detail detail(Field f, Entity e, Field... fieldList) {
        return new Detail(f, e, element, fieldList);
    }

    public Form form(Entity e, Field... fieldList) {
        return new Form(element, e, fieldList);
    }

    public Component separator() {
        return new Separator(element);
    }

    public ContextButtonProvider dialogAction(Entity e, String typeButton, Field... fieldList) {
        return new ContextButtonProvider(element, typeButton, new DialogAction(element, e, fieldList));
    }

    public DialogAction dialogAction(Entity e, Field... fieldList) {
        return new DialogAction(element, e, fieldList);
    }

    public Component ifTrue(String condition, Component... componentList) {
        return new Condition(element, condition, "siVrai", false, componentList);
    }

    public Component ifTrueInLine(String condition, Component... componentList) {
        return new Condition(element, condition, "siVrai", true, componentList);
    }

    public Component ifTrueFalseInLine(String condition, Component... componentList) {
        return new Condition(element, condition, "siVraiFaux", true, componentList);
    }

    public Component ifFalse(String condition, Component... componentList) {
        return new Condition(element, condition, "siFaux", false, componentList);
    }

    public Component ifNotEmpty(String condition, Component... componentList) {
        return new Condition(element, condition, "nonVide", false, componentList);
    }

    public Component sifNotEmptyInLine(String condition, Component... componentList) {
        return new Condition(element, condition, "nonVide", true, componentList);
    }

    public Component ifEmpty(String condition, Component... componentList) {
        return new Condition(element, condition, "estVide", false, componentList);
    }

    public Component ifEmptyInLine(String condition, Component... componentList) {
        return new Condition(element, condition, "estVide", true, componentList);
    }

    public Component separator(int height) {
        return new Separator(element, height);
    }


    public Table table(Entity e, Field... fieldList) {
        return new Table(element, e, fieldList);
    }

    public Component actionBlock(Component... componentList) {
        return new ActionBlock(element, componentList);
    }


    public RefList<?> list(Field f) {
        if (f instanceof Ref) {
            return new RefList<>((Ref<?>) f);
        }
        return null;
    }

    public StaticList staticList(String lname) {
        return new StaticList(lname);
    }

    public StaticList staticList(String lname, String type) {
        return new StaticList(lname, type);
    }

    public Field tag(Field f) {
        return new Tag(f);
    }

    public Field code(Field f) {
        return new Code(f);
    }

    public Field custom(Field f) {
        return new Custom(f);
    }

    public Custom custom(String lname) {
        return new Custom(lname);
    }

    public Field actionColumn(Entity entity, Button button) {
        return new ActionColumn(entity, button);
    }


    public Field actionColumn(Entity entity, Action action) {
        return new ActionColumn(entity, action);
    }

    public Field actionColumn(Entity entity, String lname, ComponentWrappingElement c) {
        return new ActionColumn(entity, lname + entity.uname, c);
    }

    public Field editActionColumn(Entity entity, ComponentWrappingElement c) {
        return new ActionColumn(entity, "modifier" + entity.uname, c);
    }

    public Field deleteActionColumn(Entity entity, ComponentWrappingElement c) {
        return new ActionColumn(entity, "supprimer" + entity.uname, c);
    }

    public Field dateRangeBegin(Field f) {
        return new DateRangeBegin(f);
    }

    public Field dateRangeEnd(Field f) {
        return new DateRangeEnd(f);
    }

    public Field hidden(Field f) {
        return new Hidden(f);
    }

    public Field id(Field f) {
        return new Id(f);
    }

    public Field render(String lname) {
        return new Render(lname);
    }

    public Breadcrumb breadcrumb(String uname, Component... componentList) {
        return new Breadcrumb(element, uname, componentList);
    }

}
