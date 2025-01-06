package dev.cruding.engine.service.impl;

import dev.cruding.engine.champ.impl.Ref;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.service.Service;

public class ListReferenceDataByArg extends Service {

    private Entite entite;
    private Ref ref;

    public ListReferenceDataByArg(Entite entite, Ref ref) {
        this.entite = entite;
        this.ref = ref;
    }

    @Override
    public void addResourceMethod(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/listeReference/", ref.larg, "/{id", ref.uarg, "}\")");
        f.L____("public List<ReferenceDto> listerEnTantQueReference(@PathVariable Long id", ref.uarg, ") {");
        f.L________("List<Tuple> list = ", entite.lname, "Repository.listeReference(id", ref.uarg, ");");
        f.L________("List<ReferenceDto> dtoList = list.stream().map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class))).collect(Collectors.toList());");
        f.L________("return dtoList;");
        f.L____("}");
    }

    @Override
    public void addRepositoryMethod(JavaFlow f) {
        f.L("");
        f.L____("@Query(value = \"select x.id, x.", entite.lid, " from ", entite.uname, " x where x.", ref.larg, ".id =:id", ref.uarg, " order by x.", entite.lid, "\")");
        f.L____("List<Tuple> listeReference(@Param(\"id", ref.uarg, "\") Long id", ref.uarg, ");");
    }

}
